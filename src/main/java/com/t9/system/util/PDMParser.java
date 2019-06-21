package com.t9.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.xml.sax.InputSource;

import com.t9.system.entity.AppColumn;
import com.t9.system.entity.AppForm;
import com.t9.system.entity.Node;
import com.t9.system.util.file.FileUtil;

@SuppressWarnings({"unchecked","unused","rawtypes"})
public class PDMParser  implements Serializable{
	static String fileName = "D:\\work\\cattsoft\\2011年省集中告警项目\\05系统设计\\2011年广东公司省集中告警管理系统.pdm";
	static{
		fileName = "E:\\baiduyun\\work\\t9\\t9.pdm";
	
	}

	private Node rootNode;
	
	private PDMParser(){
		
	}

	static CacheManager cacheManager= new CacheManager();
	static Cache cache = cacheManager.getCache("pdmInfo");

	public static PDMParser getInstance(){
		return getInstance(null);
	}
	public static PDMParser getInstance(String fileName ){
		if(fileName==null||fileName.length()==0)
			fileName=PDMParser.fileName;
		String cacheKey="PDM:"+fileName;
		System.out.println(cacheKey);
		Element element = cache.get(cacheKey);
	//	if(element==null){
			PDMParser pdmParser=new PDMParser();
			pdmParser.saxParas(fileName);
			element = new Element(cacheKey,pdmParser);
			cache.put(element);
	//	}		
	//	PDMParser pdmParser = (PDMParser) element.getValue();
		return pdmParser;
	}
	
	public void saxParas(String fileName){
		SAXHandler saxHandler=new SAXHandler();
		try {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			SAXParser parser = spfactory.newSAXParser();
			InputStream inputStream=new FileInputStream(new File(fileName));
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8"); 
			InputSource inputSource = new InputSource(inputStreamReader); 
			parser.parse(inputSource, saxHandler);
			rootNode=saxHandler.getRootNode();
			FileUtil.writeToFile("./target/filetest/aa.txt", rootNode.toXml());
	//		System.out.println(rootNode.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Node> nodeList=rootNode.findNodeList("o:Package");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			if(node.getNodeList().length==0)
				continue;
			
		//	System.out.println(node.toXml());
			break;
		}
	}
	public String[] findPackage(){
		List<Node> resultList=new ArrayList<Node>();
		List<Node> nodeList=rootNode.findNodeList("o:Package");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			if(node.getNodeList().length==0)
				continue;
			resultList.add(node);
		}
		String[] packageNames=new String[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			Node node=resultList.get(i);
			packageNames[i]=node.childNodeValue("a:Name");
		}
		return packageNames;
	}
	public List<Node> findPackageTables(String packageName){
		List<Node> resultList=new ArrayList<Node>();
		List<Node> tm=rootNode.select("$"+packageName+"$");
		Node nn=null;
		for (int i = 0; i < tm.size(); i++) {
			Node node=tm.get(i);
			if(node.getNodeTag().equals("a:Name")&&node.getParentNode().getNodeTag().equals("o:Package")){
				nn=node.getParentNode();
			}
		}
		List<Node> nodeList=nn.findNodeList("o:Table");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			if(node.getNodeList().length==0)
				continue;
			resultList.add(node);
		}
		String[] tableNames=new String[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			Node node=resultList.get(i);
			tableNames[i]=node.childNodeValue("a:Name");
		}
		return resultList;
	}
	private Node findPackageTableInfo(String packageName,String tableName){
		List<Node> resultList=new ArrayList<Node>();
		List<Node> tm=rootNode.select("$"+packageName+"$");
		
		Node nn=null;
		for (int i = 0; i < tm.size(); i++) {
			Node node=tm.get(i);
			if(node.getNodeTag().equals("a:Name")&&node.getParentNode().getNodeTag().equals("o:Package")){
				nn=node.getParentNode();
				break;
			}
		}
		if(packageName.length()==0)
			nn=rootNode;
		List<Node> nodeList=nn.select("$"+tableName+"$");
		if(nodeList.size()==0){
			return null;
		}
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			if(node.getParentNode().getNodeTag().equals("o:Table")){
				nn=node.getParentNode();
				break;
			}
		}
		return nn;
	}
	public HashMap findTableInfo(String packageName,String tableName){
		return findTableInfo(findPackageTableInfo(packageName, tableName));
	}
	
	private HashSet getKeySet(Node tableNode){
		HashSet hashSet=new HashSet();
		List<Node> keyList=new ArrayList<Node>();
		List<Node> pList=tableNode.select("c:PrimaryKey o:Key");
		Pattern pattern=Pattern.compile("Ref=(o\\d+)");
		for (Node node : pList) {
			String vv=node.getAttrStr();
			Matcher matcher=pattern.matcher(vv);
			if(matcher.find()){
				String v=matcher.group(1);
				List<Node> tList=tableNode.select("#"+v);
				for (Node t : tList) {
					List<Node> cList=t.select("o:Column");
					for (Node c : cList) {
						vv=c.getAttrStr();
						matcher=pattern.matcher(vv);
						if(matcher.find()){
							keyList.addAll(tableNode.select("#"+matcher.group(1)));
						}
					}
				}
				
			}
		}
		for (Node node : keyList) {
			hashSet.add(node.childNodeValue("a:Code"));
		}
		return hashSet;
	}
	
	private HashMap findTableInfo(Node tableNode){
	//	System.out.println(tableNode.toXml());
		HashMap tableMap=new HashMap();
		AppForm sysTable=new AppForm();
		sysTable.setAPP_NAME(tableNode.childNodeValue("a:Name"));
		sysTable.setTABLE_CODE(tableNode.childNodeValue("a:Code"));
		sysTable.setTABLE_COMMENT(tableNode.childNodeValue("a:Comment"));		
		sysTable.setAPP_PATH(DataBaseHelper.getPathNameformTableName(sysTable.getTABLE_CODE()));
		sysTable.setCLASS_PATH(DataBaseHelper.getClassNameFromTableName(sysTable.getTABLE_CODE()));
		HashSet hashSet=getKeySet(tableNode);		
		List<Node> nodeList=tableNode.select("c:Columns o:Column");
		ArrayList tableColumnList=new ArrayList();
		tableMap.put("columnList", tableColumnList);
		ArrayList tableColumnMapList=new ArrayList();
		tableMap.put("columnListMap", tableColumnMapList);
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			String code=node.childNodeValue("a:Code");
			String dataType=node.childNodeValue("a:DataType");
			String Length=node.childNodeValue("a:Length");
			String scale=node.childNodeValue("a:Precision");
			String Mandatory=node.childNodeValue("a:Mandatory");


			String DATA_TYPE=dataType.replaceAll("\\(.*\\)", "");
			String COL_TYPE=DataBaseHelper.databaseTypeToJavaType(DATA_TYPE, scale) ;		
			
			AppColumn sysColumn=new AppColumn();
			sysColumn.setTABLE_CODE(sysTable.getTABLE_CODE());			
			sysColumn.setCOL_CODE(code);
			sysColumn.setCOL_NAME(node.childNodeValue("a:Name"));
			sysColumn.setCOL_TYPE(DATA_TYPE);
			sysColumn.setCOL_COMMENT(node.childNodeValue("a:Comment"));
			if(Mandatory.equals("1"))
				sysColumn.setNULLABLE("N");
			else
				sysColumn.setNULLABLE("Y");
			if(hashSet.contains(code))
				sysColumn.setCOL_USE("1");
			else
				sysColumn.setCOL_USE("");
			String datalength="";
			if(!"".equals(Length))
				datalength=Length;
			else{
				if(DATA_TYPE.toUpperCase().equals("SMALLINT"))
					datalength="1";
				else if(DATA_TYPE.toUpperCase().equals("INT"))
					datalength="10";
			}
			sysColumn.setDATA_LENGTH(datalength);
			sysColumn.setDATA_SCALE(scale);
			sysColumn.setJAVA_TYPE(COL_TYPE);
			sysColumn.setJAVA_COL(code);
			sysColumn.setSHOW_LIST(1l);
			sysColumn.setLIST_LENGTH(100l);
			sysColumn.setCOL_ORDER(new Long(i));
			tableColumnList.add(sysColumn);

			HashMap columnHashMap=new HashMap();
			columnHashMap.put("colCode", sysColumn.getCOL_CODE());
			columnHashMap.put("colName", sysColumn.getCOL_NAME());
			columnHashMap.put("colType",sysColumn.getCOL_TYPE());
			columnHashMap.put("javaCol",sysColumn.getJAVA_COL());
			columnHashMap.put("javaType", sysColumn.getJAVA_TYPE());
			columnHashMap.put("dataLength",sysColumn.getDATA_LENGTH());
			columnHashMap.put("dataScale",sysColumn.getDATA_SCALE());
		//	columnHashMap.put("htmlType", sysColumn);//1：文本,2：数字,3：下拉选择,4：时间,5：单选,6：复选框,7：下拉多选,8:隐藏域
//			columnHashMap.put("dataSource",sysColumn);
//			columnHashMap.put("dataSourceSrc", sysColumn);
//			columnHashMap.put("refCol",sysColumn);
//			columnHashMap.put("isPrimary",sysColumn.getIS_PRIMARY());
//			String colUser="0";
//			if("Y".equals(sysColumn.getIS_PRIMARY()))
//				colUser="1";
//			columnHashMap.put("colUser",colUser);
			columnHashMap.put("nullable",sysColumn.getNULLABLE());
			columnHashMap.put("colOrder",sysColumn.getCOL_ORDER());
			columnHashMap.put("colComment", sysColumn.getCOL_COMMENT());
			tableColumnMapList.add(columnHashMap);
		}
		tableMap.put("sysTable", sysTable);
//		sqls[nodeList.size()]="Insert into TBSYSTABLE(TABLE_CODE, TABLE_NAME, TABLE_COMMENT, DATASOURCE, GEN_FILE," +
//		"TABLE_PATH, TABLE_JAVA_PATH, PACKAGE, LOG_ID) Values   ('" +TABLE_NAME+"', '" +sTableCNName+"', NULL, " +
//		"NULL, '" +fileType+"',null,null,'" +packageStr+"', '" +logId+"')";
		
		tableMap.put("tableCode", sysTable.getTABLE_CODE());
		tableMap.put("tableName", sysTable.getAPP_NAME());
		tableMap.put("package",sysTable.getPACKAGE());
		tableMap.put("appPath", sysTable.getAPP_PATH());
		tableMap.put("classPath",sysTable.getCLASS_PATH());
		
		return tableMap;
	}

	public List findPackageTableList(){
		ArrayList arrayList=new ArrayList();
		String[] pa=findPackage();
		HashMap map=new HashMap();
		for (int i = 0; i < pa.length; i++) {
			map=new HashMap();
			map.put("NAME", pa[i]);
			arrayList.add(map);
			List<Node> tableNodes=findPackageTables( pa[i]);
			for (int j = 0; j < tableNodes.size(); j++) {
				Node node=tableNodes.get(j);
				map=new HashMap();
				map.put("NAME", node.childNodeValue("a:Name"));
				map.put("CODE", node.childNodeValue("a:Code"));
				map.put("PARENT", pa[i]);
				arrayList.add(map);
			}
		}		
		return arrayList;
	}
	
	public List<Map> findTableList(){
		ArrayList<Map> arrayList=new ArrayList();
		List<Node> tableNodes=rootNode.findNodeList("o:Table");
		for (int i = 0; i < tableNodes.size(); i++) {
			Node node=tableNodes.get(i);
			if(node.getNodeList().length==0)
				continue;
			arrayList.add(findTableInfo(node));
		}
		return arrayList;
	}
	
	public void run(){

		HashMap tableMap=PDMParser.getInstance(null).findTableInfo("基础数据","T_TRANS_NE");
//		saxParas();
		try {
	//		soupParas();
			saxParas(fileName);
		//	findTableInfo();
	//		Node n=findPackageTableInfo("基础数据","T_TRANS_NE");
	//		getTableScripts("光缆管理");//性能管理
			String json = JsonUtil.getJsonString(BuildTree.createTree(findTableList(), "PARENT", "NAME"));
			System.out.println(json);
		//	findTableInfo(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new PDMParser().run();
		
		
	}

}

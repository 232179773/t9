package com.t9.system.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.t9.system.entity.AppColumn;
import com.t9.system.entity.AppForm;
import com.t9.system.entity.Node;

@SuppressWarnings({"unused", "rawtypes", "unchecked" })
public class DomParser {
	static String fileName = "D:\\work\\2011��ʡ���и澯��Ŀ\\05ϵͳ���\\01��Ҫ���\\2011��㶫����ʡ���и澯����ϵͳ��Ŀ\\SJZGJGL-ģ��.pdm";
	static{
	//	fileName = "D:\\javawork2\\s\\WebRoot\\upload\\pdmtest.pdm";//2011��������ۺ����.pdm
	
	}
	
	private static DomParser domParser=new DomParser();
	private DomParser(){
		
	}
	public static DomParser getInstance(){
		if(rootNode==null){
			domParser.saxParas(fileName);
		}
		return domParser;
	}
	public static DomParser getInstanceByFile(String fileName ){
		if(fileName==null||fileName.length()==0)
			fileName=DomParser.fileName;
		domParser.saxParas(fileName);
		return domParser;
	}
	private static Node rootNode;
	
	public void saxParas(String fileName){
		SAXHandler saxHandler=new SAXHandler();
		try {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			SAXParser parser = spfactory.newSAXParser();
			parser.parse(new File(fileName), saxHandler);
			rootNode=saxHandler.getRootNode();
	//		System.out.println(rootNode.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Node> nodeList=rootNode.findNodeList("o:Package");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			if(node.getNodeList().length==0)
				continue;
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
	public String getTableScripts(String packageName){
		StringBuffer stringBuffer=new StringBuffer();
		List<Node> nodeList=findPackageTables(packageName);
		for (int i = 0; i < nodeList.size(); i++) {
			Node tableNode=nodeList.get(i);
		//	System.out.print(tableNode.childNodeValue("a:Code")+",");
			System.out.println("bcp jzgjdb.."+tableNode.childNodeValue("a:Code")+" out "+tableNode.childNodeValue("a:Code")+".txt -Ujzgjdbln -Pjzgjdbln -c -Sjzgj_20 -t$$@@$$]$$@@$$ -r@#$%% -b20000 -Jcp936 -e "+tableNode.childNodeValue("a:Code")+".err");
			stringBuffer.append(getTableScript(tableNode)).append("\r\n");
		}
		System.out.println();
		System.out.println();

		System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
	}
	public String getTableScript(Node tableNode){
	//	System.out.println(tableNode.toXml());
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("if exists (select 1 from  sysobjects where  id = object_id('")
			.append(tableNode.childNodeValue("a:Code")).append("') and    type = 'U')\r\n\tdrop table ")
			.append(tableNode.childNodeValue("a:Code")).append("\r\ngo\r\n");
		stringBuffer.append("create table ").append(tableNode.childNodeValue("a:Code")).append("(\r\n");

		List<Node> nodeList=tableNode.select("c:Columns o:Column");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
			String code=node.childNodeValue("a:Code");
			String dataType=node.childNodeValue("a:DataType");
			String Length=node.childNodeValue("a:Length");
			String scale=node.childNodeValue("a:Precision");
			String Mandatory=node.childNodeValue("a:Mandatory");
			if(Mandatory.equals("1")){
				Mandatory="not null";
			}else{
				Mandatory="null";
			}
			stringBuffer.append("\t").append(code).append("\t").append(dataType);
			stringBuffer.append("\t").append(Mandatory).append(",\r\n");
		}
		String key="";
		HashSet hashSet=getKeySet(tableNode);
		for (Iterator iterator = hashSet.iterator(); iterator.hasNext();) {
			String col = (String) iterator.next();
			key=col+",";
		}		
		if(key.length()>0){
			key=key.substring(0, key.length()-1);
			stringBuffer.append("\tconstraint PK_").append(tableNode.childNodeValue("a:Code"))
				.append(" primary key nonclustered (").append(key).append(")\r\n");
		}
		stringBuffer.append(")");
	//	System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
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
//		sysTable.setTABLE_NAME(tableNode.childNodeValue("a:Name"));
		sysTable.setTABLE_CODE(tableNode.childNodeValue("a:Code"));
		sysTable.setTABLE_COMMENT(tableNode.childNodeValue("a:Comment"));		
		
		HashSet hashSet=getKeySet(tableNode);		
		List<Node> nodeList=tableNode.select("c:Columns o:Column");
		ArrayList tableColumnList=new ArrayList();
		tableMap.put("columnList", tableColumnList);
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
			sysColumn.setCOL_CODE(code);
			sysColumn.setCOL_NAME(node.childNodeValue("a:Name"));
			sysColumn.setCOL_TYPE(DATA_TYPE);
			sysColumn.setCOL_COMMENT(node.childNodeValue("a:Comment"));
			if(Mandatory.equals("1"))
				sysColumn.setNULLABLE("N");
			else
				sysColumn.setNULLABLE("Y");
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
			String isEnum="N";
			if(sysColumn.getCOL_COMMENT().contains("ö��"))
				isEnum="Y";
			tableColumnList.add(sysColumn);
		}
		tableMap.put("sysTable", sysTable);
//		sqls[nodeList.size()]="Insert into TBSYSTABLE(TABLE_CODE, TABLE_NAME, TABLE_COMMENT, DATASOURCE, GEN_FILE," +
//		"TABLE_PATH, TABLE_JAVA_PATH, PACKAGE, LOG_ID) Values   ('" +TABLE_NAME+"', '" +sTableCNName+"', NULL, " +
//		"NULL, '" +fileType+"',null,null,'" +packageStr+"', '" +logId+"')";
		return tableMap;
	}
	
	public List findTableList(){
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
	
//	public void getImportSql(String tn){
//		List<Node> tm=rootNode.select("$"+tn+"$");
//		for (Iterator iterator = tm.iterator(); iterator.hasNext();) {
//			Node tableNode =( (Node) iterator.next()).getParentNode();
//			List<Node> nodeList=tableNode.select("c:Columns o:Column");
//			String tableName=tableNode.childNodeValue("a:Name");
//			String tableCode=tableNode.childNodeValue("a:Code");
//			long tableId =Long.parseLong(DatabaseUtil.getKeyId("","Global"));
//			String tb="INSERT INTO T_SYS_IMPORT_TABLE(ITABLEID,STABLENAME,STABLECNAME) VALUES ( " + tableId +",'" + tableCode +"','" +tableName+"')";
//			System.out.println(tb);
//			for (int i = 0; i < nodeList.size(); i++) {
//				long id = Long.parseLong(DatabaseUtil.getKeyId("","Global"));
//				Node node=nodeList.get(i);
//				String code=node.childNodeValue("a:Code");
//				String name=node.childNodeValue("a:Name");
//		String s="insert into TABLE_INFO(TABLE_CODE,TABLE_NAME,COLUMN_CODE,COLUMN_NAME)" +
//				"VALUES('"+tableCode+"','"+tableName+"','"+code+"','"+name+"')";
//		s="update T_SYS_IMPORT_ITEM set SFIELDCNAME='"+name+"' where ITABLEID=1222 and SFIELDNAME='"+code+"'";
//		
//
//		String dataType=node.childNodeValue("a:DataType");
//		String Length=node.childNodeValue("a:Length");
//		String scale=node.childNodeValue("a:Precision");
//		String Mandatory=node.childNodeValue("a:Mandatory");
//
//
//		String DATA_TYPE=dataType.replaceAll("\\(.*\\)", "");
//		String COL_TYPE=DataBaseHelper.databaseTypeToJavaType(DATA_TYPE, scale) ;
//		String iFieldType="1";
//		if(COL_TYPE.equals("String"))
//			iFieldType="2";
//		else if(COL_TYPE.equals("Long"))
//			iFieldType="1";
//		else if(COL_TYPE.equals("Double"))
//			iFieldType="5";
//		else if(COL_TYPE.equals("Date"))
//			iFieldType="4";
//		HashSet hashSet=getKeySet(tableNode);	
//		String iIsPK="2";
//		if(hashSet.contains(code))
//			iIsPK="1";
//		String iRequired="0";
//		if(Mandatory.equals("1"))
//			iRequired="1";
//		String iFieldLength="30";
//		if(!Length.equals(""))
//			iFieldLength=Length;
//		String iFieldPrec="0";
//		if(scale!=null&&scale.length()>0)
//			iFieldPrec=scale;
//		String iIsEnum="2";
//		String sEnumCode="";
//		String sRemark="";
//		int iSeqNo=i+1;
//		StringBuffer sqlbuffer=new StringBuffer();
//		sqlbuffer.append("insert into T_SYS_IMPORT_ITEM ");
//		sqlbuffer.append("(IITEMID ,ITABLEID ,SFIELDNAME ,SFIELDCNAME ,IFIELDTYPE ,IISPK ,IREQUIRED ,");
//		sqlbuffer.append("IFIELDLENGTH ,IISIMPORT ,IFIELDPREC ,ISEQNO ,IISENUM,SENUMCODE,SREMARK ) values (");
//		sqlbuffer.append(id+",");
//		sqlbuffer.append(tableId+",");
//		sqlbuffer.append("'"+code+"',");
//		sqlbuffer.append("'"+name+"',");
//		sqlbuffer.append(iFieldType+",");
//		sqlbuffer.append(iIsPK+",");
//		sqlbuffer.append(iRequired+",");
//		sqlbuffer.append(iFieldLength+",");
//		sqlbuffer.append("1,");
//		sqlbuffer.append(iFieldPrec+",");
//		sqlbuffer.append(iSeqNo+",");
//		sqlbuffer.append(iIsEnum+",");
//		sqlbuffer.append("'"+sEnumCode+"',");
//		sqlbuffer.append("'"+sRemark+"')");
//		System.out.println(sqlbuffer.toString());
//			}
//		}
//	}
	public void run(){

//		saxParas();
		try {
	//		soupParas();
			saxParas(fileName);
		//	findTableInfo();
	//		Node n=findPackageTableInfo("�����","T_TRANS_NE");
	//		getTableScripts("���¹���");//���ܹ���
	//		findTableList();
		//	findTableInfo(n);
			String[] tableNames="T_RES_REGION,T_RES_SITE".split(",");
			for (int i = 0; i < tableNames.length; i++) {
				String string = tableNames[i];

				System.out.println("delete from T_SYS_IMPORT_ITEM where ITABLEID in(select ITABLEID from T_SYS_IMPORT_TABLE where STABLENAME ='" +
						string+"')");
				System.out.println("delete from T_SYS_IMPORT_TABLE where STABLENAME ='" +
						string+"'");
				System.out.println();
				
			//	getTableScripts("���¹���");//���ܹ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DomParser().run();
		
		
	}

}

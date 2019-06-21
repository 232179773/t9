package com.t9.system.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.FastFileDaoImpl;
import com.t9.system.entity.AppColumn;
import com.t9.system.entity.AppForm;
import com.t9.system.entity.Appendix;
import com.t9.system.util.PDMParser;
import com.t9.system.util.StringUtil;
import com.t9.system.util.file.FileZipUtil;
import com.t9.system.web.RequestContext;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings({ "rawtypes","unchecked"})
@Service
public class FastFileServiceImpl{

	@Autowired
	private FastFileDaoImpl fastFileDaoImpl;
	@Autowired
	private DataCache dateCache;

	/**
	 * 表名查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryTable(Map<String, String> map,boolean pageInfo) {
		return fastFileDaoImpl.queryTable(map,pageInfo);
	}
	
	/**
	 */
	public int deleteTable(String tableCode) {
		dateCache.refreshTableMap();
		return fastFileDaoImpl.deleteTable(tableCode);
	}
	
	
	/**
	 * 表列名查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryTableColumnList(Map<String, String> map,boolean pageInfo) {
		return fastFileDaoImpl.queryTableColumnList(map,pageInfo);
	}
	
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 * @throws T9Exception 
	 */
	public T9Result savePdmTable(Map<String, String> map) throws T9Exception {
		if(fastFileDaoImpl.queryTable(map,false).size()>0){
			throw new T9Exception("您所输入的表名已存在！");
		}		

		String packageName=map.get("TABLE_PARENT");
		String tableCode=map.get("TABLE_CODE");
		HashMap tableMap=PDMParser.getInstance().findTableInfo(packageName, tableCode);
		AppForm appForm=(AppForm)tableMap.get("sysTable");	
		String fileType="LIST,EDIT,SELECT,DETAIL,ADD,MODI,DELETE,IMPORT,EXPORT";
		appForm.setGEN_FILE(fileType);
		fastFileDaoImpl.save(appForm);
				
		ArrayList columnList=(ArrayList)tableMap.get("columnList");

		int listLength=100;
		for (int i = 0; i < columnList.size(); i++) {
			AppColumn appColumn=(AppColumn)columnList.get(i);
			appColumn.setAPP_ID(appForm.getID());
			

			appColumn.setSHOW_ADD(1l);
			appColumn.setSHOW_EDIT(1l);
			appColumn.setSHOW_LIST(1l);
			
			if(i<3){
				appColumn.setSHOW_QUERY(1l);
			}else{
				appColumn.setSHOW_QUERY(9l);
			}
			
			if("1".equals(appColumn.getCOL_USE())){//字段为主键
				appColumn.setSHOW_ADD(0l);
				appColumn.setSHOW_EDIT(0l);
				appColumn.setSHOW_LIST(0l);
				appColumn.setSHOW_QUERY(9l);
				appColumn.setHTML_TYPE("hidden");
			}
			
			String colComment=appColumn.getCOL_COMMENT();
			Pattern pattern=Pattern.compile("TYPE=([a-zA-Z0-9:_]+)");
			Pattern patternTable=Pattern.compile("TYPE=([a-zA-Z0-9:_#}{]+)");
			Pattern patternSql=Pattern.compile("TYPE=([a-zA-Z0-9:_ =}{,']+)");
			Pattern patternCol=Pattern.compile("\\{([a-zA-Z0-9_]+)\\}");

			System.out.println(appColumn.getCOL_CODE()+":"+colComment);
			
			Matcher matcher=pattern.matcher(colComment);
			if(matcher.find()){
				String type=matcher.group(1);
				if(type.startsWith("DICT")){
					String dictType=type.split(":")[1];
					appColumn.setDATA_TYPE("dict");
					appColumn.setDATA_SOURCE(dictType);
					appColumn.setHTML_TYPE("select");
				}
				if(type.startsWith("TABLE")){
					matcher=patternTable.matcher(colComment);
					matcher.find();
					String table=matcher.group(1).split(":")[1];
					appColumn.setDATA_TYPE("table");
					appColumn.setDATA_SOURCE(table);
					appColumn.setHTML_TYPE("select");
				}
				if(type.startsWith("SQL")){
					matcher=patternSql.matcher(colComment);
					matcher.find();
					type=matcher.group(1);
					String sql=type.split(":")[1];
					appColumn.setDATA_TYPE("sql");
					appColumn.setDATA_SOURCE(sql);
					appColumn.setHTML_TYPE("select");
					
					matcher=patternCol.matcher(colComment);
					if(matcher.find()){
						String column=matcher.group(1);
						SQLQuery sqlQuery=new SQLQuery("update TB_APP_COLUMN set REF_COL=? where TABLE_CODE=? and COL_CODE=?", 
								appColumn.getCOL_CODE(),appColumn.getTABLE_CODE(),column);
						fastFileDaoImpl.execSql(sqlQuery);
					}
				}
			}
			
			fastFileDaoImpl.save(appColumn);
		}

		dateCache.refreshTableMap();
		T9Result result=new T9Result();
		return result;
	}
	
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 * @throws T9Exception 
	 */
	public T9Result saveTable(Map<String, String> map) {
		String APP_NAME = map.get("APP_NAME");
		String UNIQUE_COL= map.get("UNIQUE_COL");
		String TOOLBAR= map.get("TOOLBAR");
		TOOLBAR=TOOLBAR.replaceAll("'", "''");
		String TABLE_COMMENT= map.get("TABLE_COMMENT");
		String APP_PATH= map.get("APP_PATH");
		String CLASS_PATH= map.get("CLASS_PATH");
		String PACKAGE= map.get("PACKAGE");
		String LOG_ID= map.get("LOG_ID");
		String KEY_COLUMN= map.get("KEY_COLUMN");
		String GEN_FILE= map.get("GEN_FILE");
		String ID= map.get("ID");
		
		
		if(StringUtil.isNotEmpty(ID)){
			String sqlStr="update tb_app_form set APP_NAME=?,UNIQUE_COL=?,TOOLBAR=?,TABLE_COMMENT=?,APP_PATH=?,CLASS_PATH=?," +
					"PACKAGE=?,LOG_ID=?,KEY_COLUMN=?,GEN_FILE=? where ID=?";
			SQLQuery sqlQuery=new SQLQuery(sqlStr,APP_NAME,UNIQUE_COL,TOOLBAR,TABLE_COMMENT,APP_PATH,CLASS_PATH,PACKAGE,LOG_ID,KEY_COLUMN,GEN_FILE,ID);
			fastFileDaoImpl.execSql(sqlQuery);
		}else{
//			String sqlStr="insert into tb_app_form(APP_NAME=?,UNIQUE_COL=?,TOOLBAR=?,TABLE_COMMENT=?,APP_PATH=?,CLASS_PATH=?," +
//					"PACKAGE=?,LOG_ID=?,KEY_COLUMN=?,GEN_FILE=? where ID=?";
//			SQLQuery sqlQuery=new SQLQuery(sqlStr,APP_NAME,UNIQUE_COL,TOOLBAR,TABLE_COMMENT,APP_PATH,CLASS_PATH,PACKAGE,LOG_ID,KEY_COLUMN,GEN_FILE,ID);
//			fastFileDaoImpl.execSql(sqlQuery);
		}

		dateCache.refreshTableMap();
		T9Result result=new T9Result();
		return result;
	}

	
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 * @throws T9Exception 
	 */
	public T9Result saveColumn(Map<String, String> map) {
		String columnId = map.get("ID");
		String COL_NAME= map.get("COL_NAME");
		String TABLE_CODE= map.get("TABLE_CODE");
		String HTML_TYPE= map.get("HTML_TYPE");
		String DATA_TYPE= map.get("DATA_TYPE");
		String DATA_SOURCE= map.get("DATA_SOURCE");
		String REF_COL= map.get("REF_COL");
		String COL_USE= map.get("COL_USE");
		String SHOW_LIST= map.get("SHOW_LIST");
		String SHOW_ADD= map.get("SHOW_EDIT");
		String SHOW_EDIT= map.get("SHOW_EDIT");
		String SHOW_QUERY= map.get("SHOW_QUERY");
		String SHOW_REMARK= map.get("SHOW_REMARK");
		
		String sql="update tb_app_column set COL_NAME='"  +COL_NAME+"',HTML_TYPE='"+HTML_TYPE+"'," +
				"DATA_SOURCE='" +DATA_SOURCE+"',DATA_TYPE='" +DATA_TYPE+"'," 
				+"REF_COL='" +REF_COL+"',COL_USE='" +COL_USE+"',SHOW_REMARK='" +SHOW_REMARK+"'," +"SHOW_LIST=" +SHOW_LIST+"," +
				"SHOW_ADD=" +SHOW_ADD+",SHOW_EDIT=" +SHOW_EDIT+",SHOW_QUERY=" +SHOW_QUERY+
		" where ID="+columnId;
		
		String sqlStr="update tb_app_column set COL_NAME=?,HTML_TYPE=?,DATA_TYPE=?,DATA_SOURCE=?,REF_COL=?,COL_USE=?," +
				"SHOW_REMARK=?,SHOW_LIST=?,SHOW_ADD=?,SHOW_EDIT=?,SHOW_QUERY=? where ID=?";
		SQLQuery sqlQuery=new SQLQuery(sqlStr,COL_NAME,HTML_TYPE,DATA_TYPE,DATA_SOURCE,REF_COL,COL_USE,SHOW_REMARK,SHOW_LIST,SHOW_ADD,SHOW_EDIT,SHOW_QUERY,columnId);
		fastFileDaoImpl.execSql(sqlQuery);
		
		dateCache.refreshTableMap();
		T9Result result=new T9Result();
		return result;
	}
	

	public String generateFile(String tableCode,String pageType) throws Exception{
		
		HttpSession session =RequestContext.getContext().getSession();
		String ftlDir=session.getServletContext().getRealPath("/WEB-INF/fastftl")+File.separator;
		String fileDir=session.getServletContext().getRealPath("/frame/demo/genfile")+File.separator;	

		if(pageType.equals("list")||pageType.equals("List")){
			pageType="List.jsp";
		}
		if(pageType.equals("edit")||pageType.equals("Edit")){
			pageType="Edit.jsp";
		}
//		generateFile(ftlDir, fileDir, tableCode, "Entity.java",isView);
		generateFile(ftlDir, fileDir, tableCode, "List.js");
	//	generateFile(ftlDir, fileDir, tableName, "List.js");
        return generateFile(ftlDir, fileDir, tableCode, pageType);
	}
	
	

	public String downLoadFile(String tableCode) throws Exception{
		HashMap tableMap=dateCache.getTableMap(tableCode);
		String appPath=(String)tableMap.get("APP_PATH");
		HttpSession session =RequestContext.getContext().getSession();
		String ftlDir=session.getServletContext().getRealPath("/WEB-INF/fastftl")+File.separator;
		String destDir=session.getServletContext().getRealPath("/frame/demo/genfile")+File.separator;
		String fileDir=destDir+appPath+File.separator;	
		File destFile = new File(fileDir);
		if (!destFile.exists()){
			destFile.mkdirs();
		}

		generateFile(ftlDir, fileDir, tableCode, "Entity.java");
		generateFile(ftlDir, fileDir, tableCode, "List.js");
		generateFile(ftlDir, fileDir, tableCode, "Edit.jsp");
        generateFile(ftlDir, fileDir, tableCode, "List.jsp");
        FileZipUtil.zip(fileDir, destDir);
        return appPath+".zip";
	}

	public String generateFile(String ftlDir,String fileDir,String tableCode,String pageType) throws Exception{
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(ftlDir));
		Template t = cfg.getTemplate(pageType+".ftl","UTF-8");
        HashMap tableMap=dateCache.getTableMap(tableCode);
        String fileName=tableMap.get("APP_PATH")+pageType;
        if(pageType.endsWith("java")){
        	fileName=tableMap.get("CLASS_PATH")+pageType;
        }
        String jspFile=fileDir+fileName;
        File file=new File(jspFile);
		Writer out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
        t.process(tableMap, out);
        out.flush();    
        out.close(); 
        return fileName;
	}
	
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Appendix
	 */
	public Appendix queryAppendixById(String id) {
		return (Appendix)fastFileDaoImpl.get(id);
	}
	/**
	 * 更新数据
	 * @param appendix
	 * @return
	 */
	public T9Result saveColumnOrder(Map<String, String> map){
		return fastFileDaoImpl.saveColumnOrder(map);
	}

	public T9Result deleteAppendix(String... ids) {
		T9Result result=new T9Result();
		fastFileDaoImpl.delete(ids);
		return result;
	}


}

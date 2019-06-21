package com.t9.system.action;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.service.FastFileServiceImpl;
import com.t9.system.util.PDMParser;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@SuppressWarnings({"serial","unused","unchecked","rawtypes"})
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class FastFileAction extends BaseAction {
	
	@Autowired
	FastFileServiceImpl fastFileServiceImpl;
	
	public String queryTable(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List dList =fastFileServiceImpl.queryTable(map,true);
		super.renderToOutput(dList);
		return null;
	}
	public String queryTableById(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List dList =fastFileServiceImpl.queryTable(map,false);
		T9Result result=new T9Result();
		if(dList.size()>0){
			result.setObj(dList.get(0));
		}
		super.renderToOutput(result);
		return null;
	}

	
	public String savePdm() throws T9Exception {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		T9Result result=new T9Result();
		fastFileServiceImpl.savePdmTable(map);
		super.renderToOutput(result);
		return null;
	}

	public String findTableList() {
		try{
			String fileName=getRequest().getParameter("fileName");
			String cacheKey="PDM"+fileName;
		
			List list=PDMParser.getInstance().findTableList();

			super.renderToOutput(list);
		//	String json = JsonUtil.getJsonString(list);
			//BuildTree.createTree(list, "PARENT", "NAME");
		//	RequestUtil.responseOut("GBK", json, this.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String queryColumn(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List dList =fastFileServiceImpl.queryTableColumnList(map,true);
		super.renderToOutput(dList);
		return null;
	}
	

	public String queryColumnById(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List dList =fastFileServiceImpl.queryTableColumnList(map,false);
		T9Result result=new T9Result(dList.get(0));
		super.renderToOutput(result);
		return null;
	}
	
	public String saveColumn(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		T9Result result=fastFileServiceImpl.saveColumn(map);
		super.renderToOutput(result);
		return null;
	}


	public String saveTable(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		T9Result result=fastFileServiceImpl.saveTable(map);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteTable() throws Exception  {		
		T9Result result=new T9Result();		
		String tableCode=getRequest().getParameter("TABLE_CODE");
		fastFileServiceImpl.deleteTable(tableCode);
		super.renderToOutput(result);
		return null;
	}

	public String validateData() throws Exception  {	
		T9Result result=new T9Result();	
		String tableCode=getRequest().getParameter("tableCode");
		if(tableCode==""){
	//		String sql="delete from TB_APP_COLUMN where TABLE_CODE not in (select table_code from tb_app_form)";
	//		int flag =dataBaseHelper.updateSql(sql);
		}else{
			String[] tableCodes=tableCode.split(",");
			//�༭����߶�=����*30+100
			for (int i = 0; i < tableCodes.length; i++) {
				tableCode=tableCodes[i];
		   
			}
		}

		super.renderToOutput(result);
		return null;
	}

	public String saveColumnOrder() throws Exception  {	
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		T9Result result=fastFileServiceImpl.saveColumnOrder(map);
		super.renderToOutput(result);
		return null;
	}
	
	public String pageView() throws Exception {
		String url = getRequest().getRequestURI().toString();
		String pageType = getRequest().getParameter("pageType");
		String tableCode = getRequest().getParameter("TABLE_CODE");
		if (pageType == null)
			pageType = "List";

		String fileName = fastFileServiceImpl.generateFile(tableCode, pageType);
		getRequest().getSession().getServletContext()
				.getRequestDispatcher("/frame/demo/genfile/" + fileName)
				.forward(getRequest(), getResponse());
		return null;
	}

	public String downLoadFile() throws Exception {
		String tableCode = getRequest().getParameter("TABLE_CODE");
		String fileName = fastFileServiceImpl.downLoadFile(tableCode);
		getResponse().sendRedirect(getRequest().getContextPath()+"/frame/demo/genfile/" + fileName);
		return null;
	}
	
	
	public Object getModel() {
		return null;
	}


	public static void main(String[] args) throws Exception{
//		Configuration cfg = new Configuration();
//		String javaDir="D:\\\\Program Files (x86)\\apache-tomcat-6.0.32-windows-x86\\apache-tomcat-6.0.32\\webapps\\s\\";
//		cfg.setDirectoryForTemplateLoading(new File(javaDir));
//		Template t = cfg.getTemplate("list.ftl");
//
//		Writer out = new OutputStreamWriter(new FileOutputStream(javaDir+"list.jsp"));
//
//        FileGenerate fileGenerate=new FileGenerate();
//        HashMap tableMap=fileGenerate.getTableMap("T_RES_ROOM");
//        t.process(tableMap, out);
		String ss="^[a-zA-Z_]+$";
		System.out.println("T_RES._SITE".matches(ss));
	}
}

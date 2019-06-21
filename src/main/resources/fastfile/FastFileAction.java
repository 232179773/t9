package com.arp.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arp.system.common.ARPException;
import com.arp.system.common.PDMParser;
import com.arp.system.service.FastFileServiceImpl;
import com.arp.system.web.ARPResult;
import com.arp.system.web.RequestContext;
import com.arp.system.web.ServletUtil;

@SuppressWarnings({"serial","unused","unchecked","rawtypes"})
@Component
@Scope("prototype")
@ParentPackage("arpPackage")
public class FastFileAction extends BaseAction {
	
	@Autowired
	FastFileServiceImpl fastFileServiceImpl;
	
	public String queryTable(){
		Map map = getRequestMap();
		List dList =fastFileServiceImpl.queryTable(map,true);
		ARPResult result=new ARPResult(dList);
		super.renderToOutput(result);
		return null;
	}
	public String queryTableById(){
		Map map = getRequestMap();
		List dList =fastFileServiceImpl.queryTable(map,false);
		ARPResult result=new ARPResult();
		if(dList.size()>0){
			result.setObj(dList.get(0));
		}
		super.renderToOutput(result);
		return null;
	}

	
	public String savePdm() throws ARPException {
		Map map = getRequestMap();
		ARPResult result=new ARPResult();
		fastFileServiceImpl.savePdmTable(map);
		super.renderToOutput(result);
		return null;
	}

	public String findTableList() {
		Map map = getRequestMap();
		try{
			String fileName=(String)map.get("fileName");
			String cacheKey="PDM"+fileName;
		
			List list=PDMParser.getInstance().findTableList();

			ARPResult result=new ARPResult(list);
			super.renderToOutput(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String queryColumn(){
		Map map = getRequestMap();
		List dList =fastFileServiceImpl.queryTableColumnList(map,true);
		ARPResult result=new ARPResult(dList);
		super.renderToOutput(result);
		return null;
	}
	

	public String queryColumnById(){
		Map map = getRequestMap();
		List dList =fastFileServiceImpl.queryTableColumnList(map,false);
		ARPResult result=new ARPResult(dList.get(0));
		super.renderToOutput(result);
		return null;
	}
	
	public String saveColumn(){
		Map map = getRequestMap();
		ARPResult result=fastFileServiceImpl.saveColumn(map);
		super.renderToOutput(result);
		return null;
	}


	public String saveTable(){
		Map map = getRequestMap();
		ARPResult result=fastFileServiceImpl.saveTable(map);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteTable() {	
		Map map = getRequestMap();	
		ARPResult result=new ARPResult();		
		String tableCode=(String)map.get("TABLE_CODE");
		fastFileServiceImpl.deleteTable(tableCode);
		super.renderToOutput(result);
		return null;
	}

	public String validateData()  {
		Map map = getRequestMap();		
		ARPResult result=new ARPResult();	
		String tableCode=(String)map.get("TABLE_CODE");
		if(tableCode==""){
	//		String sql="delete from TB_APP_COLUMN where TABLE_CODE not in (select table_code from tb_app_form)";
	//		int flag =dataBaseHelper.updateSql(sql);
		}else{
			String[] tableCodes=tableCode.split(",");
			for (int i = 0; i < tableCodes.length; i++) {
				tableCode=tableCodes[i];
		   
			}
		}

		super.renderToOutput(result);
		return null;
	}

	public String saveColumnOrder() throws Exception  {	
		Map map = getRequestMap();		
		ARPResult result=fastFileServiceImpl.saveColumnOrder(map);
		super.renderToOutput(result);
		return null;
	}
	
	public String pageView() throws Exception {
		Map map = getRequestMap();		
		String pageType =(String)map.get("pageType");
		String tableCode =(String)map.get("TABLE_CODE");
		if (pageType == null)
			pageType = "List";

		String fileName = fastFileServiceImpl.generateFile(tableCode, pageType);

		HttpSession session =RequestContext.getContext().getSession();
		session.getServletContext().getRequestDispatcher("/frame/genfile/" + fileName)
				.forward(getRequest(), getResponse());
		return null;
	}

	public String downLoadFile() throws Exception {
		String tableCode = getRequest().getParameter("TABLE_CODE");
		String fileName = fastFileServiceImpl.downLoadFile(tableCode);
		getResponse().sendRedirect(getRequest().getContextPath()+"/frame/genfile/" + fileName);
		return null;
	}
	
	
	public Object getModel() {
		return null;
	}
}

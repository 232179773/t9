package com.t9.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.common.SQLQuery;
import com.t9.system.service.CommonService;
import com.t9.system.service.DataCache;
import com.t9.system.web.RequestContext;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class CommonAction extends BaseAction {

	private HashMap<String,String[]> sqlMap=new HashMap<String,String[]>();
	{
		sqlMap.put("DICT", new String[]{"select DICT_CODE OPTION_KEY,DICT_NAME OPTION_VALUE from TB_SYSTEM_DICT"});
		sqlMap.put("DICT_OPTION", new String[]{"select OPTION_VALUE OPTION_KEY,OPTION_NAME OPTION_VALUE from TB_SYSTEM_DICT_OPTION where DICT_CODE=? order by ORDER_NO","DICT_CODE"});
		sqlMap.put("PRIVINCE", new String[]{"SELECT REGION_CODE OPTION_KEY,REGION_NAME OPTION_VALUE FROM tb_system_region where PARENT_CODE='156' order by ORDER_NUM"});
	}
	@Autowired
	private CommonService commonService;
	

	@Autowired
	private DataCache dataCache;
	
	//列表查询
	public String queryOptions() throws T9Exception {
		Map<String, String> map = RequestContext.getContext().getParamsMap();
		String type=map.get("type");
		
		if(type.equals("SQL")){
			String sql=map.get("SQL");
			SQLQuery sqlQuery=new SQLQuery(sql);
			List<Map> list=commonService.queryList(sqlQuery);
			T9Result result=new T9Result(list);
			super.renderToOutput(result);
			return null;
		}
	
		if(!sqlMap.containsKey(type))
			throw new T9Exception("common.type.error");
		String[] sql=sqlMap.get(type);
		String[] params=new String[sql.length-1];
		String paramKey=type;
		for (int i = 1; i < sql.length; i++) {
			params[i-1]=map.get(sql[i]);
			paramKey+="_"+params[i-1];
		}
		if(dataCache.getData(paramKey)==null){
			SQLQuery sqlQuery=new SQLQuery(sql[0],params);
			List<Map> list=commonService.queryList(sqlQuery);
			dataCache.pubData(paramKey, list);
		}
		List list=(List)dataCache.getData(paramKey);
		T9Result result=new T9Result(list);
		super.renderToOutput(result);
		return null;
	}
	
	public String renderTable() throws T9Exception {
		Map<String, String> map = RequestContext.getContext().getParamsMap();
		String tableParam=map.get("tableParam");
		String dataId=map.get("dataId");
		if(tableParam.indexOf("{")>0){
			tableParam=tableParam.substring(0, tableParam.indexOf("{"));
		}
		String colValue=dataCache.getEntityName(tableParam,dataId);
		Map dmap=new HashMap();
		dmap.put("NAME_OF_ID", colValue);
		T9Result result=new T9Result(dmap);
		super.renderToOutput(result);
		return null;
	}
	
	public Object getModel() {
		return null;
	}
	

}

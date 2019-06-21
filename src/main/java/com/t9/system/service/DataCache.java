package com.t9.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.CommonDao;
import com.t9.system.dao.FastFileDaoImpl;
import com.t9.system.dao.MenuDao;
import com.t9.system.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
@Component  
public class DataCache implements ApplicationListener<ApplicationEvent> {

	
	private static HashMap dictMap=new HashMap();
	private static HashMap roleMap=new HashMap();
	private static HashMap configMap=new HashMap();
	private static HashMap tableMap=new HashMap();//缓存表，表字段信息
	private static HashMap<String,String> entityMap=new HashMap();//缓存表数据ID和name关系
	
	private static HashMap dataMap=new HashMap();//缓存表数据ID和name关系
	

	@Autowired CommonDao commonDao;
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private FastFileDaoImpl fastFileDaoImpl;
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		SQLQuery sqlQuery=new SQLQuery("select DICT_CODE,OPTION_VALUE,OPTION_NAME from tb_system_dict_option order by DICT_CODE,ORDER_NO");
		List<Map> list=commonDao.querySql(sqlQuery);
		for (int i = 0; i < list.size(); i++) {
			HashMap tMap=(HashMap)list.get(i);
			String dictCode=(String)tMap.get("DICT_CODE");
			if(!dictMap.containsKey(dictCode)){
				LinkedHashMap smap=new LinkedHashMap();
				dictMap.put(dictCode, smap);
			}
			LinkedHashMap smap=(LinkedHashMap)dictMap.get(dictCode);
			smap.put(tMap.get("OPTION_VALUE"), tMap.get("OPTION_NAME"));
		}
	//	System.out.println(datamap);
		
		
		sqlQuery=new SQLQuery("select ROLE_ID,b.* from tb_system_rolefunctionright a,tb_system_menu b where a.MENU_ID=b.ID");
		list=commonDao.querySql(sqlQuery);
		for (int i = 0; i < list.size(); i++) {
			HashMap tMap=(HashMap)list.get(i);
			String roleId=(String)tMap.get("ROLE_ID");
			if(!roleMap.containsKey(roleId)){
				List<Map> roleMenuList=new ArrayList<Map>();
				roleMap.put(roleId, roleMenuList);
			}
			List<Map> roleMenuList=(List<Map>)roleMap.get(roleId);
			roleMenuList.add(tMap);
		}

		List<Map> menulist=menuDao.queryList(new HashMap(),false);
		roleMap.put("all", menulist);
		
		sqlQuery=new SQLQuery("select CODE,VALUE from tb_system_config");
		list=commonDao.querySql(sqlQuery);
		for (int i = 0; i < list.size(); i++) {
			HashMap tMap=(HashMap)list.get(i);
			String code=(String)tMap.get("CODE");
			String value=(String)tMap.get("VALUE");
			configMap.put(code, value);
		}
		
		
		tableMap=fastFileDaoImpl.getAllTableMap();
	}
	
	public static String getDictOptionName(String dictType,String dictValue){
		LinkedHashMap smap=(LinkedHashMap)dictMap.get(dictType);
		if(smap==null)
			return "";
		return (String)smap.get(dictValue);
	}
	

	public static String getSyetemConfigValue(String code){
		return (String)configMap.get(code);
	}
	

	public static List<Map> getRoleMenus(String roleId){
		List<Map> roleMenuList=(List<Map>)roleMap.get(roleId);
		if(roleMenuList==null)
			return new ArrayList<Map>();
		return roleMenuList;
	}
	

	public HashMap getTableMap(String tableCode){
		HashMap tMap=(HashMap)tableMap.get(tableCode);
		return tMap;
	}
	
	public void refreshTableMap(){
		tableMap=fastFileDaoImpl.getAllTableMap();
		appMap=null;
	}
	HashMap<String,String> appMap;
	public String appPathToTableName(String appPath){
		String tableName=null;
		if(appMap==null){
			appMap=new HashMap();
			Iterator iterator=tableMap.keySet().iterator();
			for (; iterator.hasNext();) {
				String TABLE_CODE=(String)iterator.next();
				HashMap tMap = (HashMap) tableMap.get(TABLE_CODE);
				String APP_PATH=(String)tMap.get("APP_PATH");
				appMap.put(APP_PATH, TABLE_CODE);
			}
		}
		tableName=appMap.get(appPath);
		return tableName;
	}

	public String getEntityName(String tableCode,String keyValue){
		String cacheKey=tableCode+"_"+keyValue;
		String value=entityMap.get(cacheKey);
		if(StringUtil.isNotEmpty(keyValue)&&value==null){
			HashMap tMap=getTableMap(tableCode);
			String tableDataKeyColumn=(String)tMap.get("tableDataKeyColumn");
			String tableDataNameColumn=(String)tMap.get("tableDataNameColumn");
			SQLQuery sqlQuery=new SQLQuery("select "+tableDataNameColumn+" from "+tableCode+" where "+tableDataKeyColumn+"=?",keyValue);
			List<Map> list=commonDao.querySql(sqlQuery);
			if(list.size()>0){
				value= (String)((Map)list.get(0)).get(tableDataNameColumn);
				entityMap.put(cacheKey, value);
			}
		}			
		return value;
	}
	

	public String getSQLValue(String sql){
		String value=(String)dataMap.get(sql);
		if(StringUtil.isNotEmpty(sql)&&value==null){
			SQLQuery sqlQuery=new SQLQuery(sql);
			List<Map> list=commonDao.querySql(sqlQuery);
			if(list.size()>0){
				value= (String)((Map)list.get(0)).get("OPTION_VALUE");
				dataMap.put(sql, value);
			}
		}			
		return value;
	}
	

	
	public Object getData(String key){
		Object obj=dataMap.get(key);
		return obj;
	}
	
	public void pubData(String key,Object obj){
		dataMap.put(key, obj);
	}
}

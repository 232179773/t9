package com.arp.system.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.arp.system.common.ARPRuningTimeException;
import com.arp.system.common.DBHelper;
import com.arp.system.common.MemcachedUtil;
import com.arp.system.common.SQLHelper;
import com.arp.system.dao.CommonDao;
import com.arp.system.dao.FastFileDaoImpl;
import com.arp.system.util.DataCacheUtil;
import com.arp.system.util.StringUtil;
import com.arp.system.web.RequestContext;
import com.arp.system.web.SpringInit;
/**
 * Fast缓存类
 * @author husq
 * @date: 2014-11-19
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class FastFileCache {//implements ApplicationListener<ApplicationEvent> 


	private static HashMap<String,String> appMap;
	public static String appPathToTableName(String appPath){
		HashMap tableMap=getTableMap(null);
		String tableName=null;
		if(appMap==null){
			appMap=new HashMap();
			Iterator iterator=tableMap.keySet().iterator();
			for (; iterator.hasNext();) {
				String tableCode=(String)iterator.next();
				HashMap tMap = (HashMap) tableMap.get(tableCode);
				String APP_PATH=(String)tMap.get("APP_PATH");
				appMap.put(APP_PATH, tableCode);
			}
		}
		tableName=appMap.get(appPath);
		return tableName;
	}

	

	public static HashMap getTableMap(String tableCode){
		HashMap tableMap=(HashMap)DataCacheUtil.getData(DataCacheUtil.SYSTEM_TABLE_MAP,null);
		if(tableMap==null){
			tableMap=refreshTableMap();
		}
		if(tableCode==null){
			return tableMap;
		}
		return (HashMap)tableMap.get(tableCode);
	}
		
	public static HashMap refreshTableMap(){
		ApplicationContext applicationContext=RequestContext.getContext().getWebApplicationContext();
		FastFileDaoImpl fastFileDaoImpl=(FastFileDaoImpl)applicationContext.getBean(FastFileDaoImpl.class);
		HashMap tableMap=new HashMap();
		tableMap=fastFileDaoImpl.getAllTableMap();
		DataCacheUtil.putData(DataCacheUtil.SYSTEM_TABLE_MAP,null, tableMap);
		return tableMap;
	}
	
}

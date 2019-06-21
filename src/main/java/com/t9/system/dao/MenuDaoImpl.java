package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;
import com.t9.system.web.QueryResult;

@Repository
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao{

	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map) {
		return queryList(map,true);
	}
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map,boolean pageinfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT a.*,(select NAME from tb_system_menu where ID=a.PARENT_MENU_ID) PARENT_MENU_NAME FROM tb_system_menu a WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "NAME", "PARENT_MENU_ID");
		if(StringUtil.isNotEmpty(map.get("ID"))){
			String[] ids=map.get("ID").split(",");
			sqlQuery.addSQL(" AND ID in(SELECT parent_menu_id from tb_system_menu where ID IN( " + StringUtil.getInStr(ids)
					+ ")) OR ID IN("+ StringUtil.getInStr(ids) +")");
		}			
		sqlQuery.addSQL(" order by PARENT_MENU_ID,MENU_ORDER");
		
		List<Map> list =null;
		if(pageinfo){
			list=this.queryPageSql(sqlQuery);
		}else{
			list=this.querySql(sqlQuery);
		}
		return list;
	}
	
	
	/**
	 * 查询同一父菜单下最大的编码值
	 */
	@Override
	public String queryMaxCode(String parentId) {
		SQLQuery sqlQuery=new SQLQuery("SELECT MAX(CODE) CODE FROM TB_SYSTEM_MENU WHERE 1=1 ");
		sqlQuery.addEquealParam("PARENT_MENU_ID", parentId,true);
		List<Map> list = this.querySql(sqlQuery);
		String result=null;
		if(list.size()>0)
			result=(String)((Map)list.get(0)).get("CODE");
		return result;
	}
	
	
}

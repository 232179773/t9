package com.t9.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Exception;

@Repository
public class DictOptionDaoImpl extends BaseDaoImpl implements DictOptionDao {
	
	@Override
	public List<Map> queryDictOption(Map<String, String> map,boolean pageInfo) {
		SQLQuery sqlQuery = new SQLQuery(
				"SELECT * FROM TB_SYSTEM_DICT_OPTION WHERE 1=1 ");
		
		sqlQuery.addEquealParam(map, "ID","DICT_CODE","OPTION_VALUE");
		
		if((String)map.get("NOT_EQUAL_ID")!=null){
			sqlQuery.addSQL(" and ID != ? ",(String)map.get("NOT_EQUAL_ID"));
		}
		
		sqlQuery.addSQL(" ORDER BY ORDER_NO");
		List<Map> list= null;
		if(pageInfo){
			list = this.queryPageSql(sqlQuery);
		}else{
			list = this.querySql(sqlQuery);
		}
		return list;
	}
	
	
	
	/**
	 * 根据字典类编码删除字段项
	 */
	public int deleteDictOptionByDictCode(String DICT_CODE){
		SQLQuery sqlQuery = new SQLQuery(
				"delete FROM TB_SYSTEM_DICT_OPTION WHERE 1=1 ");
		sqlQuery.addSQL(" and DICT_CODE = ?",DICT_CODE);
		int i = super.execSql(sqlQuery);
		return i;
	}

	public int updateDictOption(Map<String, String> map) {
		SQLQuery sqlQuery = new SQLQuery("UPDATE TB_SYSTEM_DICT_OPTION SET OPTION_NAME=?, ORDER_NO=? WHERE ID=?",
				map.get("OPTION_NAME"),map.get("ORDER_NO"),map.get("ID"));
		int i = super.execSql(sqlQuery);
		return i;
	}

}

package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.entity.Dict;
import com.t9.system.web.QueryResult;

@Repository
public class DictDaoImpl extends BaseDaoImpl implements DictDao{
	/**
	 * 查询字典表
	 */
	public List<Map> queryDictListByPage(Map<String, String> map,boolean dictSqlType) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_DICT WHERE 1=1 ");
		if(dictSqlType){
			sqlQuery.addLikeParam("DICT_CODE", map.get("DICT_CODE"));
			sqlQuery.addLikeParam("DICT_NAME", map.get("DICT_NAME"));
		}else{
			String[] dict_codes=map.get("DICT_CODE").split(",");
			for(String dict_code:dict_codes){
				sqlQuery.addSQL(" and DICT_CODE =?", dict_code);
			}
		}
		List<Map> list= this.queryPageSql(sqlQuery);
		return list;
	}

	
	public List<Map> queryDictList(Map<String, String> map, boolean dictSqlType) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_DICT WHERE 1=1 ");
		if(dictSqlType){
			sqlQuery.addLikeParam("DICT_CODE", map.get("DICT_CODE"));
			sqlQuery.addLikeParam("DICT_NAME", map.get("DICT_NAME"));
		}else{
			String[] dict_codes=map.get("DICT_CODE").split(",");
			for(String dict_code:dict_codes){
				sqlQuery.addSQL(" and DICT_CODE =?", dict_code);
			}
		}
		List<Map> list= this.querySql(sqlQuery);
		return list;
	}

}

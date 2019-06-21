package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Result;

@Repository
@SuppressWarnings({ "rawtypes"})
public class AppendixDaoImpl extends BaseDaoImpl implements AppendixDao{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_APPENDIX WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "TABLE_NAME", "DATA_ID");
		List<Map> list = this.queryPageSql(sqlQuery);
		return list;
	}
	
	
	/**
	 * 更新数据
	 * @param appendix
	 * @return
	 */
	@Override
	public T9Result updateAppendix(String[] fileIds,String tableName,String tableId){
		T9Result result=new T9Result();
		SQLQuery sqlQuery=new SQLQuery("update TB_SYSTEM_APPENDIX set TABLE_NAME=?,DATA_ID=? WHERE Id in ("+StringUtil.getInStr(fileIds)+")",tableName,tableId);
		int i=this.execSql(sqlQuery);
		result.setObj(i);
		return result;
	}
	
	
}

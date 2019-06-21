package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;

@Repository
public class RoleFunctionRightDaoImpl extends BaseDaoImpl implements RoleFunctionRightDao {
	
	public List<Map> queryRoleFRList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_ROLEFUNCTIONRIGHT WHERE 1=1 ");
		
		sqlQuery.addSQL("and ROLE_ID=?",map.get("ROLE_ID"));
		
		List<Map> list= this.querySql(sqlQuery);
		return list;
	}

	
	public void deleteRoleFunctionRight(Map<String,String> map) {
		SQLQuery sqlQuery = new SQLQuery(
				"DELETE FROM TB_SYSTEM_ROLEFUNCTIONRIGHT WHERE 1=1 ");
		String[] role_ids = map.get("ROLE_ID").split(",");
		sqlQuery.addSQL(" and ROLE_ID in( " + StringUtil.getInStr(role_ids) + ")");
		super.execSql(sqlQuery);
	}
	
	public List<Map> queryTreeByParentId(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_ROLEFUNCTIONRIGHT WHERE 1=1 ");
		sqlQuery.addSQL("and ROLE_ID=?",map.get("ROLE_ID"));
		List<Map> list= this.querySql(sqlQuery);
		
		return list;
	}

	
}

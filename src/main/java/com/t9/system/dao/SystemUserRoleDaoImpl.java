package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;
@Repository
public class SystemUserRoleDaoImpl extends BaseDaoImpl implements SystemUserRoleDao{

	public int deleteUserRole(String... userIds) {
		
		SQLQuery sqlQuery=new SQLQuery("delete from TB_SYSTEM_USERROLE where USER_ID in ("+StringUtil.getInStr(userIds)+")");
		return execSql(sqlQuery);
	}

}

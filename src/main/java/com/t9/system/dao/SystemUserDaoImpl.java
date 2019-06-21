package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.entity.BaseDomain;
import com.t9.system.entity.SystemUser;
import com.t9.system.util.DataUtil;
import com.t9.system.web.T9RuningTimeException;

@Repository
public class SystemUserDaoImpl extends BaseDaoImpl implements SystemUserDao{
		
	public List<Map> queryList(Map<String, String> map,boolean pageinfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT users.*,role.ID ROLE_ID, role.NAME ROLE_NAME ");
		sqlQuery.addSQL(" FROM TB_SYSTEM_USER users ");
		sqlQuery.addSQL(" LEFT JOIN TB_SYSTEM_USERROLE userRole ON userRole.USER_ID = users.ID " );
		sqlQuery.addSQL(" LEFT JOIN TB_SYSTEM_ROLE role ON role.ID = userRole.ROLE_ID  ");		
		
		sqlQuery.addSQL(" WHERE 1=1 ");
		sqlQuery.addEquealParam("userRole.ROLE_ID", map.get("ROLE_ID"));
		sqlQuery.addEquealParam("LOGIN_CODE", (String)map.get("LOGIN_CODE"));
		sqlQuery.addLikeParam("users.NAME", (String)map.get("NAME"));
		sqlQuery.addLikeParam("users.USER_RIGHTTYPE", (String)map.get("USER_RIGHTTYPE"));
		
		sqlQuery.addSQL(" and SUPER_ADMIN = 0");//只能查询非超级管理员
		
        List<Map> lst = null;
		if(pageinfo){
			lst=this.queryPageSql(sqlQuery);
		}else{
			lst=this.querySql(sqlQuery);
		}
		return lst;
	}
	

	@Override
	public List<Map> userLogin(String loginCode, String password) {
		SQLQuery sqlQuery=new SQLQuery("SELECT *");
		sqlQuery.addSQL(" FROM TB_SYSTEM_USER users ");
			
		sqlQuery.addSQL(" WHERE 1=1 ");
		sqlQuery.addEquealParam("LOGIN_CODE", loginCode,true);
		String encryptedPassword = DataUtil.encodePassword(password, null);
		sqlQuery.addEquealParam("LOGIN_PWD", encryptedPassword,true);
        List<Map> lst =this.querySql(sqlQuery);
		return lst;
	}
	
	public List<Map> queryUserRole(String userId) {
		SQLQuery sqlQuery=new SQLQuery("SELECT ROLE_ID");
		sqlQuery.addSQL(" FROM tb_system_userrole");
			
		sqlQuery.addSQL(" WHERE 1=1 ");
		sqlQuery.addEquealParam("USER_ID", userId,true);
		return querySql(sqlQuery);
	}
	
	public void save(BaseDomain... pojos) {
		for(BaseDomain pojo: pojos){	
			SystemUser systemUser = (SystemUser)pojo;
			//密码加密
			String encryptedPassword = DataUtil.encodePassword(systemUser.getLOGIN_PWD(), null);
			systemUser.setLOGIN_PWD(encryptedPassword);
//			System.out.println("ep:"+systemUser.getLOGIN_PWD());
		}
		
		super.save(pojos);

	}
	
}

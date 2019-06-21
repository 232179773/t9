package com.t9.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.entity.SystemUser;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9RuningTimeException;
@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao{

	@Autowired
	private RoleFunctionRightDao roleFunctionRightDao;
	public List<Map> queryRoleList(Map<String, String> map,boolean pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_ROLE WHERE 1=1 ");
		sqlQuery.addLikeParam("NAME", map.get("NAME"));
		if(null!=map.get("PARENTROLEID")){
			sqlQuery.addSQL("and PARENTROLEID=?",map.get("PARENTROLEID"));
		}
		List<Map> list=null;
		if(pageInfo){
			list=this.queryPageSql(sqlQuery);
		}else{
			list=this.querySql(sqlQuery);
		}
		return list;
	}
	
	
//    public List<Map> queryUsersWithRole(String roleId, boolean page){
//
//    	StringBuffer sb = new StringBuffer();
//    	sb.append("select ur.ID,  role.NAME roleName, role.ID roleID, ");
//    	sb.append("users.NAME USERNAME, users.ID USERID,users.LOGIN_CODE, users.NICKNAME, users.STATUS ");
//    	sb.append("from tb_system_userrole ur ");
//    	sb.append("LEFT JOIN tb_system_role role on ur.role_id = role.ID ");
//    	sb.append("LEFT JOIN tb_system_user users on users.ID = ur.USER_ID ");
//    	sb.append("WHERE 1=1 ");
//
//    	SQLQuery sqlQuery = new SQLQuery(sb.toString()); 
//    	sqlQuery.addSQL(" AND users.super_admin != '1' ");
//    	sqlQuery.addSQL(" AND users.user_rightType != ? ", SystemUser.USER_RIGHTTYPE_FRONT);
//    	
//    	sqlQuery.addEquealParam("ur.ROLE_ID", roleId);
//    	
//		List<Map> list= null;
//		if(page) {
//			list=this.queryPageSql(sqlQuery);
//		} else {
//			list=this.querySql(sqlQuery);
//		}
//		
//		return list;
//	}
//	  
//    public List<Map> queryUsersWithoutRoleByPage(String roleId, String userName, String nickName, boolean page){
//
//    	StringBuffer sb = new StringBuffer();
//    	sb.append("select users.ID,  ");
//    	sb.append("users.NAME ,users.LOGIN_CODE, users.NICKNAME, users.STATUS ");
//    	sb.append("from tb_system_user users ");
//    	sb.append("LEFT JOIN tb_system_userrole ur  on users.ID = ur.USER_ID ");
//    	sb.append("WHERE 1=1 ");
//
//    	SQLQuery sqlQuery = new SQLQuery(sb.toString()); 
//    	sqlQuery.addSQL(" AND users.super_admin != '1' ");
//    	sqlQuery.addSQL(" AND users.user_rightType != ? ", SystemUser.USER_RIGHTTYPE_FRONT);
//    	sqlQuery.addLikeParam("users.NAME", userName);
//    	sqlQuery.addLikeParam("users.NICKNAME", nickName);
//
//    	if(StringUtils.isEmpty(roleId)) {
//    		throw new T9RuningTimeException("000001", "角色ID");
//    	}
//    	
//    	sqlQuery.addSQL(" AND (ur.ROLE_ID='' OR ur.ROLE_ID is null Or ur.ROLE_ID != ?)", roleId);
//    	
//		List<Map> list= null;
//		if(page) {
//			list=this.queryPageSql(sqlQuery);
//		} else {
//			list=this.querySql(sqlQuery);
//		}
//		
//		return list;
//	}
    
	public List<Map> queryRoleListByParentId(String parentIds) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_ROLE WHERE 1=1 ");
		sqlQuery.addSQL(" AND PARENTROLEID=?",parentIds);
		List<Map> list= this.querySql(sqlQuery);
		return list;
	}
	
	public int deleteRole(String id) throws T9Exception {
		SQLQuery sqlQuery = new SQLQuery(
				"select ID FROM TB_SYSTEM_ROLE WHERE 1=1 ");
		sqlQuery.addSQL(" and PARENTROLEID = ?",id);
		int subOptionsSize=this.querySql(sqlQuery).size();
		if(subOptionsSize>0){
			throw new T9Exception("system.RoleDaoImpl.NotdeleteMessage");
		}		
		int i = super.delete(id);
		return i;
	}




	
	
	

}

package com.t9.system.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Role;
import com.t9.system.service.RoleService;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class RoleAction extends BaseAction {
	@Autowired
	private RoleService roleService;

	@Autowired
	private SystemUserService systemUserService;
	
	private Role role = new Role();

	public List<Map> queryRoleListByPage() {
		Map<String, String> map = ServletUtils.getMapByRequest(this
				.getRequest());
		List<Map> list = roleService.queryRoleList(map,false);
		super.renderToOutput(list);
		return null;
	}

	public String queryRoleById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Role role=roleService.queryRoleById(id);
		T9Result result=new T9Result(role);
		super.renderToOutput(result);
		return null;
	}
	
//	public List<Map> queryUsersWithRole(){
//		Map map = ServletUtils.getMapByRequest(this.getRequest());
//		String id=(String)map.get("ROLE_ID");
//		List<Map> lst=roleService.queryUsersByRoleId(id);
////		Result result=new Result(lst);
//		super.renderToOutput(lst);
//		return null;
//	}
//	
//	public List<Map> queryUsersWithoutRole(){
//		Map map = ServletUtils.getMapByRequest(this.getRequest());
//		String id=(String)map.get("ROLE_ID");
//		String userName=(String)map.get("USER_NAME");
//		String nickName=(String)map.get("NICKNAME");
//
//		List<Map> lst=roleService.queryUsersWithoutRole(id, userName, nickName);
////		Result result=new Result(lst);
//		super.renderToOutput(lst);
//		return null;
//	}
	
	public String saveRole() throws ParseException {
		role.setCREATER(systemUserService.getSessionCurrentUser().getID());// 没有登陆
        role.setCREATETIME(new Date());
		T9Result result = roleService.saveStore(role);
		super.renderToOutput(result);
		return null;
	}
	
	public String updateRole(){
		Role roleOld=roleService.queryRoleById(role.getID());
		role.setCREATER(roleOld.getCREATER());
		role.setCREATETIME(roleOld.getCREATETIME());
		role.setPARENTROLEID(roleOld.getPARENTROLEID());
		
		T9Result result = roleService.updateRole(role);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteRole() throws T9Exception {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		T9Result result = roleService.deleteRole(id);
		super.renderToOutput(result);
		return null;
	}
	
	public void deleteRoleUser() {
		String roleUserIDs = getRequest().getParameter("ID");
		T9Result result = roleService.deleteRoleUsers(roleUserIDs);
		super.renderToOutput(result);
	}
	
	public void addRoleUser() {
		String roleId = getRequest().getParameter("ROLE_ID");
		String userIds = getRequest().getParameter("USER_ID");
		T9Result result = roleService.addRoleUser(roleId, userIds);
		super.renderToOutput(result);
	}
	
	public Object getModel() {

		return role;
	}

}

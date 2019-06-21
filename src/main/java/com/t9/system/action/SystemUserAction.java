package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.SystemUser;
import com.t9.system.service.SystemUserService;
import com.t9.system.util.StringUtil;
import com.t9.system.web.Logger;
import com.t9.system.web.LoggerModel;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class SystemUserAction extends BaseAction {

	@Autowired
	private SystemUserService systemUserService;
	private SystemUser systemUser = new SystemUser();
	
	
    public void queryUsersByPage() {
    	Map map = ServletUtils.getMapByRequest(this.getRequest());
    	List<Map> lst=systemUserService.searchUsers(map,true);
	    super.renderToOutput(lst);
    }
    
    
    
    public void getSystemUserById() {
    	Map map = ServletUtils.getMapByRequest(this.getRequest());
    	String id = (String)map.get("ID");
//		FieldValidater.varifyNotBlank(id, "用户编码");

    	SystemUser config=systemUserService.querySystemUserById(id);
    	//不能把密码传到前台
    	config.setLOGIN_PWD("");
		T9Result result=new T9Result(config);
		super.renderToOutput(result);
    }

    public void getCurrentUser() {
    	SystemUser currentUser = (SystemUser)getSession().getAttribute(ServletUtils.SEESION_USERINFO);
    	
        SystemUser userInfo = new SystemUser();
        userInfo.setLOGIN_CODE(currentUser.getLOGIN_CODE());
        userInfo.setNAME(currentUser.getNAME());
		T9Result result=new T9Result(userInfo);
		super.renderToOutput(result);
		
		
    }
    
	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息新增", logParam = "LOGIN_CODE={LOGIN_CODE}&NAME={NAME}")
	public void addUser(){
		systemUser.setSUPER_ADMIN(false);
		if(StringUtil.isEmpty(systemUser.getLOGIN_PWD())){
			systemUser.setLOGIN_PWD("123456");
		}
//		FieldValidater.varifyNotBlank(systemUser.getLOGIN_CODE(), "用户登录名");
//		FieldValidater.varifyNotBlank(systemUser.getLOGIN_PWD(), "登录密码");
//		FieldValidater.varifyNotBlank(systemUser.getNAME(), "名称");
//		FieldValidater.varifyNotBlank(systemUser.getNICKNAME(), "昵称");

		T9Result result=systemUserService.saveUser(systemUser);

		if(!SystemUser.USER_RIGHTTYPE_FRONT.equals(systemUser.getUSER_RIGHTTYPE())){
			String ROLE_ID=getRequest().getParameter("ROLE_ID");
			systemUserService.saveUserRoleRelation(systemUser.getID(), ROLE_ID);
		}
		
		super.renderToOutput(result);
	}
	
	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息修改", logParam = "ID={ID}&USER_NAME={USER_NAME}")
	public void editUser() throws T9Exception{
		T9Result result=systemUserService.updateUser(systemUser);
		
		if(!SystemUser.USER_RIGHTTYPE_FRONT.equals(systemUser.getUSER_RIGHTTYPE())){
			String ROLE_ID=getRequest().getParameter("ROLE_ID");
			systemUserService.saveUserRoleRelation(systemUser.getID(), ROLE_ID);
		}
		super.renderToOutput(result);
	}
	
	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息删除", logParam = "ID={ID}")
	public void deleteUser() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=systemUserService.deleteUsers(ids);
		super.renderToOutput(result);
	}
	
	@Logger(model = LoggerModel.SYSTEM,logName = "修改密码", logParam = "ID={ID}")
	public void changePassword() throws T9Exception {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		
    	String newPassword = (String)map.get("PASSWORD");
//		FieldValidater.varifyNotBlank(newPassword, "密码");

	 	String oldPassword = (String)map.get("OLD_PASSWORD");
//		FieldValidater.varifyNotBlank(oldPassword, "旧密码");
			
    	SystemUser currentUser = (SystemUser)getSession().getAttribute(ServletUtils.SEESION_USERINFO);
		T9Result result=systemUserService.changePassword(currentUser.getID(), oldPassword, newPassword);
		super.renderToOutput(result);
	}
	
	public Object getModel() {
		return systemUser;
	}
	
}

package com.t9.system.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.SystemUser;
import com.t9.system.http.HttpRequest;
import com.t9.system.http.HttpResponse;
import com.t9.system.service.DataCache;
import com.t9.system.service.MenuService;
import com.t9.system.service.RoleFunctionRightService;
import com.t9.system.service.SystemUserService;
import com.t9.system.util.BuildTree;
import com.t9.system.util.JsonUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.Logger;
import com.t9.system.web.LoggerModel;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;

@SuppressWarnings({ "unchecked", "serial","rawtypes"})
@Component
@Scope("prototype")
@ParentPackage("t9package")
@Results(value={@org.apache.struts2.config.Result(type=org.apache.struts2.views.freemarker.FreemarkerResult.class,value = "/frame/main.jsp",name="main")})
public class LoginAction extends BaseAction {

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private RoleFunctionRightService roleFunctionRightService;
	@Autowired
	private MenuService menuService;
	
	private SystemUser systemUser = new SystemUser();

	public String wxRediect()throws T9Exception {
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8536db86f332ef20&redirect_uri=" 
				+URLEncoder.encode("http://192.168.1.100:8080/t9/service/login!wxLogin")+
				"&response_type=code&scope=snsapi_base" +
				"&state=123#wechat_redirect";
		try {
			
			System.out.println(url);
			this.getResponse().sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String wxLogin()throws T9Exception {
		try {
			String code=this.getRequest().getParameter("code");
			String state=this.getRequest().getParameter("state");
			System.out.println("code:"+code);
			System.out.println("state:"+state);

			String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8536db86f332ef20" +
					"&secret=98b213ecb2c67f25c94df5657f4e4953&code=" +code+
					"&grant_type=authorization_code";
			HttpResponse httpResponse=HttpRequest.getUrlContent(url);
			String responseText=httpResponse.getResponseBody();
			System.out.println(responseText);
			this.getResponse().sendRedirect("http://192.168.1.100:8080/t9/test.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Logger(model = LoggerModel.SYSTEM,logName = "用户登录", logParam = "LOGIN_CODE={LOGIN_CODE}")
	public String userLogin() throws T9Exception {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		
		if("1".equals(DataCache.getSyetemConfigValue("login.verifyCode"))){
			String verifyCode=(String)map.get("verifyCode");
			ServletUtils.checkRandomValidateCode(this.getRequest(), verifyCode);
			if(StringUtil.isEmpty( (String)map.get("LOGIN_CODE"))||StringUtil.isEmpty( (String)map.get("PASSWORD"))){
				throw new T9Exception("system.LoginAction.loginError");
			}
		}
		
		List<Map> userlist=systemUserService.userLogin(map);
		if(userlist.size()==0){
			throw new T9Exception("system.LoginAction.loginError");
		}
		Map userMap=userlist.get(0);		
		SystemUser systemUser=systemUserService.querySystemUserById((String)userMap.get("ID"));
		List<Map> userRoleList=systemUserService.queryUserRole(systemUser.getID());
		String roleId="";
		for (int i = 0; i < userRoleList.size(); i++) {
			roleId=(String)((Map)userRoleList.get(i)).get("ROLE_ID");
		}
		this.getSession().setAttribute(ServletUtils.SEESION_USERROLE, roleId);
		this.getSession().setAttribute(ServletUtils.SEESION_USERINFO, systemUser);

		T9Result result=new T9Result(true,"登录成功！");
		super.renderToOutput(result);
		return null;
	}
	
	//列表查询
//	@Action(name="/blogs/${year}")  
	public String mainPage() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());

		SystemUser systemUser=systemUserService.getSessionCurrentUser();
		String roleId=(String)this.getSession().getAttribute(ServletUtils.SEESION_USERROLE);

		if(systemUser.getSUPER_ADMIN())
			roleId="all";
		
		List<Map> menulist=DataCache.getRoleMenus(roleId);
		List<Map> menutree=BuildTree.createTree(menulist, "PARENT_MENU_ID", "ID");
		
		this.getRequest().setAttribute("menutree", menutree);
		return "main";
	}
	

	
	public String logonOut() {
		this.getRequest().getSession().invalidate();
		try {
			String path = getRequest().getContextPath();
			this.getResponse().sendRedirect(path+"/frame/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object getModel() {
		return systemUser;
	}
	

}

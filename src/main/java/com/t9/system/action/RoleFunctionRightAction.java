package com.t9.system.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Role;
import com.t9.system.entity.RoleFunctionRight;
import com.t9.system.service.RoleFunctionRightService;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;


@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class RoleFunctionRightAction extends BaseAction{
	
	@Autowired
	private RoleFunctionRightService roleFunctionRightService;
	@Autowired
	private SystemUserService systemUserService;
	private RoleFunctionRight roleFunctionRight=new RoleFunctionRight();
	
	public List<Map> queryRoleFRListByPage(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=roleFunctionRightService.queryRoleFRList(map);
		super.renderToOutput(list);
		return null;
	}
	
	
	public String querySelectedRoleById(){
		
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list =roleFunctionRightService.queryTreeByParentId(map);
		T9Result result=new T9Result(list);
		super.renderToOutput(result);
		return null;
		
	}

	public String queryTreeByParentId(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list =roleFunctionRightService.queryTreeByParentId(map);
		List<RoleFunctionRight> listNew=new ArrayList<RoleFunctionRight>();
		for(Map mapNew:list){
			RoleFunctionRight roleFunctionRightNew=new RoleFunctionRight();
			if(null!=mapNew){
				roleFunctionRightNew.setROLE_ID(mapNew.get("ROLE_ID").toString());
				roleFunctionRightNew.setMENU_ID(mapNew.get("MENU_ID").toString());
			}
			
			listNew.add(roleFunctionRightNew);
		}
		T9Result result=new T9Result(listNew);
		super.renderToOutput(result);
		return null;
	}
	
	
	public String saveRoleFunctionRight() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		roleFunctionRightService.deleteRoleFunctionRight(map);
		String [] menu_ids=map.get("MENU_ID").toString().split(",");
		String role_id=map.get("ROLE_ID").toString();
		T9Result result=new T9Result();
		for(String strId:menu_ids){
			roleFunctionRight.setCREATETIME(new Date());
			roleFunctionRight.setCREATER(systemUserService.getSessionCurrentUser().getID());//登陆后
			roleFunctionRight.setBUTTON_ID("");
			roleFunctionRight.setROLE_ID(role_id);
			roleFunctionRight.setMENU_ID(strId);
			roleFunctionRightService.saveRoleFunctionRight(roleFunctionRight);
		}
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteRoleFunctionRight() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		T9Result result = roleFunctionRightService.deleteRoleFunctionRight(map);
		super.renderToOutput(result);
		return null;
	}
	
	@Override
	public Object getModel() {
		return roleFunctionRight;
	}

}

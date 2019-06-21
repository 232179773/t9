package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Menu;
import com.t9.system.service.MenuService;
import com.t9.system.util.BuildTree;
import com.t9.system.util.JsonUtil;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
@Results(value={@org.apache.struts2.config.Result(type=org.apache.struts2.views.freemarker.FreemarkerResult.class,value = "/frame/admin/menuTree.jsp",name="menuTree")})
public class MenuAction extends BaseAction {

	@Autowired
	private MenuService menuService;
	private Menu menu = new Menu();

	
	//列表查询
	public String queryMenuList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=menuService.queryList(map,true);
		super.renderToOutput(list);
		return null;
	}
	
	
	//树查询
	public String queryMenuTree() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=menuService.queryList(map,false);
//		List<Map> menutree=BuildTree.createTree(list, "PARENT_MENU_ID", "ID");
//		this.getRequest().setAttribute("menutree", menutree);
		T9Result result=new T9Result(list);
		super.renderToOutput(result);
	//	return "menuTree";
		return null;
	}
	
	//
	public String queryMenuById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Menu menu=menuService.queryMenuById(id);
		T9Result result=new T9Result(menu);
		super.renderToOutput(result);
		return null;
	}

	public String saveMenu(){
		T9Result result=menuService.saveMenu(menu);
		super.renderToOutput(result);
		return null;
	}
	public String updateMenu(){
		
		T9Result result=menuService.updateMenu(menu);

		super.renderToOutput(result);
		return null;
	}

	public String deleteMenu() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=menuService.deleteMenu(ids);
		super.renderToOutput(result);
		return null;
	}


	public Object getModel() {
		return menu;
	}

}

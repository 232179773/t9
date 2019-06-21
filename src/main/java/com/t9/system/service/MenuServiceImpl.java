package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.MenuDao;
import com.t9.system.entity.Menu;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Result;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map,boolean pageinfo) {
		return menuDao.queryList(map,pageinfo);
	}
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Menu
	 */
	public Menu queryMenuById(String id) {
		return (Menu)menuDao.get(id);
	}
	/**
	 * 保存数据
	 * @param menu
	 * @return
	 */
	public T9Result saveMenu(Menu menu) {
		T9Result result=new T9Result();

		String parentId=menu.getPARENT_MENU_ID()==null?"":menu.getPARENT_MENU_ID();
		String code=menuDao.queryMaxCode(parentId);
		if(StringUtil.isNotEmpty(code)){
			String parentCode=code.substring(0, code.length()-2);
			String subCode=code.substring(code.length()-2);
			int sc=Integer.parseInt(subCode)+1;
			code=parentCode+(sc>10?""+sc:"0"+sc);			
		}else{
			Menu menut=(Menu)menuDao.get(parentId);
			String parentCode=menut.getCODE();
			code=parentCode+"01";
		}
		menu.setCODE(code);
		menuDao.save(menu);
		return result;
	}
	

	/**
	 * 更新数据
	 * @param menu
	 * @return
	 */
	public T9Result updateMenu(Menu menu){
		T9Result result=new T9Result();
		menuDao.update(menu);
		return result;
	}

	public T9Result deleteMenu(String... ids) {
		T9Result result=new T9Result();
		menuDao.delete(ids);
		return result;
	}


}

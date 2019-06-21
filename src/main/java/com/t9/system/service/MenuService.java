package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Menu;
import com.t9.system.web.T9Result;

public interface MenuService {

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map,boolean pageinfo);

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Menu
	 */
	public Menu queryMenuById(String id);

	/**
	 * 保存数据
	 * @param Menu
	 * @return
	 */
	public T9Result saveMenu(Menu menu);

	
	/**
	 * 更新数据
	 * @param Menu
	 * @return
	 */
	public T9Result updateMenu(Menu menu);
	
	public T9Result deleteMenu(String... ids);

}
package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.web.T9Exception;

public interface DictOptionDao extends BaseDao{
	
	
	/**
	 * 根据字典类编码删除字段项
	 */
	public int deleteDictOptionByDictCode(String DICT_CODE);
	
	public int updateDictOption(Map<String,String>map);
	
	
	public List<Map> queryDictOption(Map<String, String> map,boolean pageInfo);
	

}

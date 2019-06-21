package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Demo;
import com.t9.system.entity.Dict;
import com.t9.system.entity.DictOption;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

public interface DictOptionService {
	/**
	 * 查询 字典项
	 * @param e
	 * @return
	 */
	public List<Map> queryDictOption(Map<String, String> map,boolean pageInfo);
	/**
	 * 增加指点项
	 * @param dict
	 * @return
	 * @throws T9Exception 
	 */
	public T9Result saveDictOption(DictOption dictOption) throws T9Exception;
	
	/**
	 * 修改
	 * @param dict
	 */
	public T9Result updateDicOption(Map<String,String>map);
	/**
	 *  删除
	 * @param ids
	 * @throws T9Exception 
	 */
	public T9Result deleteDicOption(String id) throws T9Exception;
	
	
	public DictOption queryDictOptionById(String id);
	
	
}

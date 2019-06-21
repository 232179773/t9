package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Dict;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Result;

public interface DictService {

	/**
	 * 分页查询 dic
	 * @param e
	 * @return
	 */
	public List<Map> queryDictList(Map<String, String> e);
	
	
	public Dict queryDictById(String id);
	/**
	 * 增加dic
	 *
	 * @param dict
	 * @throws Exception TODO
	 */
	public T9Result saveDict(Dict dict);
	/**
	 * 修改
	 * @param dict
	 */
	public T9Result updateDict(Dict dict);
	/**
	 * 删除字典类
	 */
	public T9Result deleteDict(String DICT_CODE,String id);
}

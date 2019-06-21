package com.t9.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.DictDao;
import com.t9.system.dao.DictOptionDao;
import com.t9.system.entity.Dict;
import com.t9.system.entity.DictOption;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Result;

@Service
public class DictServiceImpl implements DictService{

	@Autowired
	private DictDao dictDao;
	@Autowired
	private DictOptionDao dictOptionDao;
	public List<Map> queryDictList(Map<String, String> e) {
		
		return dictDao.queryDictListByPage(e,true);
	}


	public Dict queryDictById(String id) {
		return (Dict)dictDao.get(id);
	}
	
	@Override
	public T9Result saveDict(Dict dict) {
		T9Result result=new T9Result();
		
		dictDao.save(dict);
		return result;
		
	}
	public T9Result updateDict(Dict dict) {
		T9Result result=new T9Result();
		try {
			dictDao.update(dict);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 删除字典类
	 */
	public T9Result deleteDict(String DICT_CODE,String id) {
		T9Result result=new T9Result();
		dictOptionDao.deleteDictOptionByDictCode(DICT_CODE);
		dictDao.delete(id);
		return result;
	}

}

package com.t9.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.DictDao;
import com.t9.system.dao.DictOptionDao;
import com.t9.system.entity.DictOption;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class DictOptionServiceImpl implements DictOptionService{
	@Autowired
	private DictOptionDao dictOptionDao;
	@Autowired
	private DictDao dictDao;
	
	public List<Map> queryDictOption(Map<String, String> map,boolean pageInfo) {
		
		return dictOptionDao.queryDictOption(map,pageInfo);
	}

	public T9Result saveDictOption(DictOption dictOption) throws T9Exception {
		T9Result result=new T9Result();
		HashMap map=new HashMap();
		map.put("DICT_CODE", dictOption.getDICT_CODE());
		map.put("OPTION_VALUE", dictOption.getOPTION_VALUE());
		map.put("NOT_EQUAL_ID", dictOption.getID());
		List list=dictOptionDao.queryDictOption(map,false);
		if(list.size()>0){
			throw new T9Exception("system.DictOptionServiceImpl.optionCodeExist");
		}
		dictOptionDao.save(dictOption);
		return result;
	}

	
	public T9Result updateDicOption(Map<String,String>map) {
		T9Result result=new T9Result();
		dictOptionDao.updateDictOption(map);
		return result;
	}

	
	public T9Result deleteDicOption(String id) throws T9Exception {
		T9Result result=new T9Result();
		dictOptionDao.delete(id);
		return result;
	}
	
	public DictOption queryDictOptionById(String id) {
		return (DictOption)dictOptionDao.get(id);
	}



}

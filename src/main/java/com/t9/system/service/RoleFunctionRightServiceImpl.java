package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.RoleFunctionRightDao;
import com.t9.system.entity.RoleFunctionRight;
import com.t9.system.web.T9Result;

@Service
public class RoleFunctionRightServiceImpl implements RoleFunctionRightService {

	@Autowired
	private RoleFunctionRightDao roleFunctionRightDao;

	public List<Map> queryRoleFRList(Map<String, String> map) {

		return roleFunctionRightDao.queryRoleFRList(map);
	}

	@Override
	public T9Result saveRoleFunctionRight(RoleFunctionRight roleFunctionRight) {
		T9Result result = new T9Result();
		roleFunctionRightDao.save(roleFunctionRight);
		return result;
	}

	@Override
	public T9Result deleteRoleFunctionRight(Map map) {
		T9Result result=new T9Result();
		roleFunctionRightDao.deleteRoleFunctionRight(map);
		return result;
	}

	@Override
	public List<Map> queryTreeByParentId(Map<String, String> map) {
		
		return roleFunctionRightDao.queryTreeByParentId(map);
	}

	
}

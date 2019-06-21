package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.CommonDao;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{

	@Autowired CommonDao commonDao;

	public List<Map> queryList(SQLQuery sqlQuery) {
		return commonDao.querySql(sqlQuery);
	}
}

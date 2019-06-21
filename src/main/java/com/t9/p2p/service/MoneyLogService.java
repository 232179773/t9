package com.t9.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.MoneyLogDao;
import com.t9.p2p.entity.MoneyLog;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.PageInfo;
import com.t9.system.web.T9Result;

@Service
public class MoneyLogService{
	@Autowired
	private MoneyLogDao moneyLogDao;

	@Autowired
	private SystemUserService systemUserService;
	
	
	public List<Map> queryMoneyLogList(Map<String, String> map,PageInfo pageInfo) {
		return moneyLogDao.queryMoneyLogList(map,pageInfo);
	}

	public T9Result saveMoneyLog(MoneyLog moneyLog) {
		T9Result result=new T9Result();
		
		moneyLogDao.save(moneyLog);
		
		return result;
	}

	public T9Result updateMoneyLog(MoneyLog moneyLog) {
		T9Result result=new T9Result();
		moneyLogDao.update(moneyLog);
		return result;
	}

	public T9Result deleteMoneyLog(String[] ids) {
		T9Result result=new T9Result();
		moneyLogDao.delete(ids);
		return result;
	}

	public MoneyLog queryMoneyLogById(String id) {
		return (MoneyLog)moneyLogDao.get(id);

	}
}

package com.t9.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.CardLogDao;
import com.t9.p2p.entity.CardLog;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.PageInfo;
import com.t9.system.web.T9Result;

@Service
public class CardLogService{
	@Autowired
	private CardLogDao cardLogDao;

	@Autowired
	private SystemUserService systemUserService;
	
	
	public List<Map> queryCardLogList(Map<String, String> map,PageInfo pageInfo) {
		return cardLogDao.queryCardLogList(map,pageInfo);
	}

	public T9Result saveCardLog(CardLog cardLog) {
		T9Result result=new T9Result();
		
		cardLogDao.save(cardLog);
		return result;
	}

	public T9Result updateCardLog(CardLog cardLog) {
		T9Result result=new T9Result();
		cardLogDao.update(cardLog);
		return result;
	}

	public T9Result deleteCardLog(String[] ids) {
		T9Result result=new T9Result();
		cardLogDao.delete(ids);
		return result;
	}

	public CardLog queryCardLogById(String id) {
		return (CardLog)cardLogDao.get(id);

	}
}

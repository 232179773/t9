package com.t9.system.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.dao.LogDao;
import com.t9.system.entity.Log;
import com.t9.system.util.DateUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@Service
@Transactional
public class LogServiceImpl implements LogService{

	@Autowired LogDao logDao;

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map>  queryList(Map<String, String> map) {
		return logDao.queryList(map);
	}
	
	/**
	 * PV统计
	 * @param Map
	 * @return 
	 * @throws T9Exception 
	 */
	public List<Map>  pvStat(Map<String, String> map) throws T9Exception {
		String STAT_CYCLE=map.get("STAT_CYCLE");
		if("day".equals(STAT_CYCLE)){
			String LOG_TIME_START=map.get("LOG_TIME_START");
			if(StringUtil.isEmpty(LOG_TIME_START)){
				LOG_TIME_START=DateUtil.dateToShortString(new Date());
			}
			map.put("LOG_TIME_START", LOG_TIME_START);
			map.put("LOG_TIME_END", LOG_TIME_START);
			return logDao.statPageViewByURL(map);
		}
		if("month".equals(STAT_CYCLE)){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.MONTH, -1);
			String LOG_TIME_START=map.get("LOG_TIME_START");
			if(StringUtil.isEmpty(LOG_TIME_START)){
				LOG_TIME_START=DateUtil.dateToShortString(new Date());
			}
			map.put("LOG_TIME_START", DateUtil.dateToShortString(calendar.getTime()));
			map.put("LOG_TIME_END", DateUtil.dateToShortString(new Date()));
			return logDao.statPageViewByDay(map);
		}
		if("year".equals(STAT_CYCLE)){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.YEAR, -1);
			map.put("LOG_TIME_START", DateUtil.dateToShortString(calendar.getTime()));
			map.put("LOG_TIME_END", DateUtil.dateToShortString(new Date()));
			return logDao.statPageViewByMONTH(map);
		}
		throw new T9Exception("");
	}
	/**
	 * IP统计
	 * @param Map
	 * @return 
	 * @throws T9Exception 
	 */
	@Override
	public List<Map>  ipStat(Map<String, String> map) throws T9Exception {
		String STAT_CYCLE=map.get("STAT_CYCLE");
		if("day".equals(STAT_CYCLE)){
			String LOG_TIME_START=map.get("LOG_TIME_START");
			if(StringUtil.isEmpty(LOG_TIME_START)){
				LOG_TIME_START=DateUtil.dateToShortString(new Date());
			}
			map.put("LOG_TIME_START", LOG_TIME_START);
			map.put("LOG_TIME_END", LOG_TIME_START);
			map.put("timeType", "%Y-%m-%d %H:00:00");
			return logDao.statIP(map);
		}
		if("month".equals(STAT_CYCLE)){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.MONTH, -1);
			String LOG_TIME_START=map.get("LOG_TIME_START");
			if(StringUtil.isEmpty(LOG_TIME_START)){
				LOG_TIME_START=DateUtil.dateToShortString(new Date());
			}
			map.put("LOG_TIME_START", DateUtil.dateToShortString(calendar.getTime()));
			map.put("LOG_TIME_END", DateUtil.dateToShortString(new Date()));
			map.put("timeType", "%Y-%m-%d");
			return logDao.statIP(map);
		}
		if("year".equals(STAT_CYCLE)){
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.YEAR, -1);
			map.put("LOG_TIME_START", DateUtil.dateToShortString(calendar.getTime()));
			map.put("LOG_TIME_END", DateUtil.dateToShortString(new Date()));
			map.put("timeType", "%Y%m");
			return logDao.statIP(map);
		}
		throw new T9Exception("");
	}
	
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Log
	 */
	public Log queryLogById(String id) {
		return (Log)logDao.get(id);
	}
	/**
	 * 保存数据
	 * @param staff
	 * @return
	 */
	public T9Result saveLog(Log log) {
		T9Result result=new T9Result();
		logDao.save(log);
		return result;
	}

	public T9Result deleteLog(String... ids) {
		T9Result result=new T9Result();
		logDao.delete(ids);
		return result;
	}


}

package com.t9.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.PlatDao;

@Service
public class PlatService{
	@Autowired
	private PlatDao platDao;
	
	public List<Map> queryPlatList(Map<String, String> map) {
		return platDao.queryPlatList(map);
	}
}

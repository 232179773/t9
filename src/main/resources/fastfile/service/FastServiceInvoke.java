package com.arp.system.service;

import java.util.List;
import java.util.Map;

import com.arp.system.web.ARPResult;

@SuppressWarnings({ "rawtypes"})
public interface FastServiceInvoke{

	public Map getParamsMap();
	
	public List<Map> invokeQueryList();

	public ARPResult invokeQueryById();

	public ARPResult invokeSaveEntity();	

	public ARPResult invokeUpdateEntity();

	public ARPResult invokeDeleteEntity();
}

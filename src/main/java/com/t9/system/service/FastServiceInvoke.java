package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.web.T9Result;

@SuppressWarnings({ "rawtypes"})
public interface FastServiceInvoke{

	public Map getParamsMap();
	
	public List<Map> invokeQueryList();

	public T9Result invokeQueryById();

	public T9Result invokeSaveEntity();	

	public T9Result invokeUpdateEntity();

	public T9Result invokeDeleteEntity();
}

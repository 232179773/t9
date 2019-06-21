package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.web.RequestContext;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastServiceInvokeImpl implements FastServiceInvoke{
	@Autowired
	FastService fastService;
	
	public Map getParamsMap(){
		return RequestContext.getContext().getParamsMap();
	}
	
	public List<Map> invokeQueryList(){
		return fastService.queryList();
	}
	
	public T9Result invokeQueryById(){
		Map map=fastService.queryById();
		T9Result result=new T9Result(map);
		return result;
	}
	
	@Override
	public T9Result invokeSaveEntity() {
		return fastService.saveEntity();
	}

	@Override
	public T9Result invokeUpdateEntity() {
		return fastService.updateEntity();
	}

	@Override
	public T9Result invokeDeleteEntity() {
		return fastService.deleteEntityByIds();
	}
	
}

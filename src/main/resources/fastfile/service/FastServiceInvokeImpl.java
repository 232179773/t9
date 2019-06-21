package com.arp.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arp.system.web.ARPResult;
import com.arp.system.web.RequestContext;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastServiceInvokeImpl implements FastServiceInvoke{
	@Autowired
	FastService fastService;
	
	public Map getParamsMap(){
		return RequestContext.getContext().getParamMap();
	}
	
	public List<Map> invokeQueryList(){
		return fastService.queryList();
	}
	
	public ARPResult invokeQueryById(){
		List<Map> list=fastService.queryList();
		ARPResult result=new ARPResult(list.get(0));
		return result;
	}
	
	@Override
	public ARPResult invokeSaveEntity() {
		return fastService.saveEntity();
	}

	@Override
	public ARPResult invokeUpdateEntity() {
		return fastService.updateEntity();
	}

	@Override
	public ARPResult invokeDeleteEntity() {
		return fastService.deleteEntityByIds();
	}
	
}

package com.arp.system.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.arp.system.web.ARPResult;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastBaseService{


	Logger log = Logger.getLogger(this.getClass());
	
	public List<Map> queryList(FastServiceInvoke serviceInvoke){
		return serviceInvoke.invokeQueryList();
	};
	
	public ARPResult queryById(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeQueryById();
	}
	
	public ARPResult saveEntity(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeSaveEntity();		
	}
	
	public ARPResult updateEntity(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeUpdateEntity();
	}
	
	public ARPResult deleteEntityById(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeDeleteEntity();
	}
	

}

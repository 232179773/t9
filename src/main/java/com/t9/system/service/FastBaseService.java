package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.t9.system.web.T9Result;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastBaseService{


	Logger log = Logger.getLogger(this.getClass());
	
	public List<Map> queryList(FastServiceInvoke serviceInvoke){
		return serviceInvoke.invokeQueryList();
	};
	
	public T9Result queryById(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeQueryById();
	}
	
	public T9Result saveEntity(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeSaveEntity();		
	}
	
	public T9Result updateEntity(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeUpdateEntity();
	}
	
	public T9Result deleteEntityById(FastServiceInvoke serviceInvoke) {
		return serviceInvoke.invokeDeleteEntity();
	}
	

}

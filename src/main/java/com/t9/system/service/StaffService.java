package com.t9.system.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.t9.system.util.EncrytpUtils;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Result;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StaffService extends FastBaseService{

	@Override
	public T9Result saveEntity(FastServiceInvoke serviceInvoke) {
		Map map=serviceInvoke.getParamsMap();
		String PASSWORD=(String)map.get("PASSWORD");
	//	if(StringUtil.isNotEmpty(PASSWORD))
		PASSWORD=EncrytpUtils.Md5(PASSWORD);
		map.put("PASSWORD", PASSWORD);
		return super.saveEntity(serviceInvoke);
	}

	@Override
	public T9Result updateEntity(FastServiceInvoke serviceInvoke) {
		return super.updateEntity(serviceInvoke);
	}

}
package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.t9.system.entity.SystemConfig;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class SystemConfigDaoTest {

	@Autowired
	SystemConfigDao systemConfigDao;
	
	@Test
	public void testQuerySystemConfigByCodes() {
		SystemConfig config = new SystemConfig();
		config.setCODE("r_dddd_21");
		config.setNAME("缓存地址端口");
		config.setVALUE("123.123");
		config.setDATATYPE("1");
		config.setDISPLAY_ATTRIBUTE("1");
		config.setEDITSTYLE("1");
		
		systemConfigDao.save(config);
		
		@SuppressWarnings("rawtypes")
		List<Map> list = systemConfigDao.querySystemConfigByCodes("r_dddd_21");
		
		Assert.isTrue(list != null && StringUtils.equals("123.123", (String) list.get(0).get("VALUE")));
	}

	@Test
	public void testQuerySystemConfigByUser() {
		
	}
	
	
	
	
}

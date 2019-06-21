package com.t9.system.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.RenRenStat;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class RenRenStatServiceImplTest {


	@Autowired
	private RenRenStatServiceImpl statServiceImpl;
	
	@Test
	@Rollback(false)
	public void testSaveStat() {
		RenRenStat stat=new RenRenStat();
		//总金额
		stat.setAGGREGATE_AMOUNT(23.90);
		//总次数
		stat.setSUM_TIMES(47441l);
		//为用户赚取金额
		stat.setSUM_RATE(12953.65);
		stat.setSTAT_TIME(new Date());
		statServiceImpl.saveStat(stat);
	}

}

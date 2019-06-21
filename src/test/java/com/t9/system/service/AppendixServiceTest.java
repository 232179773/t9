package com.t9.system.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.Appendix;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class AppendixServiceTest {

	@Autowired
	private AppendixService appendixService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryList() {
		HashMap map=new HashMap();
		appendixService.queryList(map);
	}

	@Test
	public void testQueryAppendixById() {
		Appendix appendix=new Appendix();
		appendix.setTABLE_NAME("TB_TEST");
		appendix.setFILE_NAME("test.txt");
		appendix.setFILE_SIZE("1002");
		appendix.setFILE_URL("/upload/12.txt");
		appendixService.saveAppendix(appendix);
		Appendix appendix1=appendixService.queryAppendixById(appendix.getID());
		assertEquals(appendix1.getTABLE_NAME(), "TB_TEST");
	}

	@Test
	public void testSaveAppendix() {
		Appendix appendix=new Appendix();
		appendix.setTABLE_NAME("TB_TEST");
		appendix.setFILE_NAME("test.txt");
		appendix.setFILE_SIZE("1002");
		appendix.setFILE_URL("/upload/12.txt");
		appendixService.saveAppendix(appendix);
	}

	@Test
	public void testUpdateAppendix() {
		Appendix appendix=new Appendix();
		appendix.setTABLE_NAME("TB_TEST");
		appendix.setFILE_NAME("test.txt");
		appendix.setFILE_SIZE("1002");
		appendix.setFILE_URL("/upload/12.txt");
		appendixService.saveAppendix(appendix);
		appendix.setFILE_SIZE("1002");
		appendixService.updateAppendix(appendix);
	}

	@Test
	public void testDeleteAppendix() {
		Appendix appendix=new Appendix();
		appendix.setTABLE_NAME("TB_TEST");
		appendix.setFILE_NAME("test.txt");
		appendix.setFILE_SIZE("1002");
		appendix.setFILE_URL("/upload/12.txt");
		appendixService.saveAppendix(appendix);
		appendixService.deleteAppendix(appendix.getID());
	}

}

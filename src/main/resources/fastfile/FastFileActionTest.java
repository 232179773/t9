package com.arp.system.action;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.arp.system.common.ARPException;
import com.arp.system.service.FastFileCache;
import com.arp.system.util.BeanUtil;
import com.arp.system.web.ARPResult;

public class FastFileActionTest  extends BaseTest{

	@Test
	public void testQueryTable() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO&page=1&pagesize=10";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			//		e.printStackTrace();
		}
		action.queryTable();
		ARPResult arpResult = getArpResult();
		List list = (List) arpResult.getObj();
		Assert.assertTrue(list.size() > 0);
		String tableName=FastFileCache.appPathToTableName("staffInfo");
		Assert.assertEquals("", tableName, "HR_STAFF_INFO");

		try {
			action.savePdm();
		} catch (ARPException e) {
			Assert.assertTrue(e.getMessage().equals("the table is exists!"));
		}
	}

	@Test
	public void testQueryTableById() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			e.printStackTrace();
		}
		action.queryTableById();
		ARPResult arpResult = getArpResult();
		Map map = (Map) arpResult.getObj();
		Assert.assertTrue(map!=null);
		action = getAction(FastFileAction.class, BeanUtil.objToString(map, "&"));
		action.saveTable();
	}

	
	@Test
	public void testFindTableList() {
		String params = "";
		FastFileAction action = getAction(FastFileAction.class, params);
		action.findTableList();
		ARPResult arpResult = getArpResult();
		List list = (List) arpResult.getObj();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void testQueryColumn() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO&page=1&pagesize=10";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			e.printStackTrace();
		}
		action.queryColumn();
	}

	@Test
	public void testQueryColumnById() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			e.printStackTrace();
		}
		action.queryColumnById();
		
		ARPResult arpResult = getArpResult();
		Map obj =  (Map)arpResult.getObj();
		action = getAction(FastFileAction.class, BeanUtil.objToString(obj, "&"));
		action.saveColumn();
	}

	@Test
	public void testDeleteTable() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			e.printStackTrace();
		}
		action.queryColumnById();
		action.validateData();
		action.deleteTable();
	}
	
	@Test
	public void testPageView() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
			action.pageView();
			action.downLoadFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}

package com.arp.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import com.arp.hr.entity.StaffInfo;
import com.arp.system.common.ARPException;
import com.arp.system.common.ARPRuningTimeException;
import com.arp.system.util.BeanUtil;
import com.arp.system.web.ARPResult;
import com.arp.system.web.MockRequest;
import com.arp.system.web.MockResponse;
import com.arp.system.web.RequestContext;
import com.arp.system.web.ServletUtil;

public class FastActionTest extends BaseTest{

	@Test
	public void testExecute() {
		String params = "TABLE_PARENT=&TABLE_CODE=HR_STAFF_INFO";
		FastFileAction action = getAction(FastFileAction.class, params);
		try {
			action.savePdm();
		} catch (ARPException e) {
			//		e.printStackTrace();
		}
		action.queryTableById();
		ARPResult arpResult = getArpResult();
		Map mapTable = (Map) arpResult.getObj();
		mapTable.put("UNIQUE_COL", "STAFF_CODE");
		Assert.assertTrue(mapTable!=null);
		action = getAction(FastFileAction.class, BeanUtil.objToString(mapTable, "&"));
		action.saveTable();
		
		HashMap map=new HashMap();
		MockRequest request=new MockRequest();
		request.addHeader("User-Agent", "");
		HttpServletResponse response=new MockResponse();
		request.addParameters(map);
		RequestContext.setActionContext(request, response);
		RequestContext.getContext().setTest(true);
		
		FastAction fastAction = applicationContext.getBean(FastAction.class);
		request.setRequestURI("/service/staffInfoA!queryList");
		try{
			fastAction.execute();
		}catch(ARPRuningTimeException e){
			
		}
		StaffInfo staffInfo=new StaffInfo();
		staffInfo.setSTAFF_CODE("111");
		staffInfo.setNAME("test");
		staffInfo.setGENDER("male");
		staffInfo.setSTAFF_TYPE("1");
		map=new HashMap();
		BeanUtil.copyProperties(map, staffInfo);
		RequestContext.getContext().setParamMap(map);
		request.setRequestURI("/service/staffInfo!saveEntity");
		fastAction.execute();
		arpResult = getArpResult();
 		Assert.assertTrue(arpResult.isSuccess());
 		

 		map=new HashMap();
 		map.put("renderCols", "GENDER:DICT:HR_STAFF_GENDER");
		RequestContext.getContext().setParamMap(map);
		request.setRequestURI("/service/staffInfo!queryList");
		fastAction.execute();
		arpResult = getArpResult();
		List list = (List) arpResult.getObj();
		Assert.assertTrue(list.size() > 0);
		
		map=new HashMap();
 		map.put("renderCols", "GENDER:DICT:HR_STAFF_GENDER");
 		String id=(String)((Map)list.get(0)).get("ID");
 		map.put("ID",id );
		RequestContext.getContext().setParamMap(map);
		request.setRequestURI("/service/staffInfo!queryEntity");
		fastAction.execute();
		arpResult = getArpResult();
		Map rmap = (Map) arpResult.getObj();
		Assert.assertTrue(rmap.keySet().size() > 0);
		
		
		Map tMap=(Map)list.get(0);
		RequestContext.getContext().setParamMap(tMap);
		request.setRequestURI("/service/staffInfo!updateEntity");
		fastAction.execute();
		arpResult = getArpResult();
 		Assert.assertTrue(arpResult.isSuccess());
 		
 		request.setRequestURI("/service/staffInfo!deleteEntity");
		fastAction.execute();
		arpResult = getArpResult();
 		Assert.assertTrue(arpResult.isSuccess());
 		
//		

		
	}

}

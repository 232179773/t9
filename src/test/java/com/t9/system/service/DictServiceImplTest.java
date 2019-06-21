package com.t9.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.Dict;
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class DictServiceImplTest {

	@Autowired
	private DictService dictService;

	
	@Test
	public void testQueryDictList() {
		
	}
	
	@Rollback(false)
	@Test 
	public void testSaveDic() throws Exception{
		Map<String,String>map=new HashMap<String,String>();
		map.put("DICT_NAME", "testName1");
		List<Map>list=dictService.queryDictList(map);
		StringBuffer sbCode=new StringBuffer();
		StringBuffer sbId=new StringBuffer();
		if(null!=list){
			for(int i=0,sizeList=list.size();i<sizeList;i++){
				Map<String,Object> mapDict=(Map<String,Object>)list.get(i);
				sbCode.append(mapDict.get("DICT_CODE")+",");
				sbId.append(mapDict.get("ID")+",");
			}
			dictService.deleteDict(sbCode.toString(),sbId.toString());
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Dict dict=new Dict();
		dict.setDICT_CODE("testCode3");
		dict.setDICT_NAME("testName1");
		dict.setDICT_TYPE("1");
		dict.setREMARK("test");
		dictService.saveDict(dict);
		Assert.assertTrue("query is one", dict.getID()!=null);
		
	}
	@Rollback(false)
	@Test
	public void testUpdateDic() {
		
	}

	@Rollback(false)
	@Test
	public void testDeleteDic() {
		
	}

}

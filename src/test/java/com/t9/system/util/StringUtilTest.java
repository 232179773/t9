package com.t9.system.util;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsNotEmpty() {
		boolean result=StringUtil.isNotEmpty(null);
		Assert.assertFalse(result);
		result=StringUtil.isNotEmpty("");
		Assert.assertFalse(result);
		result=StringUtil.isNotEmpty("aa");
		Assert.assertTrue(result);
		String code="01";
		if(StringUtil.isNotEmpty(code)){
			String parentCode=code.substring(0, code.length()-2);
			String subCode=code.substring(code.length()-2);
			code=parentCode+String.format("02d", Integer.parseInt(subCode)+1);			
		}else{
			code="01";
		}
		System.out.println(code);
		
	}

	@Test
	public void testGetInStr() {
		String[] strs=new String[]{"aa","bb"};
		String result=StringUtil.getInStr(strs);
		Assert.assertEquals(result, "'aa','bb'");
	}

	@Test
	public void testSubstringBefore() {
		String result=StringUtil.substringBefore("DemoDaoImpl", "DaoImpl");
		Assert.assertEquals(result, "Demo");
	}

	@Test
	public void testSubstringAfter() {
		String result=StringUtil.substringAfter("DemoDaoImpl", "Demo");
		Assert.assertEquals(result, "DaoImpl");
	}

	@Test
	public void testCapitalize() {
		String result=StringUtil.capitalize("systemUser");
		Assert.assertEquals(result, "SystemUser");
	}

	@Test
	public void testEqualsIgnoreCase() {
		boolean result=StringUtil.equalsIgnoreCase("DemoServiceImpl", "DemoserviceImpl");
		Assert.assertTrue(result);
	}

	@Test
	public void testGetParamString() {
		String str="select OPTION_VALUE,OPTION_NAME from TB_SYSTEM_DICT_OPTION where DICT_CODE={DICT_CODE} and DICT_VALUE={DICT_VALUE}";
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("DICT_CODE", "sex");
		map.put("DICT_VALUE", "male");
		String[] result=StringUtil.getParamString(str, map);
		Assert.assertArrayEquals(result,new String[]{"select OPTION_VALUE,OPTION_NAME from TB_SYSTEM_DICT_OPTION where DICT_CODE=? and DICT_VALUE=?","sex","male"});
	}
	
	@Test
	public void testGetRightString() {
		String str="DICT_CODE={DICT_CODE} and DICT_VALUE={DICT_VALUE}";
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("DICT_CODE", "sex");
		map.put("DICT_VALUE", "male");
		String result=StringUtil.getRightString(str, map);
		Assert.assertEquals(result,"DICT_CODE=sex and DICT_VALUE=male");
	}

}

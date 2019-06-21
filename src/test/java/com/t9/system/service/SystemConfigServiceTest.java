package com.t9.system.service;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.SystemConfig;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class SystemConfigServiceTest {

	@Autowired
	private SystemConfigService systemConfigService;
	
	@Test
	public  void testUpdateSystemConfig() {
		
	}
	
	@Test 
    public void testValue_bool() throws Exception{
        SystemConfig config = new SystemConfig();
        //value == null
        assertException(config);
        
        //dataType = bool
        config = new SystemConfig();
        config.setDATATYPE(SystemConfig.DATATYPE_BOOL);
        config.setVALUE("1");
        validateValue(config);
        config.setVALUE("0");
        validateValue(config);

        config.setVALUE("true");
        validateValue(config);

        config.setVALUE("TrUe");
        validateValue(config);

        config.setVALUE("false");
        validateValue(config);

        config.setVALUE("FAlse");
        validateValue(config);
        
        config.setVALUE("2");
        assertException(config);

        config.setVALUE("abc");
        assertException(config);
    }
    
    @Test
    public void testValue_int() throws Exception{
      //dataType == int
        SystemConfig config = new SystemConfig();
        config = new SystemConfig();
        config.setDATATYPE(SystemConfig.DATATYPE_INT);
        config.setVALUE("abc");
        assertException(config);
        config.setVALUE("123a");
        assertException(config);
        config.setVALUE("123.45");
        assertException(config);
        config.setVALUE("0");
        validateValue(config);
        config.setVALUE("123");
        validateValue(config);
        config.setVALUE("-123");
        validateValue(config);
        
        config.setPARAM_OPTION("12341");
        config.setVALUE("3");
        assertException(config);

        
        config.setPARAM_OPTION("(2,5)");
        config.setVALUE("3");
        validateValue(config);
        
        config.setVALUE("2");
        assertException(config);
        
        config.setVALUE("5");
        assertException(config);
        
        config.setVALUE("8");
        assertException(config);
        
        config.setPARAM_OPTION("[2,5]");
        config.setVALUE("2");
        validateValue(config);
        
        config.setVALUE("5");
        validateValue(config);
        
        config.setPARAM_OPTION("(,)");
        config.setVALUE("-999999");
        assertException(config);
        
        config.setPARAM_OPTION("(,5]");
        config.setVALUE("-999999");
        validateValue(config);
        
        config.setPARAM_OPTION("(2,]");
        config.setVALUE("999999");
        validateValue(config);
    }
    
    @Test
    public void testValue_number() throws Exception{
        SystemConfig config = new SystemConfig();
        
        //dataType == number
        config = new SystemConfig();
        config.setDATATYPE(SystemConfig.DATATYPE_NUMBER);
        config.setVALUE("abc");
        assertException(config);
        config.setVALUE("123a");
        assertException(config);
  
        config.setVALUE("0");
        validateValue(config);
        config.setVALUE("123");
        validateValue(config);
        config.setVALUE("123.45");
        validateValue(config);
        
        config.setPARAM_OPTION("12341");
        config.setVALUE("3");
        assertException(config);
        
        config.setPARAM_OPTION("(2,5)");
        config.setVALUE("3");
        validateValue(config);
        
        config.setVALUE("2.01");
        validateValue(config);
        config.setVALUE("2.0");
        assertException(config);
        
        config.setVALUE("4.999");
        validateValue(config);
        
        config.setVALUE("5");
        assertException(config);
        
        config.setVALUE("8");
        assertException(config);
        
        config.setPARAM_OPTION("[2,5]");
        config.setVALUE("2.0");
        validateValue(config);
       
        config.setVALUE("2");
        validateValue(config);
        
        config.setVALUE("1.999");
        assertException(config);
        
        config.setVALUE("5");
        validateValue(config);
        
        config.setVALUE("5.01");
        assertException(config);
        
        config.setPARAM_OPTION("(,5.5]");
        config.setVALUE("5.4");
        validateValue(config);
        
        config.setVALUE("5.6");
        assertException(config);
        
        config.setPARAM_OPTION("(,5]");
        config.setVALUE("-999999");
        validateValue(config);
        
        config.setPARAM_OPTION("(2,]");
        config.setVALUE("999999");
        validateValue(config);
    }
    
    @Test
    public void testValue_string() throws Exception{
        SystemConfig config = new SystemConfig();
        
        Pattern p = Pattern.compile(".{5}");
        //System.out.println("p5:"+p.matcher("12345").matches());
        
        
        //dataType == string
        config = new SystemConfig();
        config.setDATATYPE(SystemConfig.DATATYPE_STRING);
        assertException(config);  //值不能为空
        
        config.setVALUE("");
        assertException(config);  //值不能为空
        
        config.setVALUE("xxa124qa");
        validateValue(config);
        
        config.setVALUE("阿斯顿飞龙卡及世界发达拉货时间可归纳  的发送旅客就说的 =341-i5042377032u4!@!#$#$%^%$*%^&(*^");
        validateValue(config);

        config.setPARAM_OPTION(".{5}");
        
        config.setVALUE("我是谁");
        assertException(config);
        
        config.setVALUE("123456");
        assertException(config);
        
        config.setVALUE("12345");
        validateValue(config);

        config.setPARAM_OPTION(".{1,5}");
        config.setVALUE("我是谁");
        validateValue(config);
        
        config.setPARAM_OPTION("\\d{1,5}");
        config.setVALUE("我是谁");
        assertException(config);
        
        config.setVALUE("888");
        validateValue(config);
        
        
    }
    
    private void assertException(SystemConfig config){
        Exception err=null;
        try {
            validateValue(config);
        }
        catch (Exception e) {
            //System.out.println(e.toString());
            err = e;
        }
        Assert.assertNotNull(err);
    }
    
    private void validateValue(SystemConfig config) throws Exception{
//        private static void validateValue(String dataType, String paramOption, String value) {
    	Method method = SystemConfigServiceImpl.class.getDeclaredMethod("validateValue", String.class, String.class, String.class);
    	method.setAccessible(true);
    	method.invoke(SystemConfigServiceImpl.class, config.getDATATYPE(), config.getPARAM_OPTION(), config.getVALUE());
        
    }
}

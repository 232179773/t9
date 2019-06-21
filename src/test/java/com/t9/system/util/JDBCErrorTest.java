package com.t9.system.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;

import org.junit.Test;

import com.t9.system.util.file.FileUtil;

@SuppressWarnings("unchecked")
public class JDBCErrorTest {
	String fileName="D:\\work\\微信文档\\其他\\20130414\\log\\perbank.log.35.wx1";
	Pattern pattern=Pattern.compile("^\\d{4}-\\d{2}-\\d{2}.*");
	@Test
	public void testPattern()throws Exception {
	
		BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(fileName)));
		String line=bufferedReader.readLine();
		StringBuffer buffer=new StringBuffer();
		int i=0;
		while(line!=null){
			if(line.contains("com.ecc.emp.jdbc.GetConnectionFailedException")){
				if(pattern.matcher(line).matches()){
					i++;
					buffer.append(line).append("\r\n");
				//	System.out.println(line);
				}
			}
			line=bufferedReader.readLine();
		}
		System.out.println(i);
		FileUtil.writeToFile("D:\\work\\微信文档\\其他\\20130414\\log\\aa.txt", buffer.toString());
	//	assertEquals(tmpStr, "1867172efc6545416d1543f5d783c9176507f5d8".toUpperCase());
	}
	

}

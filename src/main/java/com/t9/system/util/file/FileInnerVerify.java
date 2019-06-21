package com.t9.system.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileInnerVerify {

	public void jsFileVerify(String fileName) throws Exception{
		File file1=new File(fileName);
		File file2=new File(fileName);
		
		BufferedReader br = new BufferedReader(new FileReader(file1));
		String line;
		int i=1;
		StringBuilder strBuild=new StringBuilder();
		while ((line = br.readLine()) != null) {
			String tt=line.replaceAll("[^()<>{}]", "");
			strBuild.append(tt);
			
		}
		System.out.println(strBuild);
	}
	
	public void verifyDir(String dir){
		
	}
	public void getSoupFile(String[] lines){
		
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FileInnerVerify fileInnerVerify=new FileInnerVerify();
		fileInnerVerify.jsFileVerify("D:\\test\\perbank_test.war\\WEB-INF\\EMPJMXContext.xml");

	}

}

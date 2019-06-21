package com.t9.system.util.file;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import com.t9.system.util.FreeMarkerUtil;

public class HtmlFileReport {

	private static boolean flag=false;
	public static void printResultHtml(HashMap dataMap) throws Exception{        
		String fileName=FreeMarkerUtil.generateFile(dataMap);
		if(!flag){
	        Runtime.getRuntime().exec("explorer.exe "+fileName);
	        flag=true;
		}
	}
}

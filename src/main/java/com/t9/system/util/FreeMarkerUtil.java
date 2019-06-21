package com.t9.system.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("unchecked")
public class FreeMarkerUtil{
	private static String ftlDir="D:\\work\\javawork\\t9\\src\\main\\webapp\\WEB-INF\\fileDiffftl";
	private static String fileDir="D:\\test\\report";

	public static String generateFile(HashMap dataMap) throws Exception{
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(ftlDir));
		String templateFtl=(String)dataMap.get("templateFtl");
		Template t = cfg.getTemplate(templateFtl);	//

        String fileName="index.html";
        if(dataMap.get("fileName")!=null){
        	fileName=(String)dataMap.get("fileName");
        }
        fileName=fileDir+File.separatorChar+fileName;
        File file=new File(fileName);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
        t.process(dataMap, out);
        out.flush();    
        out.close(); 
        return fileName;
	}
	

}

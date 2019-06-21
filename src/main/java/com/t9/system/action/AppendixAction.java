package com.t9.system.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Appendix;
import com.t9.system.service.AppendixService;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "rawtypes", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class AppendixAction extends BaseAction {

	@Autowired
	private AppendixService appendixService;
	private Appendix appendix = new Appendix();
	
	private File inputfile;
	private String inputfileFileName;
	private String inputfileContentType; //文件类型

	
	//列表查询
	public String queryAppendixList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=appendixService.queryList(map);
		super.renderToOutput(list);
		return null;
	}
	
	//
	public String queryAppendixById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Appendix appendix=appendixService.queryAppendixById(id);
		T9Result result=new T9Result(appendix);
		super.renderToOutput(result);
		return null;
	}

	public String saveAppendix(){
		String ext=FilenameUtils.getExtension(inputfileFileName);
		String filePath="/upload/"+System.currentTimeMillis()+"."+ext;
		 String realpath = ServletActionContext.getServletContext().getRealPath(filePath);
		 File destFile = new File(realpath);
		 System.out.println(destFile.getAbsolutePath());
		try {
			FileUtils.copyFile(inputfile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		appendix.setFILE_NAME(inputfileFileName);
		appendix.setFILE_SIZE(""+inputfile.length());
		appendix.setCREATETIME(new Date());
		appendix.setFILE_URL(filePath);
		T9Result result=appendixService.saveAppendix(appendix);
		super.renderToOutput(result);
		return null;
	}
	public String updateAppendix(){
		
		T9Result result=appendixService.updateAppendix(appendix);

		super.renderToOutput(result);
		return null;
	}

	public String deleteAppendix() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=appendixService.deleteAppendix(ids);
		super.renderToOutput(result);
		return null;
	}


	public Object getModel() {
		return appendix;
	}

	public File getInputfile() {
		return inputfile;
	}

	public void setInputfile(File inputfile) {
		this.inputfile = inputfile;
	}

	public String getInputfileFileName() {
		return inputfileFileName;
	}

	public void setInputfileFileName(String inputfileFileName) {
		this.inputfileFileName = inputfileFileName;
	}

	public String getInputfileContentType() {
		return inputfileContentType;
	}

	public void setInputfileContentType(String inputfileContentType) {
		this.inputfileContentType = inputfileContentType;
	}
	

}

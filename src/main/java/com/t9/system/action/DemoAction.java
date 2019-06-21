package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Demo;
import com.t9.system.service.DemoService;
import com.t9.system.web.Logger;
import com.t9.system.web.LoggerModel;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "rawtypes", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class DemoAction extends BaseAction {

	@Autowired
	private DemoService demoService;
	private Demo demo = new Demo();
	//列表查询
	public String queryDemoList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		String urserName=map.get("USER_NAME");
		System.out.println(urserName);
		try {
			System.out.println(new String(urserName.getBytes("iso-8859-1"),"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map> list=demoService.queryList(map);
		super.renderToOutput(list);
		return null;
	}
	
	//
	public String queryDemoById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Demo demo=demoService.queryDemoById(id);
		T9Result result=new T9Result(demo);
		super.renderToOutput(result);
		return null;
	}
	
	public String checkUserName() {
		T9Result result=new T9Result(false,"用户名已存在");
		super.renderToOutput(result);
		return null;
	}
	

	public String checkUserName2() {
		T9Result result=new T9Result();
		super.renderToOutput(result);
		return null;
	}

	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息保存", logParam = "ID={ID}&USER_NAME={USER_NAME}")
	public String saveDemo(){
		demo.setSTAFF_TYPE("001");
		T9Result result=demoService.saveDemo(demo);
		super.renderToOutput(result);
		return null;
	}
	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息修改", logParam = "ID={ID}&USER_NAME={USER_NAME}")
	public String updateDemo(){
		demo.setSTAFF_TYPE("001");
		T9Result result=demoService.updateDemo(demo);

		super.renderToOutput(result);
		return null;
	}

	@Logger(model = LoggerModel.SYSTEM,logName = "用户信息删除", logParam = "ID={ID}")
	public String deleteDemo() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=demoService.deleteDemo(ids);
		super.renderToOutput(result);
		return null;
	}


	public Object getModel() {
		return demo;
	}
	

}

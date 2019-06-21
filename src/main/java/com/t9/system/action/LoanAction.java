package com.t9.system.action;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.http.RenRenHttpClient;
import com.t9.system.service.RenRenLoanServiceImpl;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "rawtypes", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class LoanAction extends BaseAction {

	@Autowired
	private RenRenLoanServiceImpl loanServiceImpl;

	
	public String saveAppendix(){
		T9Result result=new T9Result();
		RenRenHttpClient client=new RenRenHttpClient();
		super.renderToOutput(result);
		return null;
	}


	public Object getModel() {
		return null;
	}
}

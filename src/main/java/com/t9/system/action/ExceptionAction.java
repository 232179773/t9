package com.t9.system.action;

import org.apache.struts2.config.ParentPackage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.t9.system.util.LogUtil;
import com.t9.system.web.RequestContext;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9RuningTimeException;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class ExceptionAction extends BaseAction {

	@Override
	public String execute(){
		try{
		ActionContext act = ActionContext.getContext();
		ValueStack stack = act.getValueStack();

		//获得异常
		Throwable exception= (Throwable)stack.findValue("exception");

		String msg="系统错误，请稍后重试!";
		if(exception instanceof T9RuningTimeException){
			T9RuningTimeException bre = (T9RuningTimeException)exception;
			String errmessage=exception.getMessage();			
			msg=getText(errmessage,bre.getArgs());
		}else if(exception instanceof T9Exception){
			T9Exception bre = (T9Exception)exception;
			String errmessage=exception.getMessage();			
			msg=getText(errmessage,bre.getArgs());			
		}else if(exception instanceof NoSuchMethodException){
			ApplicationContext applicationContext=RequestContext.getContext().getWebApplicationContext();
			FastAction fastAction=(FastAction)applicationContext.getBean(FastAction.class);
			return fastAction.execute();
		}

		LogUtil.logException(exception);
		T9Result result=new T9Result(false,msg);
		super.renderToOutput(result);
		}catch(Throwable e){
			LogUtil.logException(e);
		}
		return null;
	}


	@Override
	public Object getModel() {
		return null;
	}
}

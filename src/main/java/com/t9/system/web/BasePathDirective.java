package com.t9.system.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@SuppressWarnings("rawtypes")
public class BasePathDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Writer out=env.getOut();
		String basePath="http://localhost:8080/t9/";
		RequestContext requestContext=RequestContext.getContext();
		if(requestContext!=null){
			HttpServletRequest request=requestContext.getRequest();
			String path = request.getContextPath();
			basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		}
		out.write(basePath);
	//	body.render(env.getOut());
	}

}

package org.apache.jsp.bussiness;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.t9.system.web.ServletUtils;
import com.t9.system.util.JsonUtil;
import java.util.Map;
import java.io.File;

public final class orderEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/frame/_include.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Map<String, String> map = ServletUtils.getMapByRequest(request);
	String jsonStr=JsonUtil.getJsonString(map);
	String url=request.getRequestURL().toString();

      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/js/jqueryTreeTable/css/jquery.treetable.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/js/jqueryTreeTable/css/jquery.treetable.theme.default.css\" />\r\n");
      out.write("    \r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/themes/default/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\"/>\r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/themes/css/core.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\"/>\r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/themes/css/print.css\" rel=\"stylesheet\" type=\"text/css\" media=\"print\"/>\r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/js/jbox/jbox.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/js/ztree/zTreeStyle.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-1.7.2.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery.cookie.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/xheditor/xheditor-1.2.1.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/xheditor/xheditor_lang/zh-cn.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/js/validator-0.2.0/jquery.validator.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("frame/js/validator-0.2.0/jquery.validator.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("frame/js/validator-0.2.0/local/zh_CN.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/dwz.core.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/dwz.stable.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/dwz.drag.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/T9.ui.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/PageDiv.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/T9.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-extend.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/My97DatePicker/WdatePicker.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jbox/jquery.jBox-2.3.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/ztree/jquery.ztree.all-3.5.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jqueryTreeTable/jquery.treetable.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-ui/ui/jquery.ui.core.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-ui/ui/jquery.ui.widget.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-ui/ui/jquery.ui.tabs.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/js/jquery-ui/themes/base/jquery.ui.all.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/js/jquery-ui/demos.css\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("frame/uploadify/css/uploadify.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\"/>\r\n");
      out.write("<!-- \r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/uploadify/scripts/jquery.uploadify.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/T9.combox.js\" type=\"text/javascript\"></script>\r\n");
      out.write(" -->\r\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery.query.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var urlParam=");
      out.print(jsonStr );
      out.write(";\r\n");
      out.write("urlParam.get=function(key){\r\n");
      out.write("\treturn $.query.get(key);\r\n");
      out.write("}\r\n");
      out.write("var base_path=\"");
      out.print(basePath );
      out.write("\";\r\n");
      out.write("$(function(){\r\n");
      out.write("\tinitUI(document);\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");

if(url.endsWith("jsp")){
	String jsUrl=url.substring(0,url.length()-1);
	String jsFielUrl=jsUrl.substring(basePath.length());
	
	String filePath=session.getServletContext().getRealPath(jsFielUrl);
	
	File file=new File(filePath);
	if(file.exists()){
		out.print("<script type='text/javascript' src='"+jsUrl+"'></script>");
	}
}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("$(function() {\r\n");
      out.write("\tif(urlParam.ID){\r\n");
      out.write("\t\tT9.post('service/orderManagement!getOrderById',{ID:urlParam.ID},function(result){\r\n");
      out.write("\t\t\tif(result.success){\r\n");
      out.write("\t\t\t\t$(\"#orderForm\").setData(result.obj);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tT9.error(result.message);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\t//set readonly fields\r\n");
      out.write("\t\t$('#pPAY_MANEY').hide();\r\n");
      out.write("\t\t$('#pTOTLE_PRICE').hide();\r\n");
      out.write("        $('#pORDER_STATE').hide();\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function saveData() {\r\n");
      out.write("\tif(!$(\"#orderForm\").valid()) {\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tvar param=$('#orderForm').serializeJSON();\r\n");
      out.write("\t\tvar url =  urlParam.ID ? 'service/orderManagement!editOrder' : 'service/orderManagement!addOrder?a=1';\r\n");
      out.write("\t\tT9.post(url, param, function(result){\r\n");
      out.write("\t\t\t    //{\"message\":\"database.error\",\"obj\":null,\"success\":false}\r\n");
      out.write("\t\t\t\tif(result.success){\r\n");
      out.write("\t\t\t\t\tT9.alert(\"保存成功！\");\r\n");
      out.write("\t\t\t\t\tT9.dialogClose();\r\n");
      out.write("\t\t\t\t\t$(\"#orderForm\").gridReload();\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tT9.error(result.message);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div class=\"pageContent\">\r\n");
      out.write("\t<form id=\"orderForm\" >\r\n");
      out.write("\t\t<input type=\"hidden\" id=\"ID\" name=\"ID\" maxlength=\"25\"/>\r\n");
      out.write("\t\t<div class=\"pageFormContent\" layoutH=\"56\">\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>送餐时间：</label>\r\n");
      out.write("\t\t\t\t<input class=\"my97Date required\" type=\"text\" id=\"ORDER_TIME\" name=\"ORDER_TIME\"   dateFmt=\"yyyy-MM-dd HH:mm:ss\" readonly=\"true\"/>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>联系人：</label>\r\n");
      out.write("\t\t\t\t<input  class=\"required\" type=\"text\" id=\"ORDER_ID\" name=\"ORDER_ID\" maxlength=\"25\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>联系电话：</label>\r\n");
      out.write("\t\t\t\t<input class=\"required\" type=\"text\" id=\"ORDER_PHONE\" name=\"ORDER_PHONE\" maxlength=\"100\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>地址：</label>\r\n");
      out.write("\t\t\t\t<input  class=\"required\" type=\"text\" id=\"ORDER_ADDRESS\" name=\"ORDER_ADDRESS\" maxlength=\"30\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p id=\"pTOTLE_PRICE\">\r\n");
      out.write("\t\t\t\t<label>金额：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"TOTLE_PRICE\" name=\"TOTLE_PRICE\" maxlength=\"30\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t <p id=\"pPAY_MANEY\">\r\n");
      out.write("\t\t\t\t<label>应付金额：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"PAY_MANEY\" name=\"PAY_MANEY\" maxlength=\"30\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p id=\"pORDER_STATE\">\r\n");
      out.write("\t\t\t\t<label>状态：</label>\r\n");
      out.write("\t\t\t\t<select class=\"combox required\" name=\"ORDER_STATE\" id=\"ORDER_STATE\" showNull=\"false\" \r\n");
      out.write("\t\t\t\tsefUrl=\"type=DICT_OPTION&DICT_CODE=ORDER_STATE\"  style=\"width:120px\">\t</select>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>备注：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"REMARK\" name=\"REMARK\" maxlength=\"30\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"formBar\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<!--<li><a class=\"buttonActive\" href=\"javascript:;\"><span>保存</span></a></li>-->\r\n");
      out.write("\t\t\t\t<li><div class=\"buttonActive\"><div class=\"buttonContent\"><button onclick=\"saveData()\">保存</button></div></div></li>\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" class=\"close\">取消</button></div></div>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

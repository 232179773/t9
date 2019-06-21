package org.apache.jsp.bussiness;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.t9.system.web.ServletUtils;
import com.t9.system.util.JsonUtil;
import java.util.Map;
import java.io.File;

public final class storeMenuEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\tT9.post('service/storeMenu!getMenuById',{ID:urlParam.ID},function(result){\r\n");
      out.write("\t\t\tif(result.success){\r\n");
      out.write("\t\t\t\t$(\"#storeMenuForm\").setData(result.obj);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//set readonly fields\r\n");
      out.write("\t\t$('#STORE_ID').attr(\"readonly\",\"readonly\");\r\n");
      out.write("\t\t$('#MENU_PICTURE').attr('tableId', urlParam.ID);\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tfunction saveData() {\r\n");
      out.write("\t\tif (!$(\"#storeMenuForm\").valid()) {\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tvar param = $('#storeMenuForm').serializeJSON();\r\n");
      out.write("\t\t\tvar url = urlParam.ID ? 'service/storeMenu!editMenu'\r\n");
      out.write("\t\t\t\t\t: 'service/storeMenu!addMenu';\r\n");
      out.write("\t\t\tT9.post(url, param, function(result) {\r\n");
      out.write("\t\t\t\t//{\"message\":\"database.error\",\"obj\":null,\"success\":false}\r\n");
      out.write("\t\t\t\tif (result.success) {\r\n");
      out.write("\t\t\t\t\tT9.alert(\"保存成功！\");\r\n");
      out.write("\t\t\t\t\tT9.dialogClose();\r\n");
      out.write("\t\t\t\t\t$(\"#storeMenuForm\").gridReload();\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tT9.error(result.message);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction uploadSuccess(fileId,fileUrl){\r\n");
      out.write("\t\t//alert(fileId+\",\"+fileUrl);\r\n");
      out.write("\t\t$('#pictView').html('<img width=\"200\",height=\"200\" src=\"'+T9.getFileAppendix(fileUrl)+'\"/>');\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div class=\"pageContent\">\r\n");
      out.write("\t<form id=\"storeMenuForm\">\r\n");
      out.write("\t\t<input type=\"hidden\" id=\"ID\" name=\"ID\" maxlength=\"25\"/>\r\n");
      out.write("\t\t<div class=\"pageFormContent\" layoutH=\"56\">\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>餐厅：</label>\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"STORE_ID\" name=\"STORE_ID\" maxlength=\"25\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>菜单名称：</label>\r\n");
      out.write("\t\t\t\t<input class=\"required\" type=\"text\" id=\"MENU_NAME\" name=\"MENU_NAME\" maxlength=\"25\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>图片：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<input id=\"MENU_PICTURE\" class=\"fileUpload\" type=\"file\" multi=\"false\" \r\n");
      out.write("\t\t\t\ttableName=\"TB_STORE_MENU\" tableId=\"1777\" fileType=\"PICTURE\" success=\"uploadSuccess(fileId,fileUrl)\"/>\r\n");
      out.write("\t\t\t\t<div id=\"pictView\"></div>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>单价：</label>\r\n");
      out.write("\t\t\t\t<input class=\"required\" type=\"text\" id=\"MENU_PRICE\" name=\"MENU_PRICE\" maxlength=\"100\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>菜式：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<select class=\"combox required\" name=\"MENU_STYLE\" id=\"MENU_STYLE\" showNull=\"false\" \r\n");
      out.write("\t\t\t\tsefUrl=\"type=DICT_OPTION&DICT_CODE=STOREMENU_STYLE\"  style=\"width:120px\">\t</select>\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>分类：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<select class=\"combox required\" name=\"MENU_CLASS\" id=\"MENU_CLASS\" showNull=\"false\" \r\n");
      out.write("\t\t\t\tsefUrl=\"type=DICT_OPTION&DICT_CODE=STOREMENU_CLASS\"  style=\"width:120px\">\t</select>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>口味：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<select class=\"combox required\" name=\"FLAVOUR\" id=\"FLAVOUR\" showNull=\"false\" \r\n");
      out.write("\t\t\t\tsefUrl=\"type=DICT_OPTION&DICT_CODE=STOREMENU_FLAVOUR\"  style=\"width:120px\">\t</select>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>状态：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<select class=\"combox required\" name=\"STORE_MENU_STATE\" id=\"STORE_MENU_STATE\" showNull=\"false\" \r\n");
      out.write("\t\t\t\tsefUrl=\"type=DICT_OPTION&DICT_CODE=STOREMENU_STATE\"  style=\"width:120px\">\t</select>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>特色度：</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<input class=\"required\" type=\"text\" id=\"SPECIAL_FLAG\" name=\"SPECIAL_FLAG\" value=\"0\" maxlength=\"10\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t    <p>\r\n");
      out.write("\t\t\t\t<label>推荐度：</label>\r\n");
      out.write("\t\t\t\t<input class=\"required\" type=\"text\" id=\"RECOMMEND_FLAG\" name=\"RECOMMEND_FLAG\" value=\"0\" maxlength=\"10\"/>\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t\t\r\n");
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

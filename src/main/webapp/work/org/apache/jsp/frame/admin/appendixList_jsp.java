package org.apache.jsp.frame.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.t9.system.web.ServletUtils;
import com.t9.system.util.JsonUtil;
import java.util.Map;
import java.io.File;

public final class appendixList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<SCRIPT type=text/javascript>\r\n");
      out.write("$(function(){\r\n");
      out.write("    $('#appendixPageDiv').frontgrid({\r\n");
      out.write("    \tcolumns:[\r\n");
      out.write("    \t   \t{header:'',ctrl:'checkbox',width:'10%',key:\"ID\"},\r\n");
      out.write("    \t    {header:'表名',key:\"TABLE_NAME\",width:'10%'},\r\n");
      out.write("    \t    {header:'记录ID',key:\"DATA_ID\",width:'10%'},\r\n");
      out.write("    \t    {header:'文件名',key:\"FILE_NAME\",width:'10%'},\r\n");
      out.write("    \t    {header:'文件大小',key:\"FILE_SIZE\",width:'10%'},\r\n");
      out.write("    \t    {header:'创建时间',key:\"CREATETIME\",width:'30%'}\r\n");
      out.write("    \t],\r\n");
      out.write("    \tbuttons:[\r\n");
      out.write("    \t   {name:\"查询\",bclass: 'query', onpress : queryRole},\r\n");
      out.write("    \t   {name:\"删除\",bclass: 'delete', onpress : deleteRole,checkedRow:'multiple'}\r\n");
      out.write("    \t],\r\n");
      out.write("   // \tonSuccess:dataLoadBack,\r\n");
      out.write("    \turl : 'service/appendix!queryAppendixList'\r\n");
      out.write("    //\tautoload:false,\r\n");
      out.write("    //\tshowTotal:false,\r\n");
      out.write("   // \tdefaultPageSize:5\r\n");
      out.write("    });\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function queryRole(){\r\n");
      out.write("\tvar param=$(\"#appendixQueryForm\").serializeJSON();\r\n");
      out.write("\t$(\"#appendixPageDiv\").gridOptions(param);\r\n");
      out.write("\t$(\"#appendixPageDiv\").gridReload();\r\n");
      out.write("}\r\n");
      out.write("function deleteRole(value,record){\r\n");
      out.write("\tT9.confirm(\"确认要删除选中的信息吗?\",function(){\r\n");
      out.write("\t\tvar param={ID:value};\r\n");
      out.write("\t\tT9.post('service/appendix!deleteAppendix',param,function(result){\r\n");
      out.write("\t\t\t\tif(result){\r\n");
      out.write("\t\t\t\t\tT9.alert(\"删除成功\");\r\n");
      out.write("\t\t\t\t\t$(\"#appendixPageDiv\").gridReload();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("<div class=\"pageHeader\">\r\n");
      out.write("\t<form id=\"appendixQueryForm\">\r\n");
      out.write("\t<div class=\"searchBar\">\r\n");
      out.write("\t\t<table class=\"searchContent\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t表名:<input type=\"text\" name=\"TABLE_NAME\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"appendixPageDiv\" class=\"pageContent\"></div>\r\n");
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

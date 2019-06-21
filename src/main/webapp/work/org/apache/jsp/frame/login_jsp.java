package org.apache.jsp.frame;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("\n");

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-1.7.2.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/T9.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.print(basePath );
      out.write("frame/js/jquery-extend.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("<title>风险管理平台</title>\n");
      out.write("<link href=\"themes/css/login.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("var base_path=\"");
      out.print(basePath );
      out.write("\";\n");
      out.write("\n");
      out.write("if (window.top !== window.self) { \n");
      out.write("\twindow.top.location.href=window.location.href;\n");
      out.write("}\n");
      out.write("function login(){\n");
      out.write("\tvar param=$('#logonForm').serializeJSON();\n");
      out.write("\tT9.post('/service/login!userLogin',param,function(result){\n");
      out.write("\t\tif(result.success){\n");
      out.write("\t\t\tT9.navigate('/service/login!mainPage');\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\tT9.alert(result.message);\n");
      out.write("\t\t\t$('#imgCodeSpan').setCodeImage();\n");
      out.write("\t\t\t$('#verifyCode').val('');\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("}\n");
      out.write("$(function(){\n");
      out.write("//\t$('#imgCodeSpan').setCodeImage();\n");
      out.write("})\n");
      out.write("</script>\n");
      out.write("<body>\n");
      out.write("\t<div id=\"login\">\n");
      out.write("\t\t<div id=\"login_header\">\n");
      out.write("\t\t\t<h1 class=\"login_logo\">\n");
      out.write("\t\t\t\t<a href=\"http://demo.dwzjs.com\"><img src=\"themes/default/images/login_logo.gif\" /></a>\n");
      out.write("\t\t\t</h1>\n");
      out.write("\t\t\t<div class=\"login_headerContent\">\n");
      out.write("\t\t\t\t<div class=\"navList\">\n");
      out.write("\t\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\">设为首页</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a href=\"http://bbs.dwzjs.com\">反馈</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a href=\"doc/dwz-user-guide.pdf\" target=\"_blank\">帮助</a></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<h2 class=\"login_title\"><img src=\"themes/default/images/login_title.png\" /></h2>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"login_content\">\n");
      out.write("\t\t\t<div class=\"loginForm\">\n");
      out.write("\t\t\t\t<form id=\"logonForm\" method=\"post\">\n");
      out.write("\t\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t\t<label>用户名：</label>\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" id=\"LOGIN_CODE\" name=\"LOGIN_CODE\" size=\"20\" class=\"login_input\" value=\"superadmin\"/>\n");
      out.write("\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t\t<label>密码：</label>\n");
      out.write("\t\t\t\t\t\t<input type=\"password\" id=\"PASSWORD\" name=\"PASSWORD\" size=\"20\" class=\"login_input\"  value=\"123456\"/>\n");
      out.write("\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t<p style=\"display:none\">\n");
      out.write("\t\t\t\t\t\t<label>验证码：</label>\n");
      out.write("\t\t\t\t\t\t<input class=\"code\" id=\"verifyCode\" name =\"verifyCode\" type=\"text\" size=\"5\" />\n");
      out.write("\t\t\t\t\t\t<span id=\"imgCodeSpan\"></span>\n");
      out.write("\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t<div class=\"login_bar\">\n");
      out.write("\t\t\t\t\t\t<input class=\"sub\" onclick=\"login()\" type=\"button\" value=\" \" />\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"login_banner\"><img src=\"themes/default/images/login_banner.jpg\" /></div>\n");
      out.write("\t\t\t<div class=\"login_main\">\n");
      out.write("\t\t\t\t<ul class=\"helpList\">\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">下载驱动程序</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">如何安装密钥驱动程序？</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">忘记密码怎么办？</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">为什么登录失败？</a></li>\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t\t<div class=\"login_inner\">\n");
      out.write("\t\t\t\t\t<p>您可以使用 网易网盘 ，随时存，随地取</p>\n");
      out.write("\t\t\t\t\t<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>\n");
      out.write("\t\t\t\t\t<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div id=\"login_footer\">\n");
      out.write("\t\t\tCopyright &copy; 2009 www.dwzjs.com Inc. All Rights Reserved.\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("</html>");
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

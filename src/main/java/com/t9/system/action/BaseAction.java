package com.t9.system.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.t9.system.service.DataCache;
import com.t9.system.util.ExportToExcel;
import com.t9.system.util.JsonUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.QueryResult;
import com.t9.system.web.RequestContext;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9RuningTimeException;


/**
 * 
 * 功能描述：action基类
 *
 * @author husq
 * @since 1.0
 * create on: 6/28/2013 
 *
 */
@SuppressWarnings({ "serial", "unchecked","rawtypes" })
public abstract class BaseAction extends ActionSupport implements ModelDriven{


	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DataCache dataCache;
	/**
	 * 获取HttpServletRequest对象
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		ActionContext ctx = ActionContext.getContext();        
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST); 
		return request;
	}

	/***
	 * 获取HttpServletResponse对象
	 * @return
	 * @throws Exception
	 */
	public HttpServletResponse getResponse(){
		ActionContext ctx = ActionContext.getContext();        
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
		return response;
	}
	
	/***
	 * 获取HttpSession对象
	 * @return
	 * @throws Exception
	 */
	public HttpSession getSession() {
		ActionContext ctx = ActionContext.getContext();        
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST); 
		HttpSession session = request.getSession();
		return session;
	}
	
	
	/**
	 * 功能描述：返回参数到界面
	 * @param result 返回对象
	 */
	public void renderToOutput(List list)  {
		String renderCols=getRequest().getParameter("renderCols");
		if(StringUtil.isNotEmpty(renderCols)){
			String[] dcols=renderCols.split(";");//COL_USE:dict:FAST_COL_USE;render:table:FAST_COL_USE
			for (int i = 0; i < dcols.length; i++) {
				String[] ds=dcols[i].split(":");
				//循环处理查询结果集
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					HashMap dataMap=(HashMap)iterator.next();
					String colValue="";
					if(ds[1].equals("dict")){
						colValue=DataCache.getDictOptionName(ds[2], (String)dataMap.get(ds[0]));
					}
					if(ds[1].equals("table")){
						colValue=dataCache.getEntityName(ds[2], (String)dataMap.get(ds[0]));
					}
					if(ds[1].equals("sql")){
						String sql=ds[2];
						String id=(String)dataMap.get(ds[0]);
						
						String transSql=transSql(sql,id,dataMap);
						colValue=dataCache.getSQLValue(transSql);
					}
					dataMap.put("NAME_"+ds[0], colValue);
				}
			}
		}
		if("true".equals(getRequest().getParameter("exportData"))){
			outToExcel(list);
			return;
		}
		
		initResponseHeader(ServletUtils.JSON_TYPE);
		RequestContext requestContext=RequestContext.getContext();
		QueryResult result=new QueryResult(list, requestContext.getPageInfo());
		String jsonStr=JsonUtil.getJsonString(result);
		PrintWriter out;
		try {
			out = getResponse().getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new T9RuningTimeException("servlet error");
		}
	}
	
	private String transSql(String sql,String id,HashMap dataMap){
		sql=sql.toUpperCase().trim().replaceAll(" +"," ");
		String[] fromCols=sql.substring(6, sql.indexOf("FROM")).split(",");
		if(fromCols.length!=2)
			return null;
		if(fromCols[0].trim().split(" ").length!=2)
			return null;
		String idCol=fromCols[0].trim().split(" ")[0];

		String sufStr=sql.substring(sql.indexOf("FROM"));
		if(sufStr.indexOf("ORDER BY")>0){
			sufStr=sufStr.substring(0, sufStr.indexOf("ORDER BY"));
		}
		StringBuilder result=new StringBuilder("SELECT ");
		result.append(fromCols[1]).append(sufStr);

		if(result.indexOf("WHERE")>0){
			result.append(" AND ").append(idCol).append(" = '").append(id).append("'");
		}
		return StringUtil.getRightString(result.toString(), dataMap);
	}
	
	/**
	 * 功能描述：返回参数到界面
	 * @param result 返回对象
	 */
	public void renderToOutput(T9Result result){
		initResponseHeader(ServletUtils.JSON_TYPE);
		String jsonStr=JsonUtil.getJsonString(result);
		PrintWriter out;
		try {
			out = getResponse().getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new T9RuningTimeException("servlet error");
		}
	}
	/**
	 * 功能描述：返回二进制数据到界面
	 * @param result 返回对象
	 */
	public void renderToOutput(byte[] bytes) {
		OutputStream outputStream ;
		try {
			 outputStream =getResponse().getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new T9RuningTimeException("servlet error");
		}
	}
	
	/**
	 * 
	 * 功能描述：
	 * 
	 * @param title		导出的EXCEL文件中的sheet名称
	 * @param headers	导出列名数组
	 * @param data		待导出的数据集合
	 * @param keys		列名数组(非必须参数,该参数不为空时按该数组中的列名顺序取map中的值)
	 */
	public void outToExcel(String title, String[] headers, List list, String[] keys){
		try{
			String result = ExportToExcel.exportToCvs(title, headers, list, keys);
			if (getRequest().getHeader("User-Agent").indexOf("MSIE") != -1) 
				title = URLEncoder.encode(title + (".csv"), "UTF-8");
			else
				title = new String((title + ".csv").getBytes("UTF-8"), "ISO8859-1");	
				
			getResponse().setHeader("Content-Disposition", "attachment; filename=" + title);
			getResponse().setContentType("application/csv;charset=GBK"); 
			PrintWriter out = getResponse().getWriter();
			out.write(result);
			out.flush();
			out.close();
			this.getResponse().flushBuffer();
		}catch (IOException e) {
			throw new T9RuningTimeException("servlet error");
		}
	}

//	public void outToExcel(String title, String[] headers, List list, String[] keys)  throws Exception {
//		HSSFWorkbook workbook = ExportToExcel.export(title, headers, list, keys);
//		if (getRequest().getHeader("User-Agent").indexOf("MSIE") != -1) {
//			getResponse().setHeader(
//					"Content-disposition",
//					"attachment;filename="
//							+ URLEncoder.encode(title + (".xls"), "UTF-8"));
//
//		} else {
//			getResponse().setHeader(
//					"Content-disposition",
//					"attachment;filename="
//							+ new String((title + ".xls").getBytes("UTF-8"),
//									"ISO8859-1"));
//		}
//
//		getResponse().setContentType("application/force-download");
//		OutputStream fos = getResponse().getOutputStream();
//		workbook.write(fos);
//		this.getResponse().flushBuffer();
//	}
	/**
	 * 功能描述：
	 * @param list		待导出的数据集合
	 * @param map		导出参数
	 */
	public void outToExcel(List list){
		String title=getRequest().getParameter("title");
		if(title==null){
			title="expostList";
		}
		if(getRequest().getParameter("headers") != null){
			String[] headers=((String)getRequest().getParameter("headers")).split(",");
			String[] keys=((String)getRequest().getParameter("keys")).split(",");
			this.outToExcel(title, headers, list, keys);
		}
	}

	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "GBK";
	private static final boolean DEFAULT_NOCACHE = true;
	
	/**
	 * 设置contentType,headers.
	 */
	private static HttpServletResponse initResponseHeader(final String contentType, final String... headers) {
		
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtil.substringBefore(header, ":");
			String headerValue = StringUtil.substringAfter(header, ":");

			if (StringUtil.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtil.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}
}

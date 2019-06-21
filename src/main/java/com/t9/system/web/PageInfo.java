package com.t9.system.web;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 分页信息
 * @author husq
 */
public final class PageInfo {
	
	public PageInfo(){}
	
	public PageInfo(int page, int pagesize){
		this.page = page;
		this.pagesize = pagesize;
	}
	
	private int page;
	
	private int pagesize;
	
	private long count;
	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}

	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	@JsonIgnore
	public int getFirstNum() {
		return (getPage()-1)*getPagesize();
	}

	@JsonIgnore
	public int getMaxNum() {
		return getPagesize();
	}
	
}

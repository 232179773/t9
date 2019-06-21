/**
 * 
 */
package com.t9.system.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author husq
 *
 */
@SuppressWarnings("rawtypes")
public class QueryResult {
	
	public QueryResult(List gridData){
		this.gridData = gridData.toArray();
	}
	
	public QueryResult(){};
	
	public QueryResult(Collection collection, PageInfo pageInfo){
		this.gridData = collection.toArray();
		this.pageInfo = pageInfo;
	}
	
	private Object[] gridData;
	
	private PageInfo pageInfo;

	public Object[] getGridData() {
		return gridData;
	}

	public void setGridData(Object[] gridData) {
		this.gridData = gridData;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	@JsonIgnore
	public List<?> getListData(){
		return Arrays.asList(getGridData());
	}

}

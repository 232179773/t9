package com.t9.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author t9
 * @Description:字典类
 * @date:2013-7-9
 * @version:
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_DICT")
public class Dict extends BaseDomain{
	private String DICT_CODE;
	private String DICT_NAME;
	private String DICT_TYPE;
	private String REMARK;
	public String getDICT_CODE() {
		return DICT_CODE;
	}
	public void setDICT_CODE(String dICT_CODE) {
		DICT_CODE = dICT_CODE;
	}
	public String getDICT_NAME() {
		return DICT_NAME;
	}
	public void setDICT_NAME(String dICT_NAME) {
		DICT_NAME = dICT_NAME;
	}
	public String getDICT_TYPE() {
		return DICT_TYPE;
	}
	public void setDICT_TYPE(String dICT_TYPE) {
		DICT_TYPE = dICT_TYPE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	
}

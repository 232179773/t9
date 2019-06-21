package com.t9.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author t9
 * @Description:字典项
 * @date:2013-7-10
 * @version:
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_DICT_OPTION")
public class DictOption extends BaseDomain{
	private String DICT_CODE;
	private String OPTION_VALUE;
	private String OPTION_NAME;
	private Long ORDER_NO; 
	private String ISDISPLAY;
	private String IS_MODIFI;
	public String getDICT_CODE() {
		return DICT_CODE;
	}
	public void setDICT_CODE(String dICT_CODE) {
		DICT_CODE = dICT_CODE;
	}
	public String getOPTION_VALUE() {
		return OPTION_VALUE;
	}
	public void setOPTION_VALUE(String oPTION_VALUE) {
		OPTION_VALUE = oPTION_VALUE;
	}
	public String getOPTION_NAME() {
		return OPTION_NAME;
	}
	public void setOPTION_NAME(String oPTION_NAME) {
		OPTION_NAME = oPTION_NAME;
	}
	public Long getORDER_NO() {
		return ORDER_NO;
	}
	public void setORDER_NO(Long oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}
	public String getISDISPLAY() {
		return ISDISPLAY;
	}
	public void setISDISPLAY(String iSDISPLAY) {
		ISDISPLAY = iSDISPLAY;
	}
	public String getIS_MODIFI() {
		return IS_MODIFI;
	}
	public void setIS_MODIFI(String iS_MODIFI) {
		IS_MODIFI = iS_MODIFI;
	}
	
	
}

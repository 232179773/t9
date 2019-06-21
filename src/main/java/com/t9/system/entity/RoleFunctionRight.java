package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_ROLEFUNCTIONRIGHT")
public class RoleFunctionRight extends BaseDomain {
	private String ROLE_ID;
	private String BUTTON_ID;
	private String MENU_ID;
	private Date CREATETIME;//保存时间
	private String CREATER;//保存人
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getBUTTON_ID() {
		return BUTTON_ID;
	}
	public void setBUTTON_ID(String bUTTON_ID) {
		BUTTON_ID = bUTTON_ID;
	}
	public String getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(String mENU_ID) {
		MENU_ID = mENU_ID;
	}
	public Date getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}
	
	public String getCREATER() {
		return CREATER;
	}
	public void setCREATER(String cREATER) {
		CREATER = cREATER;
	}
	
}

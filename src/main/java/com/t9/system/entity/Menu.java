/* 
 * Powered By T9
 */

package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_MENU")
public class Menu extends BaseDomain {

	private String CODE;
	private String NAME;
	private String URL;
	private Long MENU_ORDER;
	private String PARENT_MENU_ID;
	private Long ISUSED;
	private String REMARK;
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public Long getMENU_ORDER() {
		return MENU_ORDER;
	}
	public void setMENU_ORDER(Long mENU_ORDER) {
		MENU_ORDER = mENU_ORDER;
	}
	public String getPARENT_MENU_ID() {
		return PARENT_MENU_ID;
	}
	public void setPARENT_MENU_ID(String pARENT_MENU_ID) {
		PARENT_MENU_ID = pARENT_MENU_ID;
	}
	public Long getISUSED() {
		return ISUSED;
	}
	public void setISUSED(Long iSUSED) {
		ISUSED = iSUSED;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}
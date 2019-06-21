package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * null
 * 
 * @author generate auto
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_WX_ACCOUNT")
public class WxAcct extends BaseDomain {
	private String ACCT_ID;
	private String OPEN_ID;
	private String NICK_NAME;
	private String SEX;
	private String CITY;
	private String COUNTRY;
	private String PROVINCE;
	private String LANGUAGE;
	private String HEADIMGURL;
	private String SUBSCRIBE_TIME;
	public String getACCT_ID() {
		return ACCT_ID;
	}
	public void setACCT_ID(String aCCTID) {
		ACCT_ID = aCCTID;
	}
	public String getOPEN_ID() {
		return OPEN_ID;
	}
	public void setOPEN_ID(String oPENID) {
		OPEN_ID = oPENID;
	}
	public String getNICK_NAME() {
		return NICK_NAME;
	}
	public void setNICK_NAME(String nICKNAME) {
		NICK_NAME = nICKNAME;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public String getPROVINCE() {
		return PROVINCE;
	}
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	public String getLANGUAGE() {
		return LANGUAGE;
	}
	public void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}
	public String getHEADIMGURL() {
		return HEADIMGURL;
	}
	public void setHEADIMGURL(String hEADIMGURL) {
		HEADIMGURL = hEADIMGURL;
	}
	public String getSUBSCRIBE_TIME() {
		return SUBSCRIBE_TIME;
	}
	public void setSUBSCRIBE_TIME(String sUBSCRIBETIME) {
		SUBSCRIBE_TIME = sUBSCRIBETIME;
	}

}
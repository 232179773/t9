package com.t9.p2p.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.t9.system.entity.BaseDomain;

/**
 * null
 * 
 * @author generate auto
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_P2P_ACCOUNT")
public class Account extends BaseDomain {

	private String SITE_NAME;
	private String SITE_URL;
	private String SITE_ACCOUNT;
	private String NICK_NAME;
	private String EMAIL;
	private String MOBILE_PHONE;
	private String LOGIN_PASSWORD;
	private String PAY_PASSWORD;
	private String IS_VIP;
	private Date VIP_EXPIRE;
	private String PAY_TYPE;
	private String IS_UPLOAD_IDCARD;
	private Date REGISTER_TIME;
	private String BANK_NAME;
	private String PLAT;

	public String getPLAT() {
		return PLAT;
	}

	public void setPLAT(String pLAT) {
		PLAT = pLAT;
	}

	public String getSITE_NAME() {
		return SITE_NAME;
	}

	public void setSITE_NAME(String sITE_NAME) {
		SITE_NAME = sITE_NAME;
	}

	public String getSITE_URL() {
		return SITE_URL;
	}

	public void setSITE_URL(String sITE_URL) {
		SITE_URL = sITE_URL;
	}

	public String getSITE_ACCOUNT() {
		return SITE_ACCOUNT;
	}

	public void setSITE_ACCOUNT(String sITE_ACCOUNT) {
		SITE_ACCOUNT = sITE_ACCOUNT;
	}

	public String getNICK_NAME() {
		return NICK_NAME;
	}

	public void setNICK_NAME(String nICK_NAME) {
		NICK_NAME = nICK_NAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getMOBILE_PHONE() {
		return MOBILE_PHONE;
	}

	public void setMOBILE_PHONE(String mOBILE_PHONE) {
		MOBILE_PHONE = mOBILE_PHONE;
	}

	public String getLOGIN_PASSWORD() {
		return LOGIN_PASSWORD;
	}

	public void setLOGIN_PASSWORD(String lOGIN_PASSWORD) {
		LOGIN_PASSWORD = lOGIN_PASSWORD;
	}

	public String getPAY_PASSWORD() {
		return PAY_PASSWORD;
	}

	public void setPAY_PASSWORD(String pAY_PASSWORD) {
		PAY_PASSWORD = pAY_PASSWORD;
	}

	public String getIS_VIP() {
		return IS_VIP;
	}

	public void setIS_VIP(String iS_VIP) {
		IS_VIP = iS_VIP;
	}

	public Date getVIP_EXPIRE() {
		return VIP_EXPIRE;
	}

	public void setVIP_EXPIRE(Date vIP_EXPIRE) {
		VIP_EXPIRE = vIP_EXPIRE;
	}

	public String getPAY_TYPE() {
		return PAY_TYPE;
	}

	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}

	public String getIS_UPLOAD_IDCARD() {
		return IS_UPLOAD_IDCARD;
	}

	public void setIS_UPLOAD_IDCARD(String iS_UPLOAD_IDCARD) {
		IS_UPLOAD_IDCARD = iS_UPLOAD_IDCARD;
	}

	public Date getREGISTER_TIME() {
		return REGISTER_TIME;
	}

	public void setREGISTER_TIME(Date rEGISTER_TIME) {
		REGISTER_TIME = rEGISTER_TIME;
	}

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

}
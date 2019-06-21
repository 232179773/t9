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
@Table(name = "TB_P2P_MONEYLOG")
public class MoneyLog extends BaseDomain {

	private String SITE_NAME;
	private String LOG_TYPE;
	private Double MONEY;
	private Date DUE_TIME;
	private String PAY_BANK;
	private String PAY_CARD;
	private String PAY_TYPE;
	private String PAY_ORDERNO;
	private String SECOND_PAYTYPE;
	private String SECOND_ORDERNO;
	private String IS_SUCCESS;
	public String getSITE_NAME() {
		return SITE_NAME;
	}
	public void setSITE_NAME(String sITE_NAME) {
		SITE_NAME = sITE_NAME;
	}
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOG_TYPE) {
		LOG_TYPE = lOG_TYPE;
	}
	public Double getMONEY() {
		return MONEY;
	}
	public void setMONEY(Double mONEY) {
		MONEY = mONEY;
	}
	public Date getDUE_TIME() {
		return DUE_TIME;
	}
	public void setDUE_TIME(Date dUE_TIME) {
		DUE_TIME = dUE_TIME;
	}
	public String getPAY_BANK() {
		return PAY_BANK;
	}
	public void setPAY_BANK(String pAY_BANK) {
		PAY_BANK = pAY_BANK;
	}
	public String getPAY_CARD() {
		return PAY_CARD;
	}
	public void setPAY_CARD(String pAY_CARD) {
		PAY_CARD = pAY_CARD;
	}
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}
	public String getPAY_ORDERNO() {
		return PAY_ORDERNO;
	}
	public void setPAY_ORDERNO(String pAY_ORDERNO) {
		PAY_ORDERNO = pAY_ORDERNO;
	}
	public String getSECOND_PAYTYPE() {
		return SECOND_PAYTYPE;
	}
	public void setSECOND_PAYTYPE(String sECOND_PAYTYPE) {
		SECOND_PAYTYPE = sECOND_PAYTYPE;
	}
	public String getSECOND_ORDERNO() {
		return SECOND_ORDERNO;
	}
	public void setSECOND_ORDERNO(String sECOND_ORDERNO) {
		SECOND_ORDERNO = sECOND_ORDERNO;
	}
	public String getIS_SUCCESS() {
		return IS_SUCCESS;
	}
	public void setIS_SUCCESS(String iS_SUCCESS) {
		IS_SUCCESS = iS_SUCCESS;
	}
	
}
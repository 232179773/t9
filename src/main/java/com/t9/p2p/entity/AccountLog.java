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
@Table(name = "TB_P2P_ACCOUNTLOG")
public class AccountLog extends BaseDomain {

	private String SITE_NAME;
	private String LOG_TYPE;
	private Double MONEY;
	private Date DUE_TIME;

	private String TENDER_TYPE;
	private String TENDER_DURATION;
	private String REPAMENT_TYPE;// '1:到期还本付息，2：等额本息，3：等额本金，4：按月付息，到期还本',
	private String BID_TITLE;
	private String BID_URL;
	private String GATHER_STATE;//0:未收款，1：已收款
	private String REMARK;
	

	private String INTEREST_RATE;
	private String REWARD;
	private String INTEREST_COST;
	private Double COLLECTION_MONEY;
	private Double EARN_MONEY;
	private Date REPAYMENT_TIME;
	
	
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
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
	public String getTENDER_TYPE() {
		return TENDER_TYPE;
	}
	public void setTENDER_TYPE(String tENDER_TYPE) {
		TENDER_TYPE = tENDER_TYPE;
	}
	public String getTENDER_DURATION() {
		return TENDER_DURATION;
	}
	public void setTENDER_DURATION(String tENDER_DURATION) {
		TENDER_DURATION = tENDER_DURATION;
	}
	public String getREPAMENT_TYPE() {
		return REPAMENT_TYPE;
	}
	public void setREPAMENT_TYPE(String rEPAMENT_TYPE) {
		REPAMENT_TYPE = rEPAMENT_TYPE;
	}
	public String getBID_TITLE() {
		return BID_TITLE;
	}
	public void setBID_TITLE(String bID_TITLE) {
		BID_TITLE = bID_TITLE;
	}
	public String getBID_URL() {
		return BID_URL;
	}
	public void setBID_URL(String bID_URL) {
		BID_URL = bID_URL;
	}
	public String getGATHER_STATE() {
		return GATHER_STATE;
	}
	public void setGATHER_STATE(String gATHER_STATE) {
		GATHER_STATE = gATHER_STATE;
	}
	public String getINTEREST_RATE() {
		return INTEREST_RATE;
	}
	public void setINTEREST_RATE(String iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
	public String getREWARD() {
		return REWARD;
	}
	public void setREWARD(String rEWARD) {
		REWARD = rEWARD;
	}
	public String getINTEREST_COST() {
		return INTEREST_COST;
	}
	public void setINTEREST_COST(String iNTEREST_COST) {
		INTEREST_COST = iNTEREST_COST;
	}
	
	public Date getREPAYMENT_TIME() {
		return REPAYMENT_TIME;
	}
	public void setREPAYMENT_TIME(Date rEPAYMENT_TIME) {
		REPAYMENT_TIME = rEPAYMENT_TIME;
	}
	public Double getCOLLECTION_MONEY() {
		return COLLECTION_MONEY;
	}
	public void setCOLLECTION_MONEY(Double cOLLECTION_MONEY) {
		COLLECTION_MONEY = cOLLECTION_MONEY;
	}
	public Double getEARN_MONEY() {
		return EARN_MONEY;
	}
	public void setEARN_MONEY(Double eARN_MONEY) {
		EARN_MONEY = eARN_MONEY;
	}
	  
	
}
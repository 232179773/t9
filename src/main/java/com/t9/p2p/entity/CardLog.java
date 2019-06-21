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
@Table(name = "TB_P2P_CARDLOG")
public class CardLog extends BaseDomain {
	private String BANK_NAME;
	private String CARD_NO;
	private Date HAPPEN_TIME;
	private String HAPPEN_TYPE;
	private String REMARK;
	private Double HAPPEN_MONEY;

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

	public String getCARD_NO() {
		return CARD_NO;
	}

	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}

	public Date getHAPPEN_TIME() {
		return HAPPEN_TIME;
	}

	public void setHAPPEN_TIME(Date hAPPEN_TIME) {
		HAPPEN_TIME = hAPPEN_TIME;
	}

	public String getHAPPEN_TYPE() {
		return HAPPEN_TYPE;
	}

	public void setHAPPEN_TYPE(String hAPPEN_TYPE) {
		HAPPEN_TYPE = hAPPEN_TYPE;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public Double getHAPPEN_MONEY() {
		return HAPPEN_MONEY;
	}

	public void setHAPPEN_MONEY(Double hAPPEN_MONEY) {
		HAPPEN_MONEY = hAPPEN_MONEY;
	}

}
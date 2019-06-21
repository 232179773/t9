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
@Table(name = "TB_RENREN_STAT")
public class RenRenStat extends BaseDomain {

	private Double AGGREGATE_AMOUNT;
	private Long SUM_TIMES;
	private Double SUM_RATE;
	private Date STAT_TIME;
	public Long getSUM_TIMES() {
		return SUM_TIMES;
	}
	public void setSUM_TIMES(Long sUMTIMES) {
		SUM_TIMES = sUMTIMES;
	}
	public Date getSTAT_TIME() {
		return STAT_TIME;
	}
	public void setSTAT_TIME(Date sTATTIME) {
		STAT_TIME = sTATTIME;
	}
	public Double getAGGREGATE_AMOUNT() {
		return AGGREGATE_AMOUNT;
	}
	public void setAGGREGATE_AMOUNT(Double aGGREGATEAMOUNT) {
		AGGREGATE_AMOUNT = aGGREGATEAMOUNT;
	}
	public Double getSUM_RATE() {
		return SUM_RATE;
	}
	public void setSUM_RATE(Double sUMRATE) {
		SUM_RATE = sUMRATE;
	}
	
	
}
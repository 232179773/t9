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
@Table(name = "TB_RENREN_LOAN")
public class RenRenLoan extends BaseDomain {

	private Long LOAN_ID;
	private String TITLE;
	private String PICTURE;
	private Double AMOUNT;
	private Double INTEREST;
	private Long MONTHS;
	private String LOAN_TYPE;
	private String STATUS;
	private String ALLOW_ACCESS;
	private String UTM_SOURCE;
	private Date OPEN_TIME;
	private Date START_TIME;
	private Date READY_TIME;
	private Date PASS_TIME;
	private Double SURPLUS_AMOUNT;
	private Double FINISHED_RATIO;
	private String repaidByGuarantor;
	private String borrowerId;
	private String forbidComment;
	private String nickName;
	private String borrowerLevel;
	private String displayLoanType;
	private String currentIsRepaid;
	private String amountPerShare;
	private String oldLoan;
	private String interestPerShare;
	private String principal;
	private String overDued;
	private String productId;
	public Long getLOAN_ID() {
		return LOAN_ID;
	}
	public void setLOAN_ID(Long lOANID) {
		LOAN_ID = lOANID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getPICTURE() {
		return PICTURE;
	}
	public void setPICTURE(String pICTURE) {
		PICTURE = pICTURE;
	}
	public Double getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Double aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public Double getINTEREST() {
		return INTEREST;
	}
	public void setINTEREST(Double iNTEREST) {
		INTEREST = iNTEREST;
	}
	public Long getMONTHS() {
		return MONTHS;
	}
	public void setMONTHS(Long mONTHS) {
		MONTHS = mONTHS;
	}
	public String getLOAN_TYPE() {
		return LOAN_TYPE;
	}
	public void setLOAN_TYPE(String lOANTYPE) {
		LOAN_TYPE = lOANTYPE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getALLOW_ACCESS() {
		return ALLOW_ACCESS;
	}
	public void setALLOW_ACCESS(String aLLOWACCESS) {
		ALLOW_ACCESS = aLLOWACCESS;
	}
	public String getUTM_SOURCE() {
		return UTM_SOURCE;
	}
	public void setUTM_SOURCE(String uMTSOURCE) {
		UTM_SOURCE = uMTSOURCE;
	}
	public Date getOPEN_TIME() {
		return OPEN_TIME;
	}
	public void setOPEN_TIME(Date oPENTIME) {
		OPEN_TIME = oPENTIME;
	}
	public Date getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(Date sTARTTIME) {
		START_TIME = sTARTTIME;
	}
	public Date getREADY_TIME() {
		return READY_TIME;
	}
	public void setREADY_TIME(Date rEADYTIME) {
		READY_TIME = rEADYTIME;
	}
	public Date getPASS_TIME() {
		return PASS_TIME;
	}
	public void setPASS_TIME(Date pASSTIME) {
		PASS_TIME = pASSTIME;
	}
	public Double getSURPLUS_AMOUNT() {
		return SURPLUS_AMOUNT;
	}
	public void setSURPLUS_AMOUNT(Double sURPLUSAMOUNT) {
		SURPLUS_AMOUNT = sURPLUSAMOUNT;
	}
	public Double getFINISHED_RATIO() {
		return FINISHED_RATIO;
	}
	public void setFINISHED_RATIO(Double fINISHEDRATIO) {
		FINISHED_RATIO = fINISHEDRATIO;
	}
	public String getRepaidByGuarantor() {
		return repaidByGuarantor;
	}
	public void setRepaidByGuarantor(String repaidByGuarantor) {
		this.repaidByGuarantor = repaidByGuarantor;
	}
	public String getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getForbidComment() {
		return forbidComment;
	}
	public void setForbidComment(String forbidComment) {
		this.forbidComment = forbidComment;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBorrowerLevel() {
		return borrowerLevel;
	}
	public void setBorrowerLevel(String borrowerLevel) {
		this.borrowerLevel = borrowerLevel;
	}
	public String getDisplayLoanType() {
		return displayLoanType;
	}
	public void setDisplayLoanType(String displayLoanType) {
		this.displayLoanType = displayLoanType;
	}
	public String getCurrentIsRepaid() {
		return currentIsRepaid;
	}
	public void setCurrentIsRepaid(String currentIsRepaid) {
		this.currentIsRepaid = currentIsRepaid;
	}
	public String getAmountPerShare() {
		return amountPerShare;
	}
	public void setAmountPerShare(String amountPerShare) {
		this.amountPerShare = amountPerShare;
	}
	public String getOldLoan() {
		return oldLoan;
	}
	public void setOldLoan(String oldLoan) {
		this.oldLoan = oldLoan;
	}
	public String getInterestPerShare() {
		return interestPerShare;
	}
	public void setInterestPerShare(String interestPerShare) {
		this.interestPerShare = interestPerShare;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getOverDued() {
		return overDued;
	}
	public void setOverDued(String overDued) {
		this.overDued = overDued;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

}
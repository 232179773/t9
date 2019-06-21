/* 
 * Powered By T9
 */

package com.t9.system.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_STAFF")
public class Demo extends BaseDomain {

	private String USER_NAME;
	private String PHONE_NO;
	private String PHONE_IMEI;
	private String PASSWORD;
	private String STAFF_TYPE;
	private Date CREATE_TIME;

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSERNAME) {
		USER_NAME = uSERNAME;
	}

	public String getPHONE_NO() {
		return PHONE_NO;
	}

	public void setPHONE_NO(String pHONENO) {
		PHONE_NO = pHONENO;
	}

	public String getPHONE_IMEI() {
		return PHONE_IMEI;
	}

	public void setPHONE_IMEI(String pHONEIMEI) {
		PHONE_IMEI = pHONEIMEI;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getSTAFF_TYPE() {
		return STAFF_TYPE;
	}

	public void setSTAFF_TYPE(String sTAFFTYPE) {
		STAFF_TYPE = sTAFFTYPE;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATETIME) {
		CREATE_TIME = cREATETIME;
	}


}
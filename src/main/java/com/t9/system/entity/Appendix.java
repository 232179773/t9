/* 
 * Powered By T9
 */

package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_APPENDIX")
public class Appendix extends BaseDomain {

	private String FILE_NAME;
	private String FILE_SIZE;
	private String FILE_URL;
	private String TABLE_NAME;
	private String COL_NAME;
	private String DATA_ID;
	private Long FILE_ORDER;
	private Long FILE_STATE;
	private Date CREATETIME;
	private Date UPDATETIME;
	private String CREATER;

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	public String getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(String fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	public String getFILE_URL() {
		return FILE_URL;
	}

	public void setFILE_URL(String fILE_URL) {
		FILE_URL = fILE_URL;
	}

	public String getTABLE_NAME() {
		return TABLE_NAME;
	}

	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}

	public String getCOL_NAME() {
		return COL_NAME;
	}

	public void setCOL_NAME(String cOL_NAME) {
		COL_NAME = cOL_NAME;
	}

	public String getDATA_ID() {
		return DATA_ID;
	}

	public void setDATA_ID(String dATA_ID) {
		DATA_ID = dATA_ID;
	}

	public Long getFILE_ORDER() {
		return FILE_ORDER;
	}

	public void setFILE_ORDER(Long fILE_ORDER) {
		FILE_ORDER = fILE_ORDER;
	}

	public Long getFILE_STATE() {
		return FILE_STATE;
	}

	public void setFILE_STATE(Long fILE_STATE) {
		FILE_STATE = fILE_STATE;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public Date getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getCREATER() {
		return CREATER;
	}

	public void setCREATER(String cREATER) {
		CREATER = cREATER;
	}

}
package com.t9.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * null
 * @author generate auto
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="TB_APP_FORM")
public class AppForm  extends BaseDomain{

	private String TABLE_CODE;
	private String APP_NAME;
	private String TABLE_COMMENT;
//	private String DATASOURCE;
	private String GEN_FILE;
	private String APP_PATH;
	private String CLASS_PATH;
	private String PACKAGE;
	private String LOG_ID;
	public String getTABLE_CODE() {
		return TABLE_CODE;
	}
	public void setTABLE_CODE(String tABLE_CODE) {
		TABLE_CODE = tABLE_CODE;
	}
	public String getAPP_NAME() {
		return APP_NAME;
	}
	public void setAPP_NAME(String aPP_NAME) {
		APP_NAME = aPP_NAME;
	}
	public String getTABLE_COMMENT() {
		return TABLE_COMMENT;
	}
	public void setTABLE_COMMENT(String tABLE_COMMENT) {
		TABLE_COMMENT = tABLE_COMMENT;
	}
	public String getGEN_FILE() {
		return GEN_FILE;
	}
	public void setGEN_FILE(String gEN_FILE) {
		GEN_FILE = gEN_FILE;
	}
	public String getAPP_PATH() {
		return APP_PATH;
	}
	public void setAPP_PATH(String aPP_PATH) {
		APP_PATH = aPP_PATH;
	}
	public String getCLASS_PATH() {
		return CLASS_PATH;
	}
	public void setCLASS_PATH(String cLASS_PATH) {
		CLASS_PATH = cLASS_PATH;
	}
	public String getPACKAGE() {
		return PACKAGE;
	}
	public void setPACKAGE(String pACKAGE) {
		PACKAGE = pACKAGE;
	}
	public String getLOG_ID() {
		return LOG_ID;
	}
	public void setLOG_ID(String lOG_ID) {
		LOG_ID = lOG_ID;
	}

}
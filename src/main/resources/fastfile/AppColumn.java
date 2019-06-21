package com.arp.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 字段
 * @author generate auto
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="SYSTEM_APP_COLUMN")
public class AppColumn extends BaseEntity{
	
	private String APP_ID;
	private String TABLE_CODE;
	private String COL_CODE;
	private String COL_NAME;
	private String COL_TYPE;
	private String JAVA_COL;
	private String JAVA_TYPE;
	private String DATA_LENGTH;
	private String DATA_SCALE;
	private String HTML_TYPE;
	private String DATA_SOURCE;
	private String DATA_TYPE;
	private String REF_COL;
	private String COL_USE;
	private String NULLABLE;
	private String SHOW_SET;
	private String SHOW_REMARK;
	private Long COL_ORDER;
	private String COL_COMMENT;
	private Long SHOW_LIST;
	private Long SHOW_ADD;
	private Long SHOW_EDIT;
	private Long SHOW_QUERY;
	private Long LIST_LENGTH;
	private Long SHOW_SIZE;
	public String getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}
	public String getTABLE_CODE() {
		return TABLE_CODE;
	}
	public void setTABLE_CODE(String tABLE_CODE) {
		TABLE_CODE = tABLE_CODE;
	}
	public String getCOL_CODE() {
		return COL_CODE;
	}
	public void setCOL_CODE(String cOL_CODE) {
		COL_CODE = cOL_CODE;
	}
	public String getCOL_NAME() {
		return COL_NAME;
	}
	public void setCOL_NAME(String cOL_NAME) {
		COL_NAME = cOL_NAME;
	}
	public String getCOL_TYPE() {
		return COL_TYPE;
	}
	public void setCOL_TYPE(String cOL_TYPE) {
		COL_TYPE = cOL_TYPE;
	}
	public String getJAVA_COL() {
		return JAVA_COL;
	}
	public void setJAVA_COL(String jAVA_COL) {
		JAVA_COL = jAVA_COL;
	}
	public String getJAVA_TYPE() {
		return JAVA_TYPE;
	}
	public void setJAVA_TYPE(String jAVA_TYPE) {
		JAVA_TYPE = jAVA_TYPE;
	}
	public String getDATA_LENGTH() {
		return DATA_LENGTH;
	}
	public void setDATA_LENGTH(String dATA_LENGTH) {
		DATA_LENGTH = dATA_LENGTH;
	}
	public String getDATA_SCALE() {
		return DATA_SCALE;
	}
	public void setDATA_SCALE(String dATA_SCALE) {
		DATA_SCALE = dATA_SCALE;
	}
	public String getHTML_TYPE() {
		return HTML_TYPE;
	}
	public void setHTML_TYPE(String hTML_TYPE) {
		HTML_TYPE = hTML_TYPE;
	}
	public String getDATA_SOURCE() {
		return DATA_SOURCE;
	}
	public void setDATA_SOURCE(String dATA_SOURCE) {
		DATA_SOURCE = dATA_SOURCE;
	}
	
	public String getREF_COL() {
		return REF_COL;
	}
	public void setREF_COL(String rEF_COL) {
		REF_COL = rEF_COL;
	}
	public String getCOL_USE() {
		return COL_USE;
	}
	public void setCOL_USE(String cOL_USE) {
		COL_USE = cOL_USE;
	}
	public String getNULLABLE() {
		return NULLABLE;
	}
	public void setNULLABLE(String nULLABLE) {
		NULLABLE = nULLABLE;
	}
	public String getSHOW_SET() {
		return SHOW_SET;
	}
	public void setSHOW_SET(String sHOW_SET) {
		SHOW_SET = sHOW_SET;
	}
	public String getSHOW_REMARK() {
		return SHOW_REMARK;
	}
	public void setSHOW_REMARK(String sHOW_REMARK) {
		SHOW_REMARK = sHOW_REMARK;
	}
	public Long getCOL_ORDER() {
		return COL_ORDER;
	}
	public void setCOL_ORDER(Long cOL_ORDER) {
		COL_ORDER = cOL_ORDER;
	}
	public String getCOL_COMMENT() {
		return COL_COMMENT;
	}
	public void setCOL_COMMENT(String cOL_COMMENT) {
		COL_COMMENT = cOL_COMMENT;
	}
	public Long getSHOW_LIST() {
		return SHOW_LIST;
	}
	public void setSHOW_LIST(Long sHOW_LIST) {
		SHOW_LIST = sHOW_LIST;
	}
	public Long getSHOW_ADD() {
		return SHOW_ADD;
	}
	public void setSHOW_ADD(Long sHOW_ADD) {
		SHOW_ADD = sHOW_ADD;
	}
	public Long getSHOW_EDIT() {
		return SHOW_EDIT;
	}
	public void setSHOW_EDIT(Long sHOW_EDIT) {
		SHOW_EDIT = sHOW_EDIT;
	}
	public Long getSHOW_QUERY() {
		return SHOW_QUERY;
	}
	public void setSHOW_QUERY(Long sHOW_QUERY) {
		SHOW_QUERY = sHOW_QUERY;
	}
	public Long getLIST_LENGTH() {
		return LIST_LENGTH;
	}
	public void setLIST_LENGTH(Long lIST_LENGTH) {
		LIST_LENGTH = lIST_LENGTH;
	}
	public Long getSHOW_SIZE() {
		return SHOW_SIZE;
	}
	public void setSHOW_SIZE(Long sHOW_SIZE) {
		SHOW_SIZE = sHOW_SIZE;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String dATA_TYPE) {
		DATA_TYPE = dATA_TYPE;
	}

}
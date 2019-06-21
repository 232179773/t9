/* 
 * Powered By T9
 */

package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_USER")
public class SystemUser extends BaseDomain {

	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_DISABLED = 2;
	public static final int STATUS_OVERDUE = 8;
	public static final int STATUS_DELETED = 9;

	public static final String USER_RIGHTTYPE_FRONT = "1";
	public static final String USER_RIGHTTYPE_BACK = "2";
	public static final String USER_RIGHTTYPE_ALL = "3";

	private String LOGIN_CODE; // 登录帐号
	
	private String LOGIN_PWD; // 登录密码(加密后)
	private Integer STATUS; // 状态(0未激活、1正常、2停用、8过期、9已删除
	private Date VALIDITY_DATE; // 有效期(即截止日期)
	private String USER_RIGHTTYPE; // 用户权限类型(1前台用户、2后台用户、3所有(即前后台权限都有))',;
	
	private boolean SUPER_ADMIN; // 是否超级管理员
	private String NAME; // 用户名或单位名',
	private String NICKNAME; // 昵称',
	private String LOGO; // 头像LOGO',
	private String TELEPHONE; // 固定电话',
	private String MOBLIE_PHONE; // 手机号',
	private String POSTCODE; // 邮编',
	private String EMAIL; // 电子邮箱',
	private String REGION;// 地区
	private String ADDRESS; // 详细地址',
	private String REMARK; // 备注',
	private Date CREATETIME; // 创建时间',
	private Date UPDATETIME; // 修改时间',
	private String CREATER; // 创建人(用户ID)',

	public String getLOGIN_CODE() {
		return LOGIN_CODE;
	}

	public void setLOGIN_CODE(String lOGIN_CODE) {
		LOGIN_CODE = lOGIN_CODE;
	}
	@JsonIgnore
	public String getLOGIN_PWD() {
		return LOGIN_PWD;
	}

	public void setLOGIN_PWD(String lOGIN_PWD) {
		LOGIN_PWD = lOGIN_PWD;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Date getVALIDITY_DATE() {
		return VALIDITY_DATE;
	}

	public void setVALIDITY_DATE(Date vALIDITY_DATE) {
		VALIDITY_DATE = vALIDITY_DATE;
	}

	public String getUSER_RIGHTTYPE() {
		return USER_RIGHTTYPE;
	}

	public void setUSER_RIGHTTYPE(String uSER_RIGHTTYPE) {
		USER_RIGHTTYPE = uSER_RIGHTTYPE;
	}
	@JsonIgnore
	public boolean getSUPER_ADMIN() {
		return SUPER_ADMIN;
	}

	public void setSUPER_ADMIN(boolean isSuperAdmin) {
		SUPER_ADMIN = isSuperAdmin;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getNICKNAME() {
		return NICKNAME;
	}

	public void setNICKNAME(String nICKNAME) {
		NICKNAME = nICKNAME;
	}

	public String getLOGO() {
		return LOGO;
	}

	public void setLOGO(String lOGO) {
		LOGO = lOGO;
	}

	public String getTELEPHONE() {
		return TELEPHONE;
	}

	public void setTELEPHONE(String tELEPHONE) {
		TELEPHONE = tELEPHONE;
	}

	public String getMOBLIE_PHONE() {
		return MOBLIE_PHONE;
	}

	public void setMOBLIE_PHONE(String mOBLIE_PHONE) {
		MOBLIE_PHONE = mOBLIE_PHONE;
	}

	public String getPOSTCODE() {
		return POSTCODE;
	}

	public void setPOSTCODE(String pOSTCODE) {
		POSTCODE = pOSTCODE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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

	public String getREGION() {
		return REGION;
	}

	public void setREGION(String rEGION) {
		REGION = rEGION;
	}

}
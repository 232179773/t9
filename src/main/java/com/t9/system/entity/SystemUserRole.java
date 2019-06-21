/* 
 * Powered By T9
 */

package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_USERROLE")
public class SystemUserRole extends BaseDomain {

	private String ROLE_ID;          //角色ID
	private String USER_ID;          //用户ID
	private Date CREATETIME;         //创建时间
	private Date UPDATETIME;         //修改时间
	private String CREATER;          //创建人(用户ID)
	private String UPDATER;          //修改人(用户ID)
	   
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
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
	public String getUPDATER() {
		return UPDATER;
	}
	public void setUPDATER(String uPDATER) {
		UPDATER = uPDATER;
	}
	
}
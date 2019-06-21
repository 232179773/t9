/* 
 * Powered By T9
 */

package com.t9.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_ROLE")
public class SystemRole extends BaseDomain {

	private String NAME;                //角色名称
	private String PARENTROLEID;        //父角色ID
	private String REMARK;              //备注
	private Date CREATETIME;            //创建时间
	private String CREATER;             //创建人(用户ID)
	
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPARENTROLEID() {
		return PARENTROLEID;
	}
	public void setPARENTROLEID(String pARENTROLEID) {
		PARENTROLEID = pARENTROLEID;
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
	public String getCREATER() {
		return CREATER;
	}
	public void setCREATER(String cREATER) {
		CREATER = cREATER;
	}

	

}
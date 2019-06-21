/* 
 * Powered By T9
 */

package com.t9.system.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_LOG")
public class Log extends BaseDomain {

	private String STAFF_NAME;
	private String LOG_TYPE;
	private Date LOG_TIME;
	private String CLIENT_IP;
	private String URL;
	private String LOG_TITLE;
	private String LOG_CONTENT;
	public String getSTAFF_NAME() {
		return STAFF_NAME;
	}
	public void setSTAFF_NAME(String sTAFFNAME) {
		STAFF_NAME = sTAFFNAME;
	}
	public String getLOG_TYPE() {
		return LOG_TYPE;
	}
	public void setLOG_TYPE(String lOGTYPE) {
		LOG_TYPE = lOGTYPE;
	}
	public Date getLOG_TIME() {
		return LOG_TIME;
	}
	public void setLOG_TIME(Date lOGTIME) {
		LOG_TIME = lOGTIME;
	}
	public String getCLIENT_IP() {
		return CLIENT_IP;
	}
	public void setCLIENT_IP(String cLIENTIP) {
		CLIENT_IP = cLIENTIP;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getLOG_TITLE() {
		return LOG_TITLE;
	}
	public void setLOG_TITLE(String lOGTITLE) {
		LOG_TITLE = lOGTITLE;
	}
	public String getLOG_CONTENT() {
		return LOG_CONTENT;
	}
	public void setLOG_CONTENT(String lOGCONTENT) {
		LOG_CONTENT = lOGCONTENT;
	}

}
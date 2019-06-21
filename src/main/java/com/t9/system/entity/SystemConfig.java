/* 
 * Powered By T9
 */

package com.t9.system.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_SYSTEM_CONFIG")
public class SystemConfig extends BaseDomain {

	//基本属性
	private String CODE;                   //参数编码
	private String NAME;                   //参数名称
	private String VALUE;                  //值
	private String REMARK;                 //详细描述
	private Integer SORT;                   //排序
	private String DEFAULT_VALUE;          //默认值
	
	//可见性|权限 控制
	private String EDIT_ATTRIBUTE;         //编辑属性 (1仅超级管理员可编辑、2有权限即可编辑、3不能编辑)
	private String DISPLAY_ATTRIBUTE;      //可见性属性 (1仅超级管理员可见、2有权限即可见)

	//页面编辑控制,校验相关
	/**
	 * 参数选项(参数为单选或复选时用，多个用分号分隔) 格式严格要求如下:
	 * 布尔： 1-启用;0-停用;
     * 选项： 1-A值;2-B值;3-C值...
	 * 数值: [|( a,b)|]  起止值
	 * 日期: [|( a,b)|]  起止日期
	 * 字符串: 用正则表达式校验.(按Java标准.如前台语言对应的正则表达式规则与Java规则不一致,可以放弃前台检查)
	 */
	private String PARAM_OPTION;           //参数选项 (参数为单选或复选时用，多个用逗号分隔)
    /**
	 * 数据类型(1字符；2数字;3整数;4日期、5布尔类型) 
	 */
	private String DATATYPE;               //数据类型 (1字符；2数字;3整数;4日期、5布尔类型)
	public static final String DATATYPE_STRING = "1";
	public static final String DATATYPE_NUMBER = "2";
    public static final String DATATYPE_INT = "3";
	public static final String DATATYPE_DATE = "4";
    public static final String DATATYPE_BOOL = "5";
    public static final String DATE_FORMAT="YYYYMMDD";
    public static final String DATETIME_FORMAT="YYYYMMDD hh:mm:ss";
    
	/**
	 * 编辑样式(1输入框;2下拉框;3日期；4复选框)
	 */
	private String EDITSTYLE;              //编辑样式 (1输入框;2下拉框;3日期,4复选框)
    public static final String EDITSTYLE_INPUT = "1";
    public static final String EDITSTYLE_COMBOBOX = "2";
    public static final String EDITSTYLE_DATE = "3";
    
    
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public Integer getSORT() {
		return SORT;
	}
	public void setSORT(Integer sORT) {
		SORT = sORT;
	}
	public String getDEFAULT_VALUE() {
		return DEFAULT_VALUE;
	}
	public void setDEFAULT_VALUE(String dEFAULT_VALUE) {
		DEFAULT_VALUE = dEFAULT_VALUE;
	}
	public String getEDIT_ATTRIBUTE() {
		return EDIT_ATTRIBUTE;
	}
	public void setEDIT_ATTRIBUTE(String eDIT_ATTRIBUTE) {
		EDIT_ATTRIBUTE = eDIT_ATTRIBUTE;
	}
	public String getDISPLAY_ATTRIBUTE() {
		return DISPLAY_ATTRIBUTE;
	}
	public void setDISPLAY_ATTRIBUTE(String dISPLAY_ATTRIBUTE) {
		DISPLAY_ATTRIBUTE = dISPLAY_ATTRIBUTE;
	}
	public String getPARAM_OPTION() {
		return PARAM_OPTION;
	}
	public void setPARAM_OPTION(String pARAM_OPTION) {
		PARAM_OPTION = pARAM_OPTION;
	}
	public String getDATATYPE() {
		return DATATYPE;
	}
	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}
	public String getEDITSTYLE() {
		return EDITSTYLE;
	}
	public void setEDITSTYLE(String eDITSTYLE) {
		EDITSTYLE = eDITSTYLE;
	}
	
	

}
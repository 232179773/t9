/**
* 
*/
package com.t9.system.entity;

/**
 * @功能/模块 ：文件上传导入组件
 * @author shenhw
 * @version 1.0 2012-11-14
 * @类描述  导入模板字段类
 * @修订历史：
 */
public class Column {
    //字段名称
    private String name;
    //字段code
    private String code;
    //字段类型
    private String type;
    //字段最大长度
    private int maxLen;
    //字段最小长度
    private int minLen;
  //字段最小长度
    private boolean required;
    
    /**
     * @param name
     * @param code
     * @param type
     * @param maxLen
     * @param minLen
     */
    public Column(String name, String code, String type, int maxLen, int minLen) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.maxLen = maxLen;
        this.minLen = minLen;
    }
    
    /**
     * @param name
     * @param code
     * @param type
     * @param maxLen
     * @param minLen
     * @param required
     */
    public Column(String name, String code, String type, int maxLen, int minLen,boolean required) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.maxLen = maxLen;
        this.minLen = minLen;
        this.required=required;
    }
    
    
    /**
     * 无参构造
     */
    public Column() {
        super();
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getMaxLen() {
        return maxLen;
    }
    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }
    public int getMinLen() {
        return minLen;
    }
    public void setMinLen(int minLen) {
        this.minLen = minLen;
    }


    public boolean isRequired() {
        return required;
    }


    public void setRequired(boolean required) {
        this.required = required;
    }
    
    
}

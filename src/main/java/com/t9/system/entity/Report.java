/**
* 
*/
package com.t9.system.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能/模块 ：文件上传导入组件
 * @author shenhw
 * @version 1.0 2012-11-14
 * @类描述    校验报告信息类
 * @修订历史：
 */
public class Report {
    //行
    private int rowIndex;
    
    //报告信息
    private List<String> messageList;
    
    public Report() {
        messageList = new ArrayList<String>();
    }

    public Report(int rowIndex) {
        this.rowIndex = rowIndex;
        messageList = new ArrayList<String>();
    }
    
    /**
     * 获取行序号
     * 
     * @return
     */
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * 设置行序号
     * 
     * @param rowIndex
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * 添加校验信息
     * 
     * @param message
     */
    public void addMessage(String message) {
        messageList.add(message);
    }

    /**
     * 返回是否校验成功
     * 
     * @return
     */
    public boolean isValid() {
        return messageList.size() == 0;
    }

    /**
     * 返回校验信息条数
     * 
     * @return
     */
    public int getSize() {
        return messageList.size();
    }

    /**
     * 获取一条检验信息
     * 
     * @param index
     * @return
     */
    public String getMessage(int index) {
        return this.messageList.get(index);
    }
    /**
     * 获取所有检验信息
     * 
     * @param 
     * @return
     */
    public List<String> getMessageList() {
        return this.messageList;
    }
}

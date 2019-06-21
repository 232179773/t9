<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<div class="pageHeader">
    <form id="platForm">
    <div class="searchBar">
        <table class="searchContent">
            <tr>
                <td>
                                                         网站名称:<input type="text" name="PLAT" />
                </td>
                
                <td>
                                                     查询月份：<input type="text" name="MONTH" id="MONTH" class="my97Date" dateFmt="yyyyMM" readonly="true"/>
                </td>
                <td>
                                                  排序字段：<select name="rankCol">
                            <option value=""></option>
                            <option value="tzj">投之家</option>
                            <option value="eye">天眼</option>
                            <option value="rong">融360</option>
                            <option value="yifei">奕飞</option>
                     </select>
              </td>
            </tr>
        </table>
    </div>
    </form>
</div>
<div id="platPageDiv" class="pageContent"></div>
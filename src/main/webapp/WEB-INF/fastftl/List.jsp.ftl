<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<title>${APP_NAME}</title>
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">
 
</script>
<div class="pageHeader">
	<form id="${APP_PATH}QueryForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			<#list columnList as col><#if col.SHOW_QUERY=="1">
			<#if col.JAVA_TYPE!="Date">
				<td>
				<#if col.HTML_TYPE??&& col.HTML_TYPE=="select">
					${col.COL_NAME}：<select id="${col.COL_CODE}" name="${col.COL_CODE}" <#if col.DATA_TYPE=="dict">dataSrc="type=DICT_OPTION&DICT_CODE=${col.DATA_SOURCE}"<#elseif col.DATA_TYPE=="sql">dataSrc="type=SQL&SQL=${col.DATA_SOURCE}"</#if><#if col.REF_COL??> ref="${col.REF_COL}"</#if>>
					</select>
				<#else>
					${col.COL_NAME}：<input type="text" id="${col.COL_CODE}" name="${col.COL_CODE}" <#if col.JAVA_TYPE=="Date"> class="my97Date" readonly="true"</#if> />
				</#if>
				</td>
			<#else>
			<td>
				${col.COL_NAME}：<input type="text" name="${col.COL_CODE}_START" id="${col.COL_CODE}_START" class="my97Date" maxDate="#F{$dp.$D('${col.COL_CODE}_END')}" readonly="true"/>
				</td>
				<td>~</td>
				<td>
					<input type="text" name="${col.COL_CODE}_END" id="${col.COL_CODE}_END" class="my97Date" minDate="#F{$dp.$D('${col.COL_CODE}_START')}" readonly="true"/>
				</td>	
			</#if>
			</#if></#list>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="${APP_PATH}PageDiv" class="pageContent"></div>


<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="/frame/_include.jsp" %>

<div class="pageHeader">
	<form id="systemConfigListForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					系统参数名称： <input type="text" name="paramName" />
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="systemConfigListDiv" class="pageContent"></div>
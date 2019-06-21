<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">
$(function(){
});
</script>
<div class="pageHeader">
	<form id="menuQueryForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					菜单名称<input type="text" name="NAME" />
				</td>
				<td>
					父菜单名称<input type="text" name="PARENT_MENU_ID"/>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="menuPageDiv" class="pageContent"></div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">

</script>
<div class="pageHeader">
	<form id="tableQueryForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					表CODE：<input type="text" name="TABLE_CODE" />
				</td>
				<td>
					表名：<input type="text" name="APP_NAME"/>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="tableListDiv" class="pageContent"></div>

<div id="columnListDiv" class="pageContent"></div>
<form id="downLoadForm" style="display:none ;" method="post" target="ExportFrame"></form>
<iframe id="ExportFrame" name="ExportFrame" style="display: none"></iframe>
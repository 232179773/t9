<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">

</script>
<div class="pageHeader">
	<form id="pointParamForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名<input type="text" name="USER_NAME" />
				</td>
				<td>
					号码<input type="text" name="PHONE_NO"/>
				</td>
			</tr>
			<tr>
				<td>
					<select name="province" showNull="true" dataSrc="type=PRIVINCE" ref="city" refUrl="type=GETREGION_BY_PARENT_CODE&PARENT_CODE={value}" style="width:120px">
					</select>
					<select name="city" id="city"  showNull="true"  refUrl="type=GETREGION_BY_PARENT_CODE&PARENT_CODE={value}"  ref="REGION" style="width:120px">
					</select>
					<select name="REGION" id="REGION"  showNull="true"  style="width:120px">
					</select>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="pointParamDiv" class="pageContent"></div>


<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="/frame/_include.jsp" %>

<div class="pageHeader">
	<form id="systemUserList_bForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					账户类型： 
					<select name="USER_RIGHTTYPE" id="USER_RIGHTTYPE" showNull="true" 
						dataSrc="type=DICT_OPTION&DICT_CODE=USER_RIGHTTYPE"  style="width:120px"></select>
				</td>
				<td>
					登录账户： <input type="text" name="LOGIN_CODE" />
				</td>
				<td>
					名称： <input type="text" name="NAME" />
				</td>
				<td>
					角色名： <input type="hidden" name="ROLE_ID" id="ROLE_ID"/>
					<input type="text" name="ROLE_NAME" id="ROLE_NAME"/>
					<input type="button" value="选 择" onClick="chooseRole()">
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="systemUserListDiv" class="pageContent"></div>
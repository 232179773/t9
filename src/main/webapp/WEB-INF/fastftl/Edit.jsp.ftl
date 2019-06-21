<%@ page language="java" pageEncoding="UTF-8"%>
<title>${APP_NAME}</title>
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">
$(function() {
	if(urlParam.ID){
		T9.post('service/${APP_PATH}!queryById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#${APP_PATH}EditForm").setData(result.obj);
			}
		});
	};
	
});

function saveData() {
	var param=$('#${APP_PATH}EditForm').serializeJSON();
	var url =  'service/${APP_PATH}!saveEntity' ;
	if(urlParam.ID){
		url = 'service/${APP_PATH}!updateEntity';
	}
	T9.post(url, param, function(result){
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#${APP_PATH}PageDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="${APP_PATH}EditForm"  data-validator-option="{valid:saveData}" >
		<#list columnList as col>
			<#if col.SHOW_EDIT=="0"><input type="hidden" id="${col.COL_CODE}" name="${col.COL_CODE}"/> </#if>
		</#list>
		<div class="pageFormContent" layoutH="56">
			<#list columnList as col><#if col.SHOW_EDIT!="0">
			<p>
				<label>${col.COL_NAME}：</label>
				<#if col.HTML_TYPE??&& col.HTML_TYPE=="select">
					<select class="combox" id="${col.COL_CODE}" name="${col.COL_CODE}" <#if col.DATA_TYPE=="dict">dataSrc="type=DICT_OPTION&DICT_CODE=${col.DATA_SOURCE}"<#elseif col.DATA_TYPE=="sql">dataSrc="type=SQL&SQL=${col.DATA_SOURCE}"</#if><#if col.REF_COL??> ref="${col.REF_COL}"</#if>>
					</select>
				<#else>
					<input type="text" id="${col.COL_CODE}" name="${col.COL_CODE}" maxlength="${col.DATA_LENGTH}"<#if col.JAVA_TYPE=="Date"> class="my97Date" readonly="true"</#if><#if col.DATA_TYPE??&& col.DATA_TYPE=="open"> dataSrc="type=open&src=${col.DATA_SOURCE}"</#if><#if col.NULLABLE=="N"> class="required"</#if>  />
				</#if>
			</p>
			</#if></#list>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>


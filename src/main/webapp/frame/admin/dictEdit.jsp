<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/dict!queryDictById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#dictEditForm").setData(result.obj);
			}
		});
	}
	
});

function saveData() {
	var param=$('#dictEditForm').serializeJSON();
	var url =  'service/dict!saveDict' ;
	if(urlParam.ID){
		url = 'service/dict!updateDict';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#dictPageDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="dictEditForm" data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">	
			<p>
				<label>字典类编码：</label>
				<input type="text" id="DICT_CODE" name="DICT_CODE" maxlength="100" data-rule="required"/>
				<span class="info"></span>
			</p>
		    <p>
				<label>字典类名称：</label>
				<input   type="text" id="DICT_NAME" name="DICT_NAME" maxlength="100"/>
			</p>
			
		    <p>
				<label>类型：</label>
				<select id="DICT_TYPE" name="DICT_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=SYS_DICT_TYPE">
				</select>
			</p>
			
		    <p>
				<label>备注：</label>
				<input type="text" id="REMARK" name="REMARK" maxlength="30"/>
			</p>
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

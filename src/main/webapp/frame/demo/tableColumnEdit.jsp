<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/fastFile!queryColumnById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#dictEditForm").setData(result.obj);
			}
		});
	}
	
});

function saveData() {
	var param=$('#dictEditForm').serializeJSON();
	var url =  'service/fastFile!saveColumn' ;
	if(urlParam.ID){
		url = 'service/fastFile!saveColumn';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#columnListDiv").gridReload();
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
		<input type="hidden" id="TABLE_CODE" name="TABLE_CODE" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
		    <p>
				<label>字段名称：</label>
				<input type="text" id="COL_NAME" name="COL_NAME" maxlength="30"/>
			</p>	
			<p>
				<label>控件类型：</label>
				<select class="combox" id="HTML_TYPE" name="HTML_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_HTML_TYPE">
				</select>
			</p>
		    <p>
				<label>字段类型：</label>
				<select class="combox" id="DATA_TYPE" name="DATA_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_DATA_TYPE">
				</select>
			</p>
			
		    <p>
				<label>字段来源：</label>
				<input type="text" id="DATA_SOURCE" name="DATA_SOURCE"/>
			</p>
		    <p>
				<label>字段用途：</label>
				<select class="combox" id="COL_USE" name="COL_USE" showNull=true dataSrc="type=DICT_OPTION&DICT_CODE=FAST_COL_USE">
				</select>
			</p>
		    <p>
				<label>字段备注：</label>
				<input type="text" id="SHOW_REMARK" name="SHOW_REMARK" maxlength="30"/>
			</p>	
		    <p>
				<label>影响字段：</label>
				<input type="text" id="REF_COL" name="REF_COL" maxlength="30"/>
			</p>
		    <p>
				<label>列表显示：</label>
				<select class="combox" id="SHOW_LIST" name="SHOW_LIST" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_SHOW_SET">
				</select>
			</p>
		    <p>
				<label>新增显示：</label>
				<select class="combox" id="SHOW_ADD" name="SHOW_ADD" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_SHOW_SET">
				</select>
			</p>
		    <p>
				<label>编辑显示：</label>
				<select class="combox" id="SHOW_EDIT" name="SHOW_EDIT" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_SHOW_SET">
				</select>
			</p>
		    <p>
				<label>查询条件显示：</label>
				<select class="combox" id="SHOW_QUERY" name="SHOW_QUERY" dataSrc="type=DICT_OPTION&DICT_CODE=FAST_SHOW_SET">
				</select>
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

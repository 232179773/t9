<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp"%>

<script>
	$(function() {
		if (urlParam.ID) {
			T9.post('service/systemConfig!getSystemConfigById', {
				ID : urlParam.ID
			}, function(result) {
				if (result.success) {
					var data = result.obj;

					//修改值部件
					var paramOption = data.param_OPTION;
					var dataType = data.datatype; //1 bool;2 小数 3整数 4日期
					editStyle = data.editstyle;

					var comp = getValueComp(data.value, editStyle, dataType,
							paramOption);
					if (comp) {
						$("#spanValue").append(comp);
						$("#spanValue").append('<div memo="VALUE"></div>');
					}
					
					$("#systemConfigForm").setData(result.obj);

				}
			});
		}
	});
	function saveData() {
		if(!$("#systemConfigForm").valid()) {
			return false;
		} else {
			var param = $('#systemConfigForm').serializeJSON();
			T9.post('service/systemConfig!updateConfig', param, function(result) {
				if (result.success) {
					T9.alert("保存成功！");
					T9.dialogClose();
					$("#systemConfigListDiv").gridReload();
				} else{
					T9.alert(result.message);
				}
			});
		}

	}

	/**
	 * 
	 * 布尔： 1-启用;0-停用;
	 * 选项： 1-A值;2-B值;3-C值...
	 * 数值: [|( a,b)|]  起止值
	 * 日期: [|( a,b)|]  起止日期
	 * 字符串: 用正则表达式校验.(按Java标准.如前台语言对应的正则表达式规则与Java规则不一致,可以放弃前台检查)
	 * @param value 
	 * @param editStyle
	 * @param dataType
	 * @param paramOption 参数选项(参数为单选或复选时用，多个用逗号分隔) 格式严格要求如下
	 *                    布尔： 1-启用;0-停用; 选项：  1-A值;2-B值;3-C值... 
	 *                    数值: [|( a,b)|]  起止值    日期: [|( a,b)|]  起止日期         
	 */
	function getValueComp(value, editStyle, dataType, paramOption) {
		var comp;
		if ('2' == editStyle) { //combobox 
			comp = document.createElement('select');
			comp.id = 'VALUE';
			comp.name = 'VALUE';
			comp.className= "required";
			if (!paramOption || paramOption == '') {
				if (dataType == '5') {
					paramOption = '1-是;0-否';
				}
			}
			if (paramOption && paramOption != '') {
				var options = paramOption.split(';');
				for ( var i = 0; i < options.length; i++) {
					var firstIndex = options[i].indexOf('-');
					var optValue = options[i].substring(0, firstIndex);
					var optText = options[i].substring(firstIndex + 1,
							options[i].length);
					var compOpt = document.createElement('option');
					compOpt.text = optText;
					compOpt.value = optValue;
					if (compOpt.value == value
							|| (dataType == '2' && isEqualNum(optValue, value))) {
						compOpt.selected = 'selected';
					}
					comp.options.add(compOpt);
				}
			}
		} else if ('3' == editStyle) { //date
			comp = document.createElement('input');
			comp.id = 'VALUE';
			comp.name = 'VALUE';
			comp.className = "my97Date required";
			comp.readonly = "true";

		} else {
			comp = '<input class="required" name="VALUE" id="VALUE" value='+value+' />';

			//'1' == editStyle //input, default
			// 数值: [|( a,b)|]  起止值  dataType＝＝5 是用正则表达式
			if (dataType != '1') {
				if (paramOption && paramOption != '') {
					paramOption = paramOption.toString().split(",");
					var strFirstFlag = paramOption[0].substring(0, 1);
					var strFirst = paramOption[0].substring(1);
					var strTwoFlag = paramOption[1]
							.substring(paramOption[1].length - 1);
					var strTwo = paramOption[1].substring(0,
							paramOption[1].length - 1);
					var minValueNumber;
					var maxValueNumber;
					if (strFirstFlag == "(") {
						if (strFirst && strFirst != "") {
							minValueNumber = parseInt(strFirst) + 1;
						} else {
							minValueNumber = null;
						}
					} else {
						if (strFirst && strFirst != "") {

							minValueNumber = parseInt(strFirst);

						} else {
							minValueNumber = null;
						}
					}
					if (strTwoFlag == ")") {
						if (strTwo && strTwo != "") {
							maxValueNumber = parseInt(strTwo) - 1;
						} else {
							maxValueNumber = null;
						}
					} else {
						if (strTwo && strTwo != "") {
							maxValueNumber = parseInt(strTwo);
						} else {
							maxValueNumber = null;
						}
					}
					
					comp.min=minValueNumber;
					comp.max=maxValueNumber;


				}
			}
			//dataType 1:bool 2:小数，3:整数，4:日期
			if (dataType == '5') { //bool

				return null; //use combobox
			} else if (dataType == '2') { //小数

				comp.className="number" ;
			} else if (dataType == '3') { //整数

				comp.className="digits" ;

			} else if (dataType == '4') { //日期
				return null; //use date editStyle
			} else if (dataType == '5' || !dataType) {
	
			}
		}
		
		return comp;
	}

	function isEqualNum(num1, num2) {
		return num1 != null && num2 != null
				&& (parseFloat(num1) - parseFloat(num2) < 0.00001);
	}
</script>

<div class="pageContent">
	<form id="systemConfigForm">
		<input type="hidden" id="ID" name="ID" maxlength="25" />
		<div class="pageFormContent" layoutH="56">

			<p>
				<label>名称：</label> <input type="text" id="NAME" name="NAME"
					readonly="readonly" maxlength="30" />
			</p>

			<p>
				<label>值：</label> <span id="spanValue"></span>
			</p>

			<p>
				<label>概述：</label>
				<textarea id="REMARK" name="REMARK"
					style="width: 200px; height: 100px; border: 0px"
					readonly="readonly"></textarea>
			</p>

		</div>

		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="saveData()">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

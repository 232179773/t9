<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp"%>
<style type="text/css">

<!--
body, p, blockquote, dl, dd, ul, ol, h1, h2, h3, h4, h5, h6, caption, form, fieldset, legend, pre {
    margin: 0;
    padding: 0;
}
body, input, select, button, textarea {
    font: 12px/1.8 Tahoma;
}
textarea {
    overflow: auto;
    resize: vertical;
}
address, cite, dfn, em, var {
    font-style: normal;
}
q:before, q:after {
    content: "";
}
img, iframe, fieldset {
    border: 0 none;
}
table {
    border-collapse: collapse;
}
a {
    text-decoration: none;
}
html {
}
img {
    vertical-align: middle;
}
iframe {
    vertical-align: top;
}
html {
    font-size: 62.5%;
}
body {
    background: none repeat scroll 0 0 #FFFFFF;
    color: #333333;
    font-size: 1.2rem;
}
p {
    line-height: 2;
}
h1, h2, h3, h4 {
    font-family: "Microsoft Yahei";
}
h1 {
    font-size: 24px;
}
h2 {
    font-size: 20px;
    margin: 40px 0 0;
    padding-bottom: 4px;
}
h3 {
    font-size: 16px;
    margin: 30px 0 0;
    padding-bottom: 4px;
}
h4 {
    font-size: 14px;
    margin: 20px 0 0;
    padding-bottom: 4px;
}
h5, h6 {
    font-size: 12px;
    margin: 16px 0 0;
}
pre, p, ul, ol {
    margin: 0.5em 0;
}
a {
    color: #93BD20;
}
pre {
    display: block;
    white-space: pre-wrap;
    word-wrap: break-word;
}
code {
    background: none repeat scroll 0 0 #FFFCE8;
    border: 1px solid #FEE9CC;
    border-radius: 3px 3px 3px 3px;
    color: rgba(0, 0, 0, 0.75);
    font-family: 'Courier New','Terminal',monospace;
    font-size: 12px;
    margin: 0 2px;
    padding: 0 5px;
}
blockquote {
    background: none repeat scroll 0 0 #F9F9F9;
    color: #777777;
    margin-bottom: 1.5em;
    padding: 0.5em 10px;
}
blockquote p {
    color: #777777;
}
.cf {
}
.cf:after {
    clear: both;
    content: " ";
    display: block;
    height: 0;
}
.fl {
    float: left;
}
.fr {
    float: right;
}
.hide {
    display: none !important;
}
.inline-block {
    display: inline-block;
}
.break-word {
    word-break: break-all;
    word-wrap: break-word;
}
.fs12 {
    font-size: 12px;
}
.fs14 {
    font-size: 14px;
}
.fw700 {
    font-weight: 700;
}
.red {
    color: #DD1144;
}
.gray {
    color: #888888;
}
.yahei {
    font-family: "Microsoft Yahei";
}
.simsun {
    font-family: Simsun;
}
.ml40 {
    margin-left: 40px;
}
.divider {
    border-bottom: 4px dotted #F5F5F5;
    border-top: 4px dotted #F5F5F5;
    font-size: 0;
    height: 0;
    margin: 80px 0 10px;
    text-align: right;
}
.divider span {
    background: none repeat scroll 0 0 #FFFFFF;
    color: #BBBBBB;
    display: inline-block;
    font-size: 12px;
    font-style: italic;
    padding: 0 15px;
    position: relative;
    top: -12px;
}
.table {
    font-size: 12px;
    margin-top: 20px;
}
.table caption {
    font: 600 18px/2.5 'microsoft yahei';
}
.table tr {
    border-bottom: 1px solid #DDDDDD;
}
.table td {
    border-left: 1px dotted #DDDDDD;
    padding: 8px;
}
.table .odd td {
    background: none repeat scroll 0 0 #EEEEEE;
}
.table thead tr {
    border: medium none;
}
.table thead th {
    height: 30px;
    padding: 0 5px;
    text-align: center;
}
.table tbody {
    border-top: 1px solid #BBBBBB;
}
.table tbody th {
    background: none repeat scroll 0 0 #F5F5F5;
    padding: 0 5px;
    text-align: right;
    white-space: nowrap;
}
.table pre {
    color: #333333;
    font-size: 12px;
    margin: 0 0 0 5px;
}
.table .title {
    background: none repeat scroll 0 0 #D5D5D5;
    font-size: 16px;
    padding: 10px;
    text-align: left;
}
.table .green {
    color: #009900;
    font-weight: 400;
    line-height: 2;
}
.orange {
    color: #AA6600 !important;
}
pre.demo {
    background: none repeat scroll 0 0 #FFFCE8;
    border-left: 2px solid #FEE9CC;
    font-family: 'Courier New','Terminal',monospace;
    font-size: 12px;
    line-height: 16px;
    margin-right: 40px;
    padding: 10px;
}
code.demo {
    background: none repeat scroll 0 0 #F5FBE6;
    border-color: #ECF7CE;
    display: inline-block;
    font-family: "source-code-pro",Consolas,monospace !important;
    font-size: 14px;
    font-weight: bold;
    margin-right: 5px;
}
.sidebar {
    background: none repeat scroll 0 0 #252A3A;
    bottom: 0;
    color: #888888;
    font-size: 12px;
    left: 0;
    padding: 0 10px;
    position: fixed;
    top: 0;
    width: 220px;
    z-index: 1;
}
.sidebar a {
    color: #A6AEC6;
    line-height: 2;
}
.sidebar a:hover {
    color: #93BD20;
    text-decoration: underline;
}
.sidebar h1 {
    margin: 1.5em 0 1em;
}
.sidebar h1 a, .sidebar h1 a:hover {
    color: #DDDDDD;
}
.sidebar h1 span, .sidebar h1 em {
    display: inline-block;
    padding: 2px;
}
.sidebar h1 span {
    background: none repeat scroll 0 0 #93BD20;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.3) inset, 0 3px 7px rgba(0, 0, 0, 0.7);
    padding-left: 5px;
}
.sidebar h1 em {
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.3) inset, 0 3px 7px rgba(0, 0, 0, 0.7);
    padding-right: 8px;
}
.sidebar img {
    vertical-align: middle;
}
.sidebar ul {
    border-bottom: 2px solid #000000;
    list-style: none outside none;
    margin: 0 20px 0 0;
}
.sidebar ul:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}
.sidebar ul a {
    color: #CCD1DF;
}
.sidebar ul li {
    float: left;
    font-size: 14px;
    padding: 0 5px;
}
.sidebar ol {
    border-top: 1px solid #454F6D;
    color: #666666;
    font-size: 14px;
    margin: 0 20px 0 0;
    padding-left: 20px;
    padding-top: 10px;
}
.sidebar li {
    padding: 0;
}
.sidebar .description {
    margin: 0 0 50px;
}
.sidebar .current {
    background: none repeat scroll 0 0 #000000;
}
.sidebar .current a {
    color: #93BD20;
}
.sidebar .totop {
    margin-top: 50px;
    text-align: right;
}
.sidebar .totop a {
    background: none repeat scroll 0 0 #000000;
    border: medium none;
    border-radius: 2px 2px 2px 2px;
    color: #BBBBBB;
    display: inline-block;
    padding: 2px 5px;
}
.sidebar .totop a:hover {
    background: none repeat scroll 0 0 #1A1A1A;
}
.main {
    font-size: 14px;
    margin-left: 240px;
    max-width: 800px;
    min-width: 720px;
    overflow: hidden;
    padding: 0 0 40px 40px;
    vertical-align: top;
}
.main a {
    text-decoration: underline;
}
.main a:hover {
    text-decoration: none;
}
.main h2 {
    margin-bottom: -1px;
    margin-top: 0;
    padding-top: 30px;
}
.main h6 {
    color: #AAAAAA;
}
.main p {
    padding-left: 2em;
}
.main ul, .main ol {
    margin-left: 4em;
}
.main ol li {
    padding-bottom: 10px;
}
.main hr {
    background-color: #DDDDDD;
    border: 0 none;
    display: block;
    height: 4px;
    margin-top: 20px;
}
.main .mark {
    background: none repeat scroll 0 0 #F5FBE6;
    color: #222222;
    display: inline-block;
    font-family: "source-code-pro",Consolas,monospace !important;
    font-weight: bold;
    margin-right: 5px;
    width: 75px;
}
.demo p {
    padding: 0;
}
.demo blockquote {
    border: medium none;
    margin: 10px 0 20px 30px;
}
.demo blockquote:before {
    color: #DDDDDD;
    content: "\"";
    font-family: Georgia;
    font-size: 64px;
    line-height: 1;
    margin-left: -35px;
    margin-top: -20px;
    position: absolute;
}
.demo_html {
    padding: 0 0 20px;
}
.type, .settings em {
    background: none repeat scroll 0 0 transparent;
    color: #999999;
    font-family: Georgia,serif;
    font-size: 14px;
    font-style: italic;
    font-weight: normal;
    margin: 0 4px;
}
.settings h3 {
    font-family: Helvetica;
    font-size: 18px;
}
.directory {
    color: #222222;
    margin-top: 20px;
    position: relative;
}
.directory span {
    left: 200px;
    position: absolute;
}
.directory p {
    margin: 0;
}
.directory li {
    background: none repeat scroll 0 0 #F5FBE6;
}
.directory .gray {
    background: none repeat scroll 0 0 transparent;
    margin-bottom: 15px;
}
.footer-wrapper {
    clear: both;
    color: #9C9C9C;
    font-family: Georgia,'Xin Gothic','PT Sans','Hiragino Sans GB','Helvetica Neue',Helvetica,Arial,sans-serif;
    font-size: 12px;
    margin-left: auto;
    margin-right: auto;
    max-width: 1040px;
    padding: 10px 0;
    text-align: right;
    text-shadow: 0 1px 0 #FFFFFF;
}
.footer-wrapper a {
    color: #9C9C9C;
    text-decoration: none;
}
.footer-wrapper iframe {
    position: relative;
    top: 3px;
}
input, textarea, select, button, label {
    font-family: inherit;
    font-size: 100%;
    margin: 0;
    outline: medium none;
    padding: 0;
    vertical-align: middle;
}
input, textarea {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #C0C0C0 #E1E1E1 #E1E1E1 #C0C0C0;
    border-image: none;
    border-style: solid;
    border-width: 1px;
    color: #000000;
    outline: medium none;
}
input:focus, textarea:focus {
    border-color: #51A7E8;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.075) inset, 0 0 5px rgba(81, 167, 232, 0.5);
}
input[type="checkbox"], input[type="radio"] {
    padding: 0;
}
input[disabled], input[readonly] {
    background-color: #F6F6F6;
    cursor: default;
}
select {
    height: 22px;
    line-height: 18px;
    padding: 2px;
}
fieldset {
    padding: 10px 0;
}
fieldset input {
    font-size: 12px;
    height: 23px;
    margin: 0 5px 0 0;
    padding: 3px;
    width: 180px;
}
fieldset label input {
    width: 22px;
}
fieldset textarea {
    min-height: 80px;
    padding: 3px;
    vertical-align: top;
    width: 300px;
}
.form-label {
    display: inline-block;
    min-width: 100px;
    text-align: right;
}
.form-submit {
    padding-left: 103px;
}
.btn, button {
    background: linear-gradient(to bottom, #F5F5F5 5%, #E6E6E6 100%) repeat scroll 0 0 #F5F5F5;
    border: 1px solid #CCCCCC;
    box-shadow: 0 1px 0 0 #FFFFFF inset;
    color: #1A1A1A;
    cursor: pointer;
    display: inline-block;
    font-family: Verdana,Simsun;
    font-size: 14px;
    font-weight: normal;
    overflow: visible;
    padding: 3px 20px;
    text-decoration: none;
    text-shadow: 0 1px 0 #FFFFFF;
}
.btn:hover, button:hover {
    background: linear-gradient(to bottom, #E6E6E6 5%, #F5F5F5 100%) repeat scroll 0 0 #E6E6E6;
}
.btn:active, button:active {
    position: relative;
    top: 1px;
}
a.btn {
    text-decoration: none;
}
-->
 
</style>
<script src="<%=basePath %>frame/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="<%=basePath %>frame/js/validator-0.2.0/jquery.validator.css">
<script type="text/javascript"
	src="<%=basePath %>frame/js/validator-0.2.0/jquery.validator.js"></script>
<script type="text/javascript"
	src="<%=basePath %>frame/js/validator-0.2.0/local/zh_CN.js"></script>
<script type="text/javascript">
<!--
	// $('firstform').on('valid.form', function(e, form){
	// 	alert(100);
	//     //do something...
	// });

	$(function() {

		// 	$('#firstform').validator({
		// 	    valid: function(form){
		// 	    	alert('验证公国的实得分的是否');
		// 	    }
		// 	});

		// 	$("#firstform").on('valid.form', function(e, form){
		// 	   alert(2353254);
		// 	});

	});

	// $('firstform').validator(function(form){
	// 	alert(100);
	//     $(form).ajaxSubmit({
	//     	alert(123);
	//         //some options
	//     });
	// });

	// function checkUserName(val) {
	// 	T9.post('service/demo!checkUserName',{},function(result){
	// 		if(result.success && result.obj){
	// 			return true;
	// 		}

	// 		return false;
	// 	});
	// }

	// jQuery.validator.addMethod("isVaildName", function(value, element) {
	// 	return this.optional(element) || checkUserName(value);
	// }, "用户名已存在!");

	// function save(){
	// 		alert("验证通过，执行提交操作!");
	// 	}

	function submittEvent() {
		document.getElementById("firstform").submit();
		return false;
		// 	alert($('#firstform').isValid());
		// 	if($('#firstform').isValid()){
		// 		alert('表单通过验证');
		// 	}else{
		// 		alert('表单验证未通过');
		// 	}
		// 	return false;

		// 	$('#firstform').on('valid.form', function(){
		// 	    alert(100);
		// 	});
		// 	return false;

		// 	if(!$("#firstform").valid()) {
		// 		return false;
		// 	} else {
		// 		///做后续业务。。。。
		// 		alert("校验成功");
		// 	}
	}

	function save(form) {
// 		alert("验证通过，执行提交操作!");
	}

	function noPass() {
		alert("验证未通过");
	}
//-->
</script>
<h2 class="contentTitle">表单验证</h2>


<div align="left">

	<form method="post" id="firstform"
		data-validator-option="{valid:save,invalid:noPass}">
		<fieldset>
			<div class="pageFormContent nowrap" layoutH="97">

				<p>
					<input name="username" data-rule="用户名:required;username"
						data-tip="请输入3到8位数字" data-ok="正确" placeholder="用户名">
				</p>
				<p>
					<input name="userpwd" data-rule="密码:required;password"
						placeholder="密码">
				</p>
				<button type="submit">提交</button>
			</div>
		</fieldset>
	</form>
  <p align="left">两个字段匹配<br></p>
 <p align="left">1. 两个字段的值必须相同<br></p>
  <p align="left">规则：match[name];<br></p>
	<form id="demo_21"  method="post" target="_blank">
		<fieldset>
		<div class="pageFormContent nowrap" layoutH="97">
			<p>
				<label class="form-label">密码：</label> <input name="pwd"
					data-rule="密码:required;">
			</p>
			<p>
				<label class="form-label">确认密码：</label> <input name="againPwd"
					data-rule="确认密码: required;match[pwd]">
			</p>
		</div>
		<div class="form-submit">
			<button type="submit">提交</button>
		</div>
		</fieldset>
	</form>
	
	
	
	<h4>2. 两个字段比较大小</h4>
<blockquote>
规则：match[limits, name];
<br>
limits可用值：eq (相同), lt (小于)，gt (大于)，lte (小于等于)，gte (大于等于)
<br>
这种模式下，两个字段的值都会转换为数字进行比较
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_22" target="_blank" method="post">
<fieldset>
<p>
<label class="form-label">总量：</label>
<input data-rule="总量:required;integer[+0];match[gte, last]" name="totle">
</p>
<p>
<label class="form-label">余量：</label>
<input data-rule="余量:required;integer[+0];match[lte, totle]" name="last">
</p>
<p> </p>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>
	



<div class="demo_html" align="left">
<h2 id="plugin_demo_size">字符串长度</h2>
<h4>1. 限制字符长度范围</h4>
<blockquote>
规则：length[limits];
<br>
limits（范围）表示方式： ~100 (小于100)，0~100 (0到100)，100~ (大于100)
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_31" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">描述：</label>
<textarea data-rule="描述:required;length[8~100]" name="description"></textarea>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>


<h4>2. 限制字符长度范围，计算真实长度（全角字符计算两个字符）</h4>
<blockquote>
规则：length[limits, true];
<br>
limits（范围）表示方式同上
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_32" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">描述：</label>
<textarea data-rule="描述:required;length[8~100, true]" name="description"></textarea>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>


<h2 id="plugin_demo_range">数值范围</h2>
<blockquote>
规则：range[limits];
<br>
limits（范围）表示方式： ~100 (小于100)，0~100 (0到100)，100~ (大于100)
</blockquote>
<h4>1. 输入数值必须在指定范围</h4>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_41" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">折扣：</label>
<input data-rule="required; integer; range[1~100]" name="discount">
%
<span class="msg-box n-right" data-for="discount"></span>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>

<h4>2. 输入数值必须小于某个范围</h4>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_42" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">分数：</label>
<input data-rule="required; integer[+0]; range[~100]" name="score">
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>

<h4>3. 输入数值必须大于某个范围</h4>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_43" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">分数：</label>
<input data-rule="required; integer; range[0~]" name="score">
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>
	


<h2 id="plugin_demo_need">checkbox 和 radio的验证</h2>
<h4>1. radio的必选</h4>
<blockquote>
1. 对于checkbox和radio，要“必填”的话，不能使用“
<code>required</code>
”，而是使用“
<code>checked</code>
”
<br>
2. 你只需要在第一个checkbox或者radio上面绑定规则就可以了
<br>
3. 消息会自动生成，并且显示在最后面，你无需关注消息怎么显示
<br>
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_51" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">性别：</label>
<label>
<input type="radio" data-rule="checked" value="1" name="gender">
男
</label>
<label>
<input type="radio" value="2" name="gender">
女
</label>
<label>
<input type="radio" value="0" name="gender">
保密
</label>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>


<h4>2. 不能为空，至少选择一项</h4>
<blockquote>
在第一个checkbox上绑定规则：
<code>checked</code>
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_52" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">兴趣：</label>
<label>
<input type="checkbox" data-rule="checked" value="0" name="interest[]">
看书
</label>
<label>
<input type="checkbox" value="1" name="interest[]">
上网
</label>
<label>
<input type="checkbox" value="2" name="interest[]">
睡觉
</label>
<label>
<input type="checkbox" value="3" name="interest[]">
运动
</label>
<label>
<input type="checkbox" value="4" name="interest[]">
发呆
</label>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>


<h4>3. 控制选中项目数</h4>
<blockquote>
1.
<code>checked[2~]</code>
表示选择的项目要在2项以上
<br>
2. 不要对
<code>:radio</code>
使用参数，因为本身就是单选，直接
<code>checked</code>
就可以了
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_53" target="_blank" method="post" action="results.php">
<fieldset>
<label class="form-label">兴趣：</label>
<label>
<input type="checkbox" data-rule="checked[2~]" value="0" name="interest[]">
看书
</label>
<label>
<input type="checkbox" value="1" name="interest[]">
上网
</label>
<label>
<input type="checkbox" value="2" name="interest[]">
睡觉
</label>
<label>
<input type="checkbox" value="3" name="interest[]">
运动
</label>
<label>
<input type="checkbox" value="4" name="interest[]">
发呆
</label>
</fieldset>
<div class="form-submit">
<button type="submit">提交</button>
</div>
</form>
</div>

<h2 id="plugin_custom_rule">自定义规则</h2>
<h4>1. 正则方式 - DOM传参</h4>
<blockquote>
DOM传参：data-rule-ruleName="[RegExp, 'ErrorMessage']"
<br>
其中ruleName是规则名字，可以随便定义，只要调用规则时保持一致就可以
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_101" target="_blank" method="post" action="results.php">
<fieldset>
<input data-rule-mobile="[/^1[3458]\d{9}$/, '请检查手机号格式']" data-rule="required; mobile" placeholder="手机号" name="mobile">
</fieldset>
<button type="submit">提交</button>
</form>
</div>

<h4>2. 正则方式 - JS传参</h4>
<blockquote>
JS传参：ruleName: [RegExp, 'ErrorMessage']
<br>
实际上跟DOM传参是一样的
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_102" class="n-validator n-default" target="_blank" method="post" action="results.php" novalidate="true">
<fieldset>
<input placeholder="手机号" name="mobile" aria-required="true">
</fieldset>
<button type="submit">提交</button>
</form>
</div>

<h4>3. 回调方式 - JS传参</h4>
<blockquote>
JS传参：ruleName: function(element, param, field) {}
<br>
这种方式具有最大的灵活性
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_103" class="n-validator n-default" target="_blank" method="post" action="results.php" novalidate="true">
<fieldset>
<input placeholder="手机号" name="mobile" aria-required="true">
</fieldset>
<button type="submit">提交</button>
</form>
</div>


<h2 id="plugin_custom_rule">自定义消息显示位置</h2>
<h4>1. 验证隐藏域，消息显示在代理的输入框周围</h4>
<blockquote>
DOM传参：data-target="jQuery选择器"
<br>
如果“jQuery选择器”选择的DOM是输入框，该输入框将成为消息位置的代理
<br>
否则消息直接显示在“jQuery选择器”选择的DOM内部
</blockquote>
<p>下面的例子中，实际验证和提交的是“pid”字段，不要在意乎表象^_^</p>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_111" target="_blank" method="post" action="results.php">
<fieldset>
<input id="pid" type="hidden" data-target="#product" data-rule="required;" name="pid">
<input id="product" readonly="" placeholder="点击选择产品">
</fieldset>
<button type="submit">提交</button>
</form>
</div>


<h4>2. 设置消息占位，精确控制消息位置</h4>
<blockquote>
<span class="msg-box" data-for="fieldName"></span>
<br>
把这段DOM放到哪个位置，消息就显示在哪儿
<br>
还可以通过给这个span写style内联样式，控制更精准的位置
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<form id="demo_112" target="_blank" method="post" action="results.php">
<fieldset>
<input data-rule="required; mobile;" placeholder="手机号" name="mobile">
</fieldset>
<button type="submit">提交</button>
<span class="msg-box n-right" data-for="mobile"> </span>
</form>
</div>

<h4>3. 消息我做主，怎么显示完全由我说了算</h4>
<blockquote>
传递 msgHandler 回调来实现，该回调有一个参数（所有错误消息的数组）
<br>
<b>注意</b>
：传递msgHandler后意味着，只在提交表单时验证，并且所有消息将不会自动生成，包括tip、ok、loading
</blockquote>
<h6>Preview</h6>
<div class="demo_html">
<div id="msg_holder"></div>
<form id="demo_113" class="n-validator n-default" target="_blank" method="post" action="results.php" novalidate="true">
<fieldset>
<input placeholder="手机号" name="mobile" aria-required="true">
<input placeholder="邮箱" name="email" aria-required="true">
</fieldset>
<button type="submit">提交</button>
</form>
</div>

</div>



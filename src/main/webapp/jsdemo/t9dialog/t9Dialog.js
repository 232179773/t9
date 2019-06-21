var T9={
		/**
		 * 属性:
		 * autoOpen   初始化之后，是否立即显示对话框，默认为 true
		 * modal        是否模式对话框，默认为 false
		 * closeOnEscape   当用户按 Esc 键之后，是否应该关闭对话框，默认为 true
		 * draggable  是否允许拖动，默认为 true
		 * resizable    是否可以调整对话框的大小，默认为 true
		 * title           对话框的标题，可以是 html 串，例如一个超级链接。
		 * position     用来设置对话框的位置，有三种设置方法
		 *    1.  一个字符串，允许的值为  'center', 'left', 'right', 'top', 'bottom'.  此属性的默认值即为 'center'，表示对话框居中。
		 *    2.  一个数组，包含两个以像素为单位的位置，例如， 
		 *    3. 一个字符串组成的数组，例如， ['right','top']，表示对话框将会位于窗口的右上角
		 * 一组关于尺寸的属性，以像素为单位。
		 *   width     宽度, 默认 300
		 *   height    高度，默认 'auto'
		 *   minWidth     最小宽度，默认 150
		 *   minHeight    最小高度，默认 150
		 *   maxWidth   最大宽度
		 *   maxHeight   最大高度
		 * buttons   一个对象，属性名将会被作为按钮的提示文字，属性值为一个函数，即按钮的处理函数
		 * bgiframe     解决 IE6 的 select 元素穿透问题，通过增加一个 iframe 来解决。默认为  true
		 *         这一功能需要使用脚本 jquery.bgiframe-2.1.2，脚本在 jQuery UI 压缩包中 development-bundle/external 文件夹中，
		 *         需要将这个文件加入到页面中
		 *         
		 * 方法:
		 * open     打开对话框，需要注意的是，并没有 open() 方法，而是通过 dialog( "open" ) 来调用。例如，  .dialog( "open" )
		 * close     关闭对话框
		 * destroy  摧毁一个对话框，去除对话框功能，将元素还原为初始化之前的状态。
		 * isOpen   检查对话框的状态，如果已经打开，返回 true.
		 * moveToTop  将对话框移到对话框的顶端
		 * option    设置或者读取对话框某个属性的值，有两种用法。
		 *        如果第二个参数为字符串，如果提供了三个参数，表示设置，如果两个参数，表示读取。 例如 .dialog( "option" , optionName , [value] )
		 *        如果第二个参数为对象，表示一次性设置多个属性。
		 * enable   启用对话框
		 * disable  禁用对话框
		 * 
		 * 事件
		 * create
		 * open
		 * focus
		 * dragStart
		 * drag
		 * dragStop
		 * resizeStart
		 * resize
		 * resizeStop
		 * beforeClose
		 * close
		 */
		dialog:function(selector, options){
			var dialog = $(selector).dialog({
				autoOpen:false,
			});
			
			dialog.open=function(){
				dialog.dialog("open");
			};
			
			dialog.isOpen=function(){
				dialog.dialog("isOpen");
			};
			dialog.close=function(){
				dialog.dialog("close");
			};
			
			dialog.getOption=function(optName){
				dialog.dialog("option", optName);
			};
			
			dialog.setOption=function(optName,optValue){
				dialog.dialog("option", optName, optValue);
			};
			
			dialog.bingCallback=function(callback){
				dialog.bind( "dialogclose", callback);
			};
			
			return dialog;
        }
	
};
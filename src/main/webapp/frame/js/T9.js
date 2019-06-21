/**
 * T9基础工具类
 * @author husq
 * create on: 1/7/2013 
 */

(function( window, undefined ) {
	
var document = window.document,
	navigator = window.navigator,
	location = window.location;

var _T9 =_t9 = t = window.T;

var T = t = window.T9 = function( selector ) {
	return T9.fn.init( selector);
};
	
T9.fn = T9 = {
	version:'1.0.1',
	
	init: function( selector) {
		if ( selector.size) {
			this[0] = selector;
			return this;
		}
		return T($(selector));
	},
	dialog:function(options){
		var defaults={
				width:700,
				height:400, /* 窗口的高度，值为'auto'或表示像素的整数 */
				border: 1, /* 窗口的外边框像素大小,必须是0以上的整数 */
				draggable: false, /* 是否可以拖动窗口 */
				closed:'',
				title:''
		};
		var opts = $.extend({}, defaults, options);
		var url=base_path;
		if(options.url.charAt(0)==='/')
			url+=options.url.substring(1);
		else{
			url+=options.url;
		}
		var jboxparam={
			    title: opts.title,
			    width: opts.width,
			    height:opts.height,
			    border:opts.border,
			    draggable:opts.draggable,
			    buttons: {},
				closed:function(){
			    	if(opts.callback){
				    	var result=window.returnResult;
				    	opts.callback(result);
				    	delete window.returnResult;
			    	}
			    }
		};
		$.jBox("iframe:"+url, jboxparam);
    },
    dialogClose:function(){    	
    	parent.$.jBox.close();
    },
    
    openWindow:function(options){
    	var url=base_path;
		if(options.url.charAt(0)==='/')
			url+=options.url.substring(1);
		else{
			url+=options.url;
		}
		window.open(url);
    },
    getCurrentLayer:function(){
    	var dig=$.pdialog.getCurrent();
    	var nav= navTab.getCurrentPanel();    
    	if(dig)
    		return dig;
    	return nav;
    },
    confirm:function(content,success){
    	$.jBox.confirm(content,'',function(v,h,f) {
    		if (v == 'ok'){
    			success();
    		}
    	});
    },
    error:function(content){
    	$.jBox.error(content, '');
    },
    info:function(content){
    	$.jBox.info(content, '');
    },
    warn:function(content){
    	$.jBox.info(content, '');
    },
    alert:function(content){
    	alert(content);
	//	$.jBox.alert(content, '');
    },
	/**
	 * tab组件
	 */
	tabs:function(selector,options){
		if(!options){
			options={};
		}
		if(!options.activate){
			options.activate=function( event, ui ) {
				if(ui.newPanel.attr('load')){
				return;
			}
			var init=ui.newPanel.attr('init');
		  	eval(init);
		  	ui.newPanel.attr('load','true');
			};
		}
		var tab=$(selector).data('tab');
		if(!tab){
		  tab=$(selector).tabs(options);
		}
		tab.getSelected=function(){
		  return tab.tabs( "option", "active" );
		};
		  
		tab.setSelected=function(index){
		  return tab.tabs( "option", "active",index);
		};		  
		  
		tab.mouseover=function(){
		  	return tab.tabs( "option", "event","mouseover");
		};
		  
		tab.disable=function(array){
		  	return tab.tabs("disable",array);
		};
		  
		tab.enable=function(array){
		  	return tab.tabs("enable",array);
		};
		  
		tab.tabsload=function(){
		  	return tab.on("tabsload",function( event, ui ) {
			var id=ui.newPanel.attr('id');
		  		alert(id);
		  	});
		};
		return tab;
	},
	
	tree:function(selector,url,options){
		var defaultOption={
				checkbox:false,
				getSelected:function(){},
				click:function(){}
		};
		var opts=$.extend({},defaultOption,options);
		var setting = {
				check: {
					enable: opts.checkbox,
					chkboxType :{ "Y" : "ps", "N" : "ps" }
				},
				data: {
					key: {
						title:"t"
					},
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: opts.beforeClick,
					onClick: opts.onClick
				}
			};
			T9.post(url,{},function(result){
				var zNodes=[];
				for(var i=0;i<result.obj.length;i++){
					var obj=result.obj[i];
					var m={ id:obj.ID, pId:obj.PARENT_MENU_ID, name:obj.NAME, t:obj.NAME};
					zNodes[i]=m;
				}
				zTree=$.fn.zTree.init($('#'+selector), setting, zNodes);
				zTree.expandAll(true);
			});
	},
	getTreeNodes:function(nodes){
		var arr=[];
		for(var i=0;nodes.length?(i<nodes.length):i<1;i++){
			var node=nodes.length?nodes[i]:nodes;
			arr.push(node);
			var childNode=node.children;
			if(childNode){
				var childNodes=T9.getTreeNodes(childNode);
				for(var z=0;z<childNodes.length;z++){
					arr.push(childNodes[z]);
				}
			}
		}
		return arr;
		
	},
	setTreeChecked:function(selector,ids){
		var zTree = $.fn.zTree.getZTreeObj(selector);
		var nodets = zTree.getNodes();
		var nodes=T9.getTreeNodes(nodets);
		for(var i=0;i<ids.length;i++){
			for(var k=0;k<nodes.length;k++){
				if(nodes[k].id==ids[i]){
					zTree.checkNode(nodes[k], true, true, null);
				}
			}
		}
	},
	getTreeChecked:function(selector){
		var zTree = $.fn.zTree.getZTreeObj(selector);
		var nodets = zTree.getNodes();
		var nodes=T9.getTreeNodes(nodets);
		var arr=[];
		for(var k=0;k<nodes.length;k++){
			if(nodes[k].checked){
				arr.push(nodes[k].id);
			}
		}
		return arr;
	},
	/**
	 * 加载进度条
	 */
	loading : function(target, loading) {
		var $target = $(target);
		if (loading) {
			$target.addClass('bl_ui_loading');
		} else {
			$target.removeClass('bl_ui_loading');
		}
	},
	
	navigate : function(url, onTop) {
		if (onTop === false) {
			window.location = base_path  + url;
		} else {
			window.top.location = base_path  + url;
		}
	},
	getFileAppendix:function(url){
		return base_path+url;
	},
	ajax: function( url, options ) {
		if(url.indexOf(base_path)<0){
			url=base_path+url;
		}
		var params=options.params?options.params:{};
		var async=options.async==undefined?false:options.async;
		var callback=options.callback?options.callback:{};
		var type=type?type:"post";
		var cache=cache?cache:false;
		var $eventTarget=$.data(document.body, 'eventTarget');
		if($eventTarget){
	//		alert($eventTarget.attr('name'));
			$eventTarget.attr('disabled','disabled');
		}
		top.$("#background,#progressBar").show();
		var afterRequest = function(callback) {	
			top.$("#background,#progressBar").hide();
			if($eventTarget){
				$eventTarget.removeAttr('disabled');
			}
			if ($.isPlainObject(callback)) {
				var loadingArea = callback.loadingArea;
				var loadingButton = callback.loadingButton;
				var disableButton = callback.disableButton;
				if (disableButton && !$.isArray(disableButton)) {
					disableButton = [ disableButton ];
				}
				var after = callback.after;	
				if (loadingArea) {
					T9.loading(loadingArea, false);
				}
				if (loadingButton) {
					loadingButton.loading(false);
				}
				if (disableButton && disableButton.length > 0) {
					$(disableButton).each(function(index, button) {
						button.enable();
					});
				}
				if ($.isFunction(after)) {
					after();
				}
			}
		};
		var beforeRequest = function(callback) {
			if ($.isPlainObject(callback)) {
				var loadingArea = callback.loadingArea;
				var loadingButton = callback.loadingButton;
				var disableButton = callback.disableButton;
				if (disableButton && !$.isArray(disableButton)) {
					disableButton = [ disableButton ];
				}
				var before = callback.before;
				if (loadingArea) {
					T9.loading(loadingArea, true);
				}
				if (loadingButton) {
					loadingButton.loading(true);
				}
				if (disableButton && disableButton.length > 0) {
					$(disableButton).each(function(index, button) {
						button.disable();
					});
				}
				if ($.isFunction(before)) {
					before();
				}
			}
		};
		beforeRequest(callback);
		$.ajax({
			url : url,
			data : params,
			dataType:'json',
			type : type,
			cache : cache,
			async:async,
			traditional : true,
			success : function(data) {
//				if(data.stateCode==-1){
//					data.success=false;
//				}else{
//					data.success=true;
//				}
				afterRequest(callback);
				var success;
				if ($.isFunction(callback)) {
					success = callback;
				}else if ($.isPlainObject(callback)) {
					success = callback.success;
				}
				if ($.isFunction(success)) {
					success(data);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				afterRequest(callback);
				var data={errorCode : jqXHR.status, errorMessage : errorThrown};
				if ($.isFunction(callback.fail)) {
					callback.fail(data);
				}else {
					T9.error('错误信息：' + data.errorMessage);
				}
			}
		});
	},
	post: function(url, params, callback,async,cache) {
		T9.ajax(url,{
			params : params,
			async:async,
			cache:cache,
			callback:callback
		});
	}
	
};
})( window );


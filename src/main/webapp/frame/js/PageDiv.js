/**
 * 分页组件
 */
window.PageDiv={
	//分页组件参数
	option:{
		url:"",//直接写项目相对路径
		autoload:true,//是否自动加载
		buttons:[],
		columns : [],// 列模型格式: [{header:'标题', key:'col_name' [,width:80,render:'dict:FAST_COL_USE',render:'table:FAST_COL_USE', cellRenderer:function(val), showTip:false}]
		pageSize:15,
		params:{},//默认提交参数
		formId:null,
		noDataMess:'没有数据记录',//无数据时显示提示
		displayNull:true,//null是否显示为‘null’还是空串
		pageDiv:true,
		height:0,//自动设置,0自动满屏，具体数字：设置高度
		onSuccess:function(records,$dataDiv,pageInfo){},//数据查询成功后回调
		checkedRow:function(record){},//选中某行记录时回调
		dbCheckedRow:function(record){},//选中某行记录时回调
		onDataLoaded:function(records,pageInfo){}//数据加载完毕后回调
	},
	/**
	 * 将div变成分页组件
	 */
	frontgrid:function($obj,options){
		$obj.frontgrid(options);
	},
	/**
	 * 设置分页组件参数
	 */
	gridOptions:function($obj,params){
		$obj.gridOptions(params);
	},
	/**
	 * 重新加载分页数据
	 */
	gridReload:function($obj){
		$obj.gridReload();
	},
	/**
	 * 获取分页选中的数据
	 */
	getCheckedArrayValue:function($obj){
		return $obj.getCheckedArrayValue();
	}
};
(function($) {
	$.addFrontgrid = function(placeholder, options) {
		var defaults={
				usepager : true, // 是否使用分页栏?
				autoload : true,
				columns : [],// 列模型格式: [{header:'标题', key:'col_name' [,width:80, cellRenderer:function(val), showTip:false}]
				page : 1, // 当前的页码
				pageSize:15,
				height:0,//自动设置
				total : 0, // 总共的记录数
				showTotal : true,
				params:{},//默认提交参数
				formId:null,
				pageDiv:true,
				noDataMess:'没有数据记录',//无数据时显示提示
				displayNull:true,//null是否显示为‘null’还是空串
				messageWhenHideTotal : '' // 当showTotal为false时, 默认显示的提示信息
		};
		var placeholderDiv=placeholder.id;
		var _pageDiv=placeholderDiv+'_pageDiv';
		var _dataDiv=placeholderDiv+'_dataDiv';
		var _dataGridTable=placeholderDiv+'_dataGridTable';
		var _loadDiv=placeholderDiv+'_loadDiv';
		var $placeholder=$(placeholder);
		var opts = $.extend({}, defaults, options);
		

		buildPageButton();
		$dataDiv=$('<div id="'+_dataDiv+'"></div>');
		if(opts.height==0){
			var height=$placeholder.prev().height()+90;
			$dataDiv.attr('layoutH',height);
		}else{
			$dataDiv.height(opts.height);
		}
		$placeholder.append($dataDiv);

		var cols=opts.columns;
		
		var renderCols='';
		for(var i=0;i<cols.length;i++){	
			if(cols[i].hide===true){
				cols.splice(i, 1);
				i--;
				continue;
			}
			if(cols[i].render){
				renderCols+=cols[i].key+":"+cols[i].render+";";
			}
		}
		buildTableHead();

		$placeholder.append('<div id="'+_loadDiv+'"></div>');
		var pDiv='<div id="'+_pageDiv+'" class="panelBar"></div>';
		
		$placeholder.append(pDiv);
				
		if(opts.treegrid){
			opts.pageDiv=false;
			opts.pageSize=1000;
		}
		buildPageDiv();
		
		if(!opts.treegrid){
			$placeholder.find('table.table').jTable();
			var tHeight=$(window).height();
			tHeight=tHeight-$dataDiv.attr('layoutH');
			$placeholder.find('.gridScroller').height(tHeight);
		}
				
		/**
		 * grid object for exporting
		 */
		var grid = {
			/**
			 * 加载最新的数据
			 * reflushParamsCache 当reflushParamsCache=true时重新读取参数
			 *                    当reflushParamsCache=false时读取缓存的参数
			 */
			populate : function(reflushParamsCache) {
				if (opts.onSubmit) {
					var gh = opts.onSubmit();
					if (!gh) {
						return false;
					}
				}
				if(reflushParamsCache){
					opts.page=1;
				}
				if(!opts.pageCount){
					if(opts.page!=1){
						alert("参数有误");
						return;
					}
				}else{
					if(opts.page<1||opts.page>opts.pageCount){
						return;
					}
				}	
				var params={page:opts.page,pagesize:opts.pageSize,renderCols:renderCols};
				if(opts.formId){
					$.extend(params, $("#"+opts.formId).serializeJSON());	
				}
				$.extend(params, opts.params);	
					var cc= {
							loadingArea : '#'+_loadDiv,
							success : function(data) {
								if(data.success===false){
									T9.error(data.message);
									return;
								}
								if(!data.pageInfo.pageCount){
									var count=data.pageInfo.count;
									data.pageInfo.page=parseInt(data.pageInfo.page);
									data.pageInfo.pageCount=Math.floor(count/opts.pageSize)+(count%opts.pageSize==0?0:1);
								}
								if(opts.treegrid){
									var vls=opts.treegrid.split(',');
									var id=vls[0];
									var pid=vls[1];
									var tData=[];
									for(var i=0;i<data.gridData.length;i++){
										var obj=data.gridData[i];
										var j=tData.length-1;
										if(!obj[pid])
											obj[pid]='';
										while(j>=0&&tData[j][pid]!=obj[pid]){
											j--;
										}
										if(j!=-1){
											j++;
											for(var z=tData.length-1;z>=j;z--){
												tData[z+1]=tData[z];
											}
											tData[j]=obj;
										}else{
											tData[tData.length]=obj;
										}
									}
									data.gridData=tData;
									tData=[];
									for(var i=0;i<data.gridData.length;i++){
										var obj=data.gridData[i];
										var j=tData.length-1;
										while(j>0&&obj[pid]&&(tData[j][pid]!=obj[pid]&&tData[j][id]!=obj[pid])){
											j--;
										}
										j++;
										for(var z=tData.length-1;z>=j;z--){
											tData[z+1]=tData[z];
										}
										tData[j]=obj;
									}
									data.gridData=tData;
								}
								opts.gridData=data.gridData;
								if(opts.onSuccess){
									opts.onSuccess(data.gridData,$('#'+_dataDiv),data.pageInfo);
								}else{
									buildTableHead();
									setTableData(data.gridData);
								}
								setPageDiv(data.pageInfo);
								if(opts.onDataLoaded){
									opts.onDataLoaded(data.gridData,data.pageInfo);
								}
								if(opts.treegrid){
									$placeholder.find('table.table').treetable({ expandable: true });
								//	$placeholder.find('table.table').treetable('expandAll');
									$placeholder.find('tbody').on("mousedown", "tr", function() {
								        $(".selected").not(this).removeClass("selected");
								        $(this).toggleClass("selected");
								      });
								}else{
									$placeholder.find('table.table').jTable();
									var tHeight=$(window).height();
									tHeight=tHeight-$dataDiv.attr('layoutH');
									$placeholder.find('.gridScroller').height(tHeight);
								}
								 // end of grid object
								$placeholder.find('#ctrl').click( function () {
									var checked=this.checked;
									var $objs=$placeholder.find('input[name="ctrlChk"]');
									if(checked){
										$objs.attr('checked',checked);
									}else{
										$objs.removeAttr('checked');
									}
								});
								$placeholder.find('tbody tr').click( function () {
									var i=$(this).attr('rel');
									if(opts.checkedRow){
										if(i==undefined){
											opts.checkedRow();
										}else{
											opts.checkedRow(opts.gridData[i]);
										}
									}
									if(opts.ctrl=='radio'){
										$($placeholder.find(':radio').get(i)).attr('checked','true');
									}
								});
								$placeholder.find('tbody tr:first').click();
								
								if(opts.dbCheckedRow){
									$placeholder.find('tbody tr').dblclick( function () {
										var i=$(this).attr('rel');
										if(i==undefined){
											opts.dbCheckedRow();
										}else{
											opts.dbCheckedRow(opts.gridData[i]);
										}
									});
								}
							}
						};
					$('#'+_dataDiv).html('');
					$('#'+_dataDiv).removeClass('j-resizeGrid');
					T9.post(opts.url, params, cc);
			}, // end of populate
			
			
			
			timeoutHandle : function() {
				if (opts._overtime) {
					if (opts.onTimeout) {
						opts.onTimeout(placeholder);
					} else {
						T9.alert('超时！超时设置为：' + opts.timeout + 's');
					}
				}
			},
			
			setNoData:function(){
				$('#'+_dataDiv).html('');
				$('#'+_dataDiv).removeClass('j-resizeGrid');
				buildTableHead();
				setTableData([]);
			}
			
			
		};
		
		function setTableData(gridData){			
			var $tbody = $('#'+_dataGridTable+' tbody');
			if(gridData.length==0&&opts.noDataMess!=''){
				var $emptyBody = $('<tr><td align="center" colspan="20">'+opts.noDataMess+'</td></tr>')
						.appendTo($tbody);
				return;
			}
			
			var cols=opts.columns;
			var html='';
			
			for ( var i = 0;i < gridData.length; i++) {
				$tbody.append($tr = $('<tr rel="'+i+'"></tr>'));
				if(opts.treegrid){
					var vls=opts.treegrid.split(',');
					var id=vls[0];
					var pid=vls[1];
					$tr.attr('data-tt-id',gridData[i][id]);
					if(gridData[i][pid])
						$tr.attr('data-tt-parent-id',gridData[i][pid]);
				}
				var j=0;
				if(opts.ctrl){
					if(!opts.treegrid){
						var content=gridData[i][cols[j].key];
						if(opts.ctrl=='checkbox')
							$tr.append($td = $('<td><input name="ctrlChk" type="checkbox" value="'+content+'" rel="'+i+'" /></td>'));
						else if(opts.ctrl=='radio')
							$tr.append($td = $('<td><input name="'+placeholderDiv+'ctrlRadio" type="radio" value="'+content+'"  rel="'+i+'" /></td>'));
					}
					j++;
				}
				for(;j<cols.length;j++){
					if(cols[j].name){
						gridData[i].NAME_OF_ID=gridData[i][cols[j].key];
					}
					$tr.append($td = $('<td></td>'));
					var content=gridData[i][cols[j].key];
					if (content === null || content === undefined) {
						if (opts.displayNull) {
							content = '';
							gridData[i][cols[j].key]='';
						} else {
							content = 'null';
						}
					}
					var cutoff=(cols[j].cutoff==null||cols[j].cutoff==undefined) ? true:cols[j].cutoff;
					if ($.isFunction(cols[j].cellRenderer)) {
						content = cols[j].cellRenderer(content, gridData[i]);
					}
					if(cols[j].render){
						content=gridData[i]["NAME_"+cols[j].key];
					}
					/*
					var content1=content;
					var colWidLen=0;
					if($td.width()>0)
						colWidLen=$td.width()/12;
					if(colWidLen>0&&cutoff&&typeof content1==="string"&&content1.indexOf("<")<0){
						if(content1.length>colWidLen){
						    var len = 0;  
						    for (var ti=0; ti<colWidLen; ti++) {   
						    	var c = content1.charCodeAt(ti); 
						    	if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
						    		if((++len)%2==0)
						    			colWidLen++;				    	
						    	}
						    }
						    if(content1.length>colWidLen){ 
						    	content1=content1.substring(0,colWidLen-2)+"...";
						    }
						}
						$td.html(content1);
						$td.attr({ title: content}); 
					}else{
						$td.html(content);
					}
					*/
					if(j==1&&opts.treegrid&&opts.ctrl){
						var aa='';
						if(opts.ctrl=='checkbox')
							aa='<input name="ctrlChk" type="checkbox" value="'+gridData[i][cols[0].key]+'" rel="'+i+'" />';
						else if(opts.ctrl=='radio')
							aa='<input name="ctrlRadio" type="radio" value="'+gridData[i][cols[0].key]+'" rel="'+i+'" />';
						content=aa+content;
					}
					$td.html(content);
				}
			}
		}
		function buildPageDiv(){
			$pageDiv=$('#'+_pageDiv);
			if(!opts.pageDiv){
				$pageDiv.hide();
			}
			sb='<div class="pages"><span>第 <b id="_pageNumber">0</b> 页</span>  ';
			sb+='<span>总记录数  <b id="_totleNumber">0</b> 条</span>';
			sb+='<span>每页显示  <b>'+opts.pageSize+'</b> 条</span> </div>';
			sb+='<a class="un" id="_firstPage">[首 页]</span>  ';
			sb+='<a class="un" id="_prevPage">[上一页]</span> ';
			sb+='<a class="act" id="_nextPage">[下一页]</a> ';
			sb+='<a class="act" id="_lastPage">[尾 页]</a> ';
			sb+='<span>转到第<input type="text"  id="pageBarPageNo" class="pageInp" />页</span>';
			sb+='<span id="pageBarGo" class="go">GO</span>';
			$pageDiv.html(sb);
			$pageDiv.find('#_firstPage').click($.proxy(frist, this));
			$pageDiv.find('#_prevPage').click($.proxy(previous, this));
			$pageDiv.find('#_nextPage').click($.proxy(next, this));
			$pageDiv.find('#_lastPage').click($.proxy(last, this));
			$pageDiv.find('#pageBarGo').click($.proxy(pageBarGo, this));
		}
		function pageBarGo(){
			var num=$('#'+_pageDiv).find('#pageBarPageNo').val();
	        $('#'+_pageDiv).find('#pageBarPageNo').val('');
			if(!/^\d+$/.test(num)||num=='0'||num==""||num==null){
			  return false;
			}
			if(!opts.pageCount){
				 alert("请先进行查询");
				 return false;
			}
			if(num>opts.pageCount){
				 alert("页码大于最大页码");
				 return false;
			}
			opts.page=num;
			grid.populate();
		}
		function frist(){
			if(opts.page==1)
				return;
			opts.page=1;
			grid.populate();
		}
		function previous(){
			if(opts.page==1)
				return;
			opts.page=opts.page-1;
			grid.populate();
		}
		function next(){
			if(opts.page==opts.pageCount)
				return;
			opts.page=opts.page+1;
			grid.populate();
		}
		function last(){
			if(opts.page==opts.pageCount)
				return;
			opts.page=opts.pageCount;
			grid.populate();
		}
		function setPageDiv(pageInfo){
			var pageNumber=pageInfo.page+"/"+pageInfo.pageCount;
			opts.pageCount=pageInfo.pageCount;
			$('#'+_pageDiv).find('#_totleNumber').html(pageInfo.count);
			$('#'+_pageDiv).find('#_pageNumber').html(pageNumber);
			if(pageInfo.page>1){
				$('#'+_pageDiv).find('#_firstPage').addClass('act').removeClass('un');
				$('#'+_pageDiv).find('#_prevPage').addClass('act').removeClass('un');
			}else{
				$('#'+_pageDiv).find('#_firstPage').addClass('un').removeClass('act');
				$('#'+_pageDiv).find('#_prevPage').addClass('un').removeClass('act');
			}
			if(pageInfo.page<pageInfo.pageCount){
				$('#'+_pageDiv).find('#_nextPage').addClass('act').removeClass('un');
				$('#'+_pageDiv).find('#_lastPage').addClass('act').removeClass('un');
			}else{
				$('#'+_pageDiv).find('#_nextPage').addClass('un').removeClass('act');
				$('#'+_pageDiv).find('#_lastPage').addClass('un').removeClass('act');	
			}
		}
		
		function buildPageButton(){
			var buts=opts.buttons;
			if(!buts){
				return;
			}
			if(urlParam.type=='selectMulti'||urlParam.type=='select'){
				buts=[
					   {name:"选择",bclass: 'select',onpress :  function(value,record){
					   		if(!record[0].NAME_OF_ID){
					   			record[0].NAME_OF_ID=record[0].ID;
					   		}
						   parent.window.returnResult=record;
						   T9.dialogClose();
					   },checkedRow:'single'},      
					   {name:"取消",bclass: 'cancel',onpress : function(){
						   T9.dialogClose();
					   }}
					];
			};
			var btsDiv='<div class="panelBar"></div>';
			var $btsDivp=$('<ul class="toolBar"></ul>');
			for(var i=0;i<buts.length;i++){	
				var btn=buts[i];
				$btsDivp.append($('<li></li>').append(createNormalButton(btn)));
			}
			var $btsDiv=$(btsDiv).append($btsDivp);
			$placeholder.append($btsDiv);
		}
		
		function createNormalButton(btn, isGroupItem) {
			var btnDiv=document.createElement('a');
			var $btnDiv = $(btnDiv);
			if (btn.id) {
				btnDiv.id = btn.id;
			}
			if (btn.tip) {
				btnDiv.title = btn.tip;
			}
			if (btn.name) {
				btnDiv.name = btn.name;
				$btnDiv.html('<span>'+btn.name+'</span>');
			}
			btnDiv.onpress = btn.onpress;
			btnDiv.href ='javascript:;';
			if (btn.hide) {
				$btnDiv.css('display', 'none');
			}
			if (btn.bclass) {
				$btnDiv.addClass(btn.bclass);
			}
			if (btn.onpress) {
				$btnDiv.click(function () {
					if(!checkedRowValidate(btn.name,btn.checkedRow))
						return;
					if(btn.confirm){
						T9.confirm("确认要删除选中的信息吗?",function(){
							btnClickFunction(btn.onpress);
						});
					}else{
						btnClickFunction(btn.onpress);
					}
				});
			}
			if (!btn.onpress && btn.bclass=='query') {
				$btnDiv.click(function () {
					grid.populate(true);
				});
			}
			if (btn.bclass=='exp'||btn.bclass=='expAll') {
				$btnDiv.click(function () {
					var $form=$("#exportForm");
					if($form.length==0){
						$("body").append('<form id="exportForm" style="display:none ;" method="post" target="ExportFrame"></form>');
						$form=$("#exportForm");
					}
					$form.attr("action",base_path+opts.url);
					if($("#ExportFrame").length==0){
						$("body").append('<iframe id="ExportFrame" name="ExportFrame" style="display: none"></iframe>');	
					}
					var params={page:opts.page,pagesize:opts.pageSize,renderCols:renderCols};
					$.extend(params, opts.params);	
					if(opts.formId){
						$.extend(params, $("#"+opts.formId).serializeJSON());	
					}
					$.extend(params, {"exportData":"true","headers":opts.columnHeads,'keys':opts.columnKeys});	
					var html='<input type="text" name="_charset_" value="utf-8"/>';
					for(var p in params){
						html+='<input type="text" name="'+p+'" value="'+params[p]+'"/>';
					}
					$form.html(html);
					$form.submit();
				//	grid.populate(true);
				});
			}
			return $btnDiv;
		}
		function btnClickFunction(callFunction){
			var checkedValue=$('#'+placeholderDiv).getCheckedArrayValue();
			var chkv=$placeholder.getCheckedArray();
//			if(btn.checkedRow=="1")
//				chkv=chkv[0];
			if ($.isFunction(callFunction)) {
				callFunction(checkedValue,chkv);
			} else { 
				eval(callFunction + '('+checkedValue+','+chkv+');');
			}
		}
		function checkedRowValidate(name,checkedRow){
			if(checkedRow!='single'&&checkedRow!='multiple'){
				return true;
			}
			var cRow=$placeholder.getCheckedArray().length;
			var tipStr="";
			if(checkedRow=="single"&&cRow!=1){
				if(cRow==0)
					tipStr="您必须选择一行进行"+name;
				else
					tipStr="您必须只选择一行进行"+name;					
			}

			if(checkedRow=="multiple"&&cRow==0){
				tipStr="您必须选择一行或多行进行"+name;
			}
			if(tipStr!=""){
				T9.warn(tipStr+"!");
				return false;
			}
			return true;
		}
		function buildTableHead(){
			var cols=opts.columns;
			if(!cols||cols.length==0){
				return;
			}
			var titleDiv='<table id="'+_dataGridTable+'" class="table" width="100%"><thead><tr>';
			var baifei=false;
			for(var i=0;i<cols.length;i++){	
				if(/^\d*%$/.test(cols[i].width)){
					baifei=true;
					break;
				}
			}
			if(baifei){
				var totlewidth=$placeholder.width();
				var sWidth=0;
				for(var i=0;i<cols.length;i++){	
					if(/^\d*%$/.test(cols[i].width)){
						sWidth+=parseInt(cols[i].width.substring(0,cols[i].width.length-1));
					}
				}
				sWidth=100/sWidth;
				var avgWidth=Math.round(totlewidth/cols.length);
				if(cols[0].ctrl){
					cols[0].width='22';
					avgWidth=Math.round((totlewidth-22)/(cols.length-1));
				}
				var lastwidth=totlewidth;
				for(var i=0;i<cols.length-1;i++){	
					if(/^\d*%$/.test(cols[i].width)){
						var tw=parseInt(cols[i].width.substring(0,cols[i].width.length-1));
						cols[i].width=Math.round(tw/100*sWidth*totlewidth);
					}else{
						if(!/^\d*$/.test(cols[i].width)&&!(/^\d*px$/).test(cols[i].width)){
							cols[i].width=avgWidth;
						}
					}
					lastwidth=lastwidth-cols[i].width;
				}
				cols[cols.length-1].width=lastwidth;
			}
			var totlewidth=$placeholder.width();
			var lastwidth=100;
			for(var i=0;i<cols.length-1;i++){
				if(/^\d*$/.test(cols[i].width)){
					var bwith=Math.round(cols[i].width/totlewidth*100);
					lastwidth=lastwidth-bwith;
					cols[i].width=bwith+'%';
				}
			}
			cols[cols.length-1].width=lastwidth+'%';
			var columnHeads='',columnKeys='';
			var i=0;
			if(cols[0].ctrl){
				opts.ctrl=cols[0].ctrl;
				if(!opts.treegrid){
					if(opts.ctrl=='checkbox')
						titleDiv+='<th  width="'+cols[0].width+'"><input type="checkbox" name="ctrl" id="ctrl"/></td>';
					else{
						titleDiv+='<th  width="'+cols[0].width+'">'+cols[0].header+'</td>'; 
						if(!opts.ctrl){
							columnHeads+=cols[0].header+",";
							columnKeys+=cols[0].key+",";
						}
					}
				}
				i++;
			}
			for(;i<cols.length;i++){
				titleDiv+='<th nowrap="nowrap" width="'+cols[i].width+'">'+cols[i].header+'</td>';
				columnHeads+=cols[i].header+",";
				columnKeys+=cols[i].key+",";
			}
			opts.columnHeads=columnHeads;
			opts.columnKeys=columnKeys;
			titleDiv+='</tr></thead><tbody></tbody></table>';
			$placeholder.find('#'+_dataDiv).append(titleDiv);
		}
		
		//make grid functions accessible
		placeholder.opts = opts;
		placeholder.grid = grid;
		
		if (opts.autoload) {
			grid.populate(true);
		}
	};
	

	$.fn.getCheckedArray = function() { 
			var obj=this[0];
			if (obj.grid) {
				var cs=$(obj).find('tbody :checked');
				var rr=[];
				for(var i=0;i<cs.length;i++){
					var kk=$(cs[i]).attr('rel');
					rr.push(obj.opts.gridData[kk]);
				}
				return rr;
			}
	};
	
	$.fn.getCheckedArrayValue = function() { 
			var obj=this[0];
			if (obj.grid) {
				var cs=$(obj).find('tbody :checked');
			//	var rr=[];
				var rr='';
				for(var i=0;i<cs.length;i++){
					rr=rr+cs[i].value+",";
				}
				if(rr.length>0)
					rr=rr.substring(0,rr.length-1);
				return rr;
			}
	}; //end gridReload
	

	$.fn.setNoData = function(options) {
		return this.each( function() {
			if (this.grid) {
				this.grid.setNoData();
			}
		});
	};
	
	/**
	 * function to reload grid
	 */
	$.fn.gridReload = function(str) {
		var refresh=str?false:true;
		return this.each( function() {
			if (this.grid) {
				this.grid.populate(refresh);
			}
		});
	}; //end gridReload
	/**
	 * function to update general options
	 */
	$.fn.gridOptions = function(options) { 
		var optParam=options;
		if(!options.params){
			optParam={params:options};
		}
		return this.each( function() {
			if (this.grid) {
				$.extend(this.opts, optParam);
			}
		});
	}; //end gridOptions
	
	var docloaded = false; // 确保HTML加载完毕

	$(document).ready(function() {docloaded = true} );
	
	/**
	 * 该插件的主方法
	 */
	$.fn.frontgrid = function(options) {
		return this.each(function() {
			if (!docloaded) {
				$(this).hide();
				var t = this;
				$(document).ready(function() {
					$.addFrontgrid(this, options);
				});
			} else {
				$.addFrontgrid(this, options);
			}
		});
	};
})(jQuery);
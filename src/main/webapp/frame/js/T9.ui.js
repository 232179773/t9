function initUI(_box){
	var $p = $(_box || document);

	$("button.close",$p).click(function(){
		T9.dialogClose();
	});
	$('input.my97Date', $p).each(function(){
		var $this = $(this);
		var opts = {};
		if ($this.attr("dateFmt")) opts.dateFmt = $this.attr("dateFmt");
		if ($this.attr("minDate")) opts.minDate = $this.attr("minDate");
		if ($this.attr("maxDate")) opts.maxDate = $this.attr("maxDate");
		if ($this.attr("mmStep")) opts.mmStep = $this.attr("mmStep");
		if ($this.attr("ssStep")) opts.ssStep = $this.attr("ssStep");
		$this.click(function(){
			opts.el=undefined;
			WdatePicker(opts);
		});
//		$this.after($('<a class="inputDateButton" href="javascript:;">选择</a>').click(function(){
//			var id=$this.attr('id');
//			if(id){			
//				opts.el=$dp.$(id);
//				WdatePicker(opts);
//			}
//		}));
	});
	
	if ($.fn.xheditor) {
		richEditUI($p);
	}
	

	if($(".fileUpload", $p).length>0&!$.fn.uploadify){		
			$.getScript(base_path+"frame/uploadify/scripts/jquery.uploadify.min.js", function(){
				uploadFileUI($p);
		});
	}else if($(".fileUpload", $p).length>0){
		uploadFileUI($p);
	}	
	
	$(":input[dataSrc]",$p).dataInit();
}


function richEditUI($p){
	$("textarea.editor", $p).each(function(){
		var $this = $(this);
		var op = {html5Upload:false, skin: 'vista',tools: $this.attr("tools") || 'full'};
		var upAttrs = [
			["upLinkUrl","upLinkExt","zip,rar,txt"],
			["upImgUrl","upImgExt","jpg,jpeg,gif,png"],
			["upFlashUrl","upFlashExt","swf"],
			["upMediaUrl","upMediaExt","avi"]
		];
		
		$(upAttrs).each(function(i){
			var urlAttr = upAttrs[i][0];
			var extAttr = upAttrs[i][1];
			
			if ($this.attr(urlAttr)) {
				op[urlAttr] = $this.attr(urlAttr);
				op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
			}
		});
		
		$this.xheditor(op);
	});
}
function uploadFileUI($p){
	$(".fileUpload", $p).each(function(){
		var $this = $(this);
		var options = {
			swf:base_path+'/frame/uploadify/scripts/uploadify.swf',
			uploader:base_path+'service/appendix!saveAppendix',
			fileObjName: "inputfile",
			auto: true,
			buttonText:'选择',
			onUploadError: function(file, errorCode, errorMsg){
//				alertMsg.error(errorCode+": "+errorMsg);
			}
		};
		var uploaderOption ={
				formData:{TABLE_NAME:$this.attr('tableName'), DATA_ID:$this.attr('tableId')},
				fileSizeLimit:'200KB',
				fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
				fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
				auto:true,
				multi:$this.attr('multi')=='false'?false:true,
				onUploadSuccess:function(file, data, response){
					if($this.attr('success')){
						var result=eval('(' + data + ')');
						var fs=$this.attr('success');
						var fname=fs.substring(0,fs.indexOf('('));
						eval(fname+'("'+result.obj.id+'","'+result.obj.file_URL+'")');
					}
				},
				onQueueComplete:uploadFileQueueComplete
			};
		$.extend(options, uploaderOption);
		$this.uploadify(options);
	});
} 

function uploadFileQueueComplete(queueData){

	var msg = "The total number of files uploaded: "+queueData.uploadsSuccessful+"<br/>"
		+ "The total number of errors while uploading: "+queueData.uploadsErrored+"<br/>"
		+ "The total number of bytes uploaded: "+queueData.queueBytesUploaded+"<br/>"
		+ "The average speed of all uploaded files: "+queueData.averageSpeed;
	
	if (queueData.uploadsErrored) {
//		alertMsg.error(msg);
	} else {
//		alertMsg.correct(msg);
	}
}


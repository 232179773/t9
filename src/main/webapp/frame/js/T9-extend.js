String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*)$/g,"");
}

String.prototype.replaceAll=function(srcStr,destStr){
	return this.replace(new RegExp(srcStr,"gm"),destStr);
}

String.prototype.startWith=function(str){     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
}  

String.prototype.endWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
}
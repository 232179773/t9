package com.t9.system.web;

public class T9Result {

	private boolean isSuccess=true;

	private String message;
	
	private Object obj;
	

	public T9Result() {
	}
	public T9Result(Object obj) {
		this.obj = obj;
	}
	public T9Result(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public T9Result(boolean isSuccess, String message, Object obj) {
		this.isSuccess = isSuccess;
		this.message = message;
		this.obj = obj;
	}

	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setResult(boolean isSuccess,String message){
		setResult(isSuccess,message,null);
	}
	
	public void setResult(boolean isSuccess,String message,Object obj){
		this.isSuccess = isSuccess;
		this.message = message;
		this.obj = obj;
	}
	public String toString(){
		if(message==null){
			if(isSuccess)
				message="save successed";
			else
				message="error";
		}
		String result=isSuccess+"@@"+message+"@@"+obj;
		
		return result;
	}
}

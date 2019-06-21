package com.t9.system.web;


import java.io.Serializable;

/**
 * 错误基类,用于用户自定义错误
 * @author HUSQ
 *
 */
public class T9Exception extends Exception implements Serializable {

    private static final long serialVersionUID = 3988541965370355253L;

	private Throwable cause;
    private String[] args;

    public T9Exception(String errorMssage, String... args) {
        super(errorMssage);
        this.args = args;
    }

    public T9Exception(String errorMssage) {
        super(errorMssage);
    }
    

	public T9Exception(String errorMssage, Throwable cause) {
		super(errorMssage, cause);
		this.cause=cause;
	}
	
	public T9Exception(Throwable cause) {
		super(cause);
		this.cause=cause;
	}

    public String[] getArgs() {
        return args;
    }
    public void setArgs(String[] args) {
        this.args = args;
    }

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}

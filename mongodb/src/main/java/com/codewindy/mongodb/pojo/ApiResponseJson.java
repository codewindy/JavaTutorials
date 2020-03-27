package com.codewindy.mongodb.pojo;

import java.io.Serializable;

/**
 * api 通用返回json格式数据
 * 
 * @author yui
 *
 */
public class ApiResponseJson implements Serializable {
	private static final long serialVersionUID = -8134781164681812987L;
	private boolean success;
	private String error;
	private int errorCode;
	private Object data;

	public ApiResponseJson() {
		this.success = true;
		this.error = null;
		this.errorCode = ErrorEnum.SUCCESS.getKey();
		this.data = null;
	}

	public ApiResponseJson(Object data) {
		this.success = true;
		this.error = null;
		this.errorCode = ErrorEnum.SUCCESS.getKey();
		this.data = data;
	}
	public ApiResponseJson(boolean success, String error, int errCode, Object data) {
		super();
		this.success = true;
		this.error = error;
		this.errorCode = 400;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

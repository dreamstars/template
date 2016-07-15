
package com.tuya.webx.web.base.common;

public class ScreenResult {

	private String	errMsg;	//错误信息

	private String	url;		//转到外部网站

	private String	forward;	//内部转发

	private String	Code;

	private Boolean	isSuccess;	//是否成功

	public ScreenResult() {
		this.Code = "success";
		this.isSuccess = true;
	}

	public ScreenResult(String errMsg, String forward) {
		this.errMsg = errMsg;
		this.Code = "error";
		this.forward = forward;
		this.isSuccess = false;
	}

	public ScreenResult(String errMsg) {
		this.errMsg = errMsg;
		this.Code = "error";
		this.isSuccess = false;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
		if (code.equals("error")) {
			this.isSuccess = false;
		}
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}

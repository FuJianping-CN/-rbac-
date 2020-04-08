package com.iotek.user.po;
/**
 * AjaxResult 对象表示返回的json格式
 * @author Administrator
 *
 */
public class AjaxResult {
    private boolean success;
    public boolean isSuccess(){
    	return success;
    }
    public void setSuccess(boolean success){
    	this.success=success;
    }
	@Override
	public String toString() {
		return "AjaxResult [success=" + success + "]";
	}
    
}

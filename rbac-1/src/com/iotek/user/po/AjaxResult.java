package com.iotek.user.po;
/**
 * AjaxResult �����ʾ���ص�json��ʽ
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.error.custom.exception;

/**
 *
 * @author megaweb
 */
public class DuplicateDataException extends Exception{
    
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;

	public DuplicateDataException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
    
}

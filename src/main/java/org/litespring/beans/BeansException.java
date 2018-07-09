package org.litespring.beans;

public class BeansException extends RuntimeException {

	public BeansException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BeansException(String msg) {
		super(msg);
	}
	
}

package com.cgg.pub.common.listener;

import org.springframework.context.ApplicationEvent;

public class TestEmailEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1016512318397518236L;

	public String address;
	
	public String text;
	
	public TestEmailEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public TestEmailEvent(Object source, String address, String text) {
		super(source);
		this.address = address;
		this.text = text;
	}

	public void print(){
		System.out.println("-------------email spring event!----------------");
	}
	
}

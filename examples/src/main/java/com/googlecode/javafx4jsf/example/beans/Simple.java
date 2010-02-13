package com.googlecode.javafx4jsf.example.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Simple {
	private String text = "Welcome from JSF2";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String bind() {
		return null;
	}
}

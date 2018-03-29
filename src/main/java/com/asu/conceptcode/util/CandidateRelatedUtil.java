package com.asu.conceptcode.util;

public class CandidateRelatedUtil {
	private String rxcui;
	private String name;
	private String synonym;
	private String tty;
	private String umlscui;
	public String getRxcui() {
		return rxcui;
	}
	public void setRxcui(String rxcui) {
		this.rxcui = rxcui;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSynonym() {
		return synonym;
	}
	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}
	public String getTty() {
		return tty;
	}
	public void setTty(String tty) {
		this.tty = tty;
	}
	public String getUmlscui() {
		return umlscui;
	}
	public void setUmlscui(String umlscui) {
		this.umlscui = umlscui;
	}
	@Override
	public String toString() {
		return "CandidateRelatedUtil [rxcui=" + rxcui + ", name=" + name + ", tty=" + tty
				+ ", umlscui=" + umlscui + "]";
	}	
	
}



package com.asu.conceptcode.util;

import java.util.List;
import java.util.Set;

public class RxnormResult {
	private String term;
	private Set<CandidateUtil> rxcuiSet;
	
	private boolean isSensitive;
	private String sensitiveCategory;
	private String sensitiveTerm;
	private String sensitiveCode;
	private String sensitiveTty;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Set<CandidateUtil> getRxcuiSet() {
		return rxcuiSet;
	}
	public void setRxcuiSet(Set<CandidateUtil> rxcuiSet) {
		this.rxcuiSet = rxcuiSet;
	}
	public String getSensitiveTerm() {
		return sensitiveTerm;
	}
	public void setSensitiveTerm(String sensitiveTerm) {
		this.sensitiveTerm = sensitiveTerm;
	}
	public boolean isSensitive() {
		return isSensitive;
	}
	public void setSensitive(boolean isSensitive) {
		this.isSensitive = isSensitive;
	}
	public String getSensitiveCategory() {
		return sensitiveCategory;
	}
	public void setSensitiveCategory(String sensitiveCategory) {
		this.sensitiveCategory = sensitiveCategory;
	}
	public String getSensitiveCode() {
		return sensitiveCode;
	}
	public void setSensitiveCode(String sensitiveCode) {
		this.sensitiveCode = sensitiveCode;
	}
	public String getSensitiveTty() {
		return sensitiveTty;
	}
	public void setSensitiveTty(String sensitiveTty) {
		this.sensitiveTty = sensitiveTty;
	}
	@Override
	public String toString() {
		return "RxnormResult [term=" + term + ", rxcuiSet=" + rxcuiSet + ", isSensitive=" + isSensitive
				+ ", sensitiveCategory=" + sensitiveCategory + ", sensitiveTerm=" + sensitiveTerm + ", sensitiveCode="
				+ sensitiveCode + ", sensitiveTty=" + sensitiveTty + "]";
	}
	
}

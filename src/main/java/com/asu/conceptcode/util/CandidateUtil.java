package com.asu.conceptcode.util;

public class CandidateUtil {
	private String rxcui;
	private String rxaui;
	private String score;
	private String rank;
	private String umlscui;
	private String tty;
	
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
	public String getRxcui() {
		return rxcui;
	}
	public void setRxcui(String rxcui) {
		this.rxcui = rxcui;
	}
	public String getRxaui() {
		return rxaui;
	}
	public void setRxaui(String rxaui) {
		this.rxaui = rxaui;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rxcui == null) ? 0 : rxcui.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateUtil other = (CandidateUtil) obj;
		if (rxcui == null) {
			if (other.rxcui != null)
				return false;
		} else if (!rxcui.equals(other.rxcui))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CandidateUtil [rxcui=" + rxcui + ", rxaui=" + rxaui + ", score=" + score + ", rank=" + rank
				+ "]";
	}
}

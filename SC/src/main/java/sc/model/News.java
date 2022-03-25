package sc.model;

import java.util.Date;

public class News {
	
	private int NEWSIDX;
	private String TYPE;
	private String TITLE;
	private String CONTENT;
	private Date NEWSDATE;
	private String ID;
	private String ISDEL;
	private int READCOUNT;
	private int GOOD;
	private int BAD;
	
	
	public int getNEWSIDX() {
		return NEWSIDX;
	}
	public void setNEWSIDX(int nEWSIDX) {
		NEWSIDX = nEWSIDX;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getNEWSDATE() {
		return NEWSDATE;
	}
	public void setNEWSDATE(Date nEWSDATE) {
		NEWSDATE = nEWSDATE;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getISDEL() {
		return ISDEL;
	}
	public void setISDEL(String iSDEL) {
		ISDEL = iSDEL;
	}
	public int getREADCOUNT() {
		return READCOUNT;
	}
	public void setREADCOUNT(int rEADCOUNT) {
		READCOUNT = rEADCOUNT;
	}
	public int getGOOD() {
		return GOOD;
	}
	public void setGOOD(int gOOD) {
		GOOD = gOOD;
	}
	public int getBAD() {
		return BAD;
	}
	public void setBAD(int bAD) {
		BAD = bAD;
	}
	
	

}

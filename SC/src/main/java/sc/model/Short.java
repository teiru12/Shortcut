package sc.model;

import java.util.Date;

public class Short {
	
	private int SHORTIDX;
	private String TYPE;
	private String TITLE;
	private String CONTENT;
	private Date SHORTDATE;
	private int READCOUNT;
	private int GOOD;
	private int BAD;
	private String STYPE;
	
	public int getSHORTIDX() {
		return SHORTIDX;
	}
	public void setSHORTIDX(int sHORTIDX) {
		SHORTIDX = sHORTIDX;
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
	public Date getSHORTDATE() {
		return SHORTDATE;
	}
	public void setSHORTDATE(Date sHORTDATE) {
		SHORTDATE = sHORTDATE;
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
	public String getSTYPE() {
		return STYPE;
	}
	public void setSTYPE(String sTYPE) {
		STYPE = sTYPE;
	}
}

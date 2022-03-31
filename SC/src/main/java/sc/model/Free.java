package sc.model;

import java.util.Date;

public class Free {

	private int FREEIDX;
	private String TYPE;
	private String TITLE;
	private String CONTENT;
	private Date FREEDATE;
	private String ID;
	private String IP;
	private String PASSWORD;
	private String ISDEL;
	private int READCOUNT;
	private int GOOD;
	private int BAD;
	
	public int getFREEIDX() {
		return FREEIDX;
	}
	public void setFREEIDX(int fREEIDX) {
		FREEIDX = fREEIDX;
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
	public Date getFREEDATE() {
		return FREEDATE;
	}
	public void setDATE(Date fREEDATE) {
		FREEDATE = fREEDATE;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
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

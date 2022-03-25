package sc.model;

import java.util.Date;

public class Info {

	private int INFOIDX;
	private String TYPE;
	private String TITLE;
	private String CONTENT;
	private Date INFODATE;
	private String ID;
	private String IP;
	private String PASSWORD;
	private String ISDEL;
	private int READCOUNT;
	private int GOOD;
	private int BAD;
	
	public int getINFOIDX() {
		return INFOIDX;
	}
	public void setINFOIDX(int iNFOIDX) {
		INFOIDX = iNFOIDX;
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
	public Date getINFODATE() {
		return INFODATE;
	}
	public void setINFODATE(Date iNFODATE) {
		INFODATE = iNFODATE;
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
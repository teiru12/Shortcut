package sc.model;

import java.util.Date;

public class InfoCom {
	private int INFOCOMIDX;
	private int INFOIDX;
	private String CONTENT;
	private Date INFOCOMDATE;
	private String ID;
	private String IP;
	private String PASSWORD;
	private int RETYPE;
	private int RESTEP;
	private int RELEVEL;
	private String ISDEL;
	
	public int getINFOCOMIDX() {
		return INFOCOMIDX;
	}
	public void setINFOCOMIDX(int iNFOCOMIDX) {
		INFOCOMIDX = iNFOCOMIDX;
	}
	public int getINFOIDX() {
		return INFOIDX;
	}
	public void setINFOIDX(int iNFOIDX) {
		INFOIDX = iNFOIDX;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getINFOCOMDATE() {
		return INFOCOMDATE;
	}
	public void setINFOCOMDATE(Date iNFOCOMDATE) {
		INFOCOMDATE = iNFOCOMDATE;
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
	public int getRETYPE() {
		return RETYPE;
	}
	public void setRETYPE(int rETYPE) {
		RETYPE = rETYPE;
	}
	public int getRESTEP() {
		return RESTEP;
	}
	public void setRESTEP(int rESTEP) {
		RESTEP = rESTEP;
	}
	public int getRELEVEL() {
		return RELEVEL;
	}
	public void setRELEVEL(int rELEVEL) {
		RELEVEL = rELEVEL;
	}
	public String getISDEL() {
		return ISDEL;
	}
	public void setISDEL(String iSDEL) {
		ISDEL = iSDEL;
	}
}
package sc.model;

import java.util.Date;

public class FreeCom {

	private int FREECOMIDX;
	private int FREEIDX;
	private String CONTENT;
	private Date FREECOMDATE;
	private String ID;
	private String IP;
	private String PASSWORD;
	private int RETYPE;
	private int RESTEP;
	private int RELEVEL;
	private int PCOMIDX;

	private String ISDEL;
	
	public int getFREECOMIDX() {
		return FREECOMIDX;
	}
	public void setFREECOMIDX(int fREECOMIDX) {
		FREECOMIDX = fREECOMIDX;
	}
	public int getFREEIDX() {
		return FREEIDX;
	}
	public void setFREEIDX(int fREEIDX) {
		FREEIDX = fREEIDX;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getFREECOMDATE() {
		return FREECOMDATE;
	}
	public void setFREECOMDATE(Date fREECOMDATE) {
		FREECOMDATE = fREECOMDATE;
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
	public int getPCOMIDX() {
		return PCOMIDX;
	}
	public void setPCOMIDX(int pCOMIDX) {
		PCOMIDX = pCOMIDX;
	}
	public String getISDEL() {
		return ISDEL;
	}
	public void setISDEL(String iSDEL) {
		ISDEL = iSDEL;
	}
}
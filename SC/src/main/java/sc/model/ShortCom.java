package sc.model;

import java.util.Date;

public class ShortCom {

	private int SHORTCOMIDX;
	private int SHORTIDX;
	private String CONTENT;
	private Date SHORTCOMDATE;
	private String ID;
	private String IP;
	private String PASSWORD;
	
	public int getSHORTCOMIDX() {
		return SHORTCOMIDX;
	}
	public void setSHORTCOMIDX(int sHORTCOMIDX) {
		SHORTCOMIDX = sHORTCOMIDX;
	}
	public int getSHORTIDX() {
		return SHORTIDX;
	}
	public void setSHORTIDX(int sHORTIDX) {
		SHORTIDX = sHORTIDX;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getSHORTCOMDATE() {
		return SHORTCOMDATE;
	}
	public void setSHORTCOMDATE(Date sHORTCOMDATE) {
		SHORTCOMDATE = sHORTCOMDATE;
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
}
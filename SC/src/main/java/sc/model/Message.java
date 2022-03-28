package sc.model;

import java.util.Date;

public class Message {

	private int MSGIDX;
	private String SENDID;
	private String GETID;
	private Date SENDDATE;
	private String CONTENT;
	private String ISDEL;
	
	public int getMSGIDX() {
		return MSGIDX;
	}
	public void setMSGIDX(int mSGIDX) {
		MSGIDX = mSGIDX;
	}
	public String getSENDID() {
		return SENDID;
	}
	public void setSENDID(String sENDID) {
		SENDID = sENDID;
	}
	public String getGETID() {
		return GETID;
	}
	public void setGETID(String gETID) {
		GETID = gETID;
	}
	public Date getSENDDATE() {
		return SENDDATE;
	}
	public void setSENDDATE(Date sENDDATE) {
		SENDDATE = sENDDATE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getISDEL() {
		return ISDEL;
	}
	public void setISDEL(String iSDEL) {
		ISDEL = iSDEL;
	}
}
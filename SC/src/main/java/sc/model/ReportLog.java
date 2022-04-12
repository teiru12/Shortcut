package sc.model;

import java.util.Date;

public class ReportLog {
	private int REPORTLOG_IDX;
	private String TYPE;
	private int IDX;
	private String ID;
	private Date REPORTDATE;
	
	public int getREPORTLOG_IDX() {
		return REPORTLOG_IDX;
	}
	public void setREPORTLOG_IDX(int rEPORTLOG_IDX) {
		REPORTLOG_IDX = rEPORTLOG_IDX;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public int getIDX() {
		return IDX;
	}
	public void setIDX(int iDX) {
		IDX = iDX;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Date getREPORTDATE() {
		return REPORTDATE;
	}
	public void setREPORTDATE(Date rEPORTDATE) {
		REPORTDATE = rEPORTDATE;
	}	
}
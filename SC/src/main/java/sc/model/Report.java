package sc.model;

import java.sql.Date;

public class Report {
	
	private int REPORTIDX; /* 신고게시판 식별번호 */
	private String TYPE; /* 타입 */
	private String TITLE; /* 제목 */
	private String CONTENT; /* 내용 */
	private Date REPORTDATE; /* 날짜 */
	private String ID; /* 작성자 */
	private String ISDEL; /* 삭제여부 Y : 삭제됨 N : 삭제안됨 */
	
	
	public int getREPORTIDX() {
		return REPORTIDX;
	}
	public void setREPORTIDX(int rEPORTIDX) {
		REPORTIDX = rEPORTIDX;
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
	public Date getREPORTDATE() {
		return REPORTDATE;
	}
	public void setREPORTDATE(Date rEPORTDATE) {
		REPORTDATE = rEPORTDATE;
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

	
	
}
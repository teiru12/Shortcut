package sc.model;

import java.sql.Date;

public class ReportCom {
	
	private int REPORTCOMIDX; /* 신고 댓글 식별번호 */
	private int REPORTIDX; /* 신고 게시글 번호 */
	private String CONTENT; /* 신고 댓글 내용 */
	private Date REPORTCOMDATE; /* 날짜 */
	
	
	public int getREPORTCOMIDX() {
		return REPORTCOMIDX;
	}
	public void setREPORTCOMIDX(int rEPORTCOMIDX) {
		REPORTCOMIDX = rEPORTCOMIDX;
	}
	public int getREPORTIDX() {
		return REPORTIDX;
	}
	public void setREPORTIDX(int rEPORTIDX) {
		REPORTIDX = rEPORTIDX;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getREPORTCOMDATE() {
		return REPORTCOMDATE;
	}
	public void setREPORTCOMDATE(Date rEPORTCOMDATE) {
		REPORTCOMDATE = rEPORTCOMDATE;
	}

	
	
}
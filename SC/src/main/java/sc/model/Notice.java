package sc.model;

import java.sql.Date;

public class Notice {
	
	private int NOTICEIDX; /* 공지게시판 식별번호 */
	private String TYPE; /* 타입 */
	private String TITLE; /* 제목 */
	private String CONTENT; /* 내용 */
	private Date NOTICEDATE; /* 날짜 */
	private String ISDEL; /* 삭제여부 Y : 삭제됨 N : 삭제안됨 */
	private int READCOUNT; /* 조회수 카운트 */
	private int GOOD; /* 좋아요 카운트 */
	private int BAD; /* 싫어요 카운트 */
	
	
	public int getNOTICEIDX() {
		return NOTICEIDX;
	}
	public void setNOTICEIDX(int nOTICEIDX) {
		NOTICEIDX = nOTICEIDX;
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
	public Date getNOTICEDATE() {
		return NOTICEDATE;
	}
	public void setNOTICEDATE(Date nOTICEDATE) {
		NOTICEDATE = nOTICEDATE;
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

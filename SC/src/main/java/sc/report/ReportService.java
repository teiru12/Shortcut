package sc.report;

import java.util.List;

import sc.model.Report;
import sc.model.ReportCom;

public interface ReportService {
	
	/*신고 게시판 리스트 페이징*/
	public List<Report> reportListPaging(int START, int END) throws Exception;
	
	/* 신고 게시판 카운트 */
	public int countReportList() throws Exception;
	
	/* 신고 게시판 입력 */
	public void insertReportList(Report report) throws Exception;
	
	/* 신고 게시판 수정 */
	public void updateReportList(Report report) throws Exception;
	
	/* 신고 게시판 수정(삭제) */
	public void updateReportListDel(int REPORTIDX) throws Exception;
	
	/* 신고 게시판 선택 */
	public Report selectReportIDX(int REPORTIDX) throws Exception;
	
	/* 신고 게시판 댓글 */
	
	
	
	/* 신고 게시판 댓글 리스트 */
	public List<ReportCom> reportListComByReportIDX(int REPORTIDX) throws Exception;
	
	/* 신고 게시판 댓글 입력 BY 게시글번호 */
	public void insertReportListComByReportIDX(ReportCom reportCom) throws Exception;
	
	/* 신고 게시판 댓글 수정 BY 게시글번호 */
	public void updateReportListComByReportIDX(ReportCom reportCom) throws Exception;
	
	/* 신고 게시판 댓글 수정 (삭제) BY 게시글번호 */
	public void updateReportListComDelByReportIDX(int REPORTCOMIDX) throws Exception;

}

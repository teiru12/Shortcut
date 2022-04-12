package sc.report;

import java.util.List;

import sc.model.Report;
import sc.model.ReportCom;
import sc.model.ReportLog;

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
	
	/* 신고 게시판 댓글 삭제 BY 게시글번호 */
	public void deleteReportListComByReportIDX(int REPORTCOMIDX) throws Exception;

	
	/* 신고 카운트 */
	/* 신고 횟수 초기화 생성 */
	public void insertCountReport(String TYPE, int IDX) throws Exception;
	
	/* 신고 횟수 증가 */
	public void updateCountReport(String TYPE, int IDX) throws Exception;
	
	/* 신고 횟수 조회 */
	public int selectCountReport(String TYPE, int IDX) throws Exception;
	
	/* 신고 로그 */
	/* 신고 로그 조회 */
	public ReportLog selectReportLog(String TYPE, int IDX, String ID) throws Exception;
	
	/* 신고 로그 입력 */
	public void insertReportLog(String TYPE, int IDX, String ID) throws Exception;
	
	/* 자유 게시판의 최근 입력 게시글 번호 */
	public int selectMaxFREEIDX() throws Exception;
	
	/* 정보교류 게시판의 최근 입력 게시글 번호 */
	public int selectMaxINFOIDX() throws Exception;
	
	/* 뉴스 게시판의 최근 입력 게시글 번호 */
	public int selectMaxNEWSIDX() throws Exception;
}
package sc.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Report;
import sc.model.ReportCom;
import sc.model.ReportLog;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
	
	@Resource(name="reportDAO")
	private ReportDAO reportDAO;

	/*신고 게시판 리스트 페이징*/
	@Override
	public List<Report> reportListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return reportDAO.reportListPaging(map);
	}

	/* 신고 게시판 카운트 */
	@Override
	public int countReportList() throws Exception {
		return Integer.parseInt(String.valueOf(reportDAO.countReportList()));
	}

	/* 신고 게시판 입력 */
	@Override
	public void insertReportList(Report report) throws Exception {
		reportDAO.insertReportList(report);
		
	}

	/* 신고 게시판 수정 */
	@Override
	public void updateReportList(Report report) throws Exception {
		reportDAO.updateReportList(report);
		
	}

	/* 신고 게시판 수정(삭제) */
	@Override
	public void updateReportListDel(int REPORTIDX) throws Exception {
		reportDAO.updateReportListDel(REPORTIDX);
		
	}

	/* 신고 게시판 선택 */
	@Override
	public Report selectReportIDX(int REPORTIDX) throws Exception {
		return reportDAO.selectReportIDX(REPORTIDX);
	}
	
	/* 신고 게시판 댓글 */
	/* 신고 게시판 댓글 리스트 */
	@Override
	public List<ReportCom> reportListComByReportIDX(int REPORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("REPORTIDX", REPORTIDX);
		
		return reportDAO.reportListComByReportIDX(REPORTIDX);
	}

	/* 신고 게시판 댓글 입력 BY 게시글번호 */
	@Override
	public void insertReportListComByReportIDX(ReportCom reportCom) throws Exception {
		reportDAO.insertReportListComByReportIDX(reportCom);
	}

	/* 신고 게시판 댓글 수정 BY 게시글번호 */
	@Override
	public void updateReportListComByReportIDX(ReportCom reportCom) throws Exception {
		reportDAO.updateReportListComByReportIDX(reportCom);
	}

	/* 신고 게시판 댓글 삭제 BY 게시글번호 */
	@Override
	public void deleteReportListComByReportIDX(int REPORTCOMIDX) throws Exception {
		reportDAO.deleteReportListComByReportIDX(REPORTCOMIDX);
	}

	/* 신고 카운트 */
	/* 신고 횟수 초기화 생성 */
	@Override
	public void insertCountReport(String TYPE, int IDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		
		reportDAO.insertCountReport(map);
	}
	
	/* 신고 횟수 증가 */
	@Override
	public void updateCountReport(String TYPE, int IDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		
		reportDAO.updateCountReport(map);
	}
	
	/* 신고 횟수 조회 */
	@Override
	public int selectCountReport(String TYPE, int IDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		
		return Integer.parseInt(String.valueOf(reportDAO.selectCountReport(map)));
	}
	
	/* 신고 로그 */
	/* 신고 로그 조회 */
	@Override
	public ReportLog selectReportLog(String TYPE, int IDX, String ID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		map.put("ID", ID);
		
		return reportDAO.selectReportLog(map);
	}
	
	/* 신고 로그 입력 */
	@Override
	public void insertReportLog(String TYPE, int IDX, String ID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		map.put("ID", ID);
		
		reportDAO.insertReportLog(map);
	}	
	
	/* 자유 게시판의 최근 입력 게시글 번호 */
	public int selectMaxFREEIDX() throws Exception {
		return Integer.parseInt(String.valueOf(reportDAO.selectMaxFREEIDX()));
	}
	
	/* 정보교류 게시판의 최근 입력 게시글 번호 */
	public int selectMaxINFOIDX() throws Exception {
		return Integer.parseInt(String.valueOf(reportDAO.selectMaxINFOIDX()));
	}
	
	/* 뉴스 게시판의 최근 입력 게시글 번호 */
	public int selectMaxNEWSIDX() throws Exception {
		return Integer.parseInt(String.valueOf(reportDAO.selectMaxNEWSIDX()));
	}
}
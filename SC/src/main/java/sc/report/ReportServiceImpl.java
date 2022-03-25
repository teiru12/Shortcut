package sc.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Report;
import sc.model.ReportCom;

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

	/* 신고 게시판 댓글 수정 (삭제) BY 게시글번호 */
	@Override
	public void updateReportListComDelByReportIDX(int REPORTCOMIDX) throws Exception {
		reportDAO.updateReportListComDelByReportIDX(REPORTCOMIDX);
		
	}

	
	}
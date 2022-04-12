package sc.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Notice;
import sc.model.Report;
import sc.model.ReportCom;
import sc.notice.NoticeService;
import sc.util.Paging;

@Controller
public class ReportController {

	@Resource(name = "reportService")
	private ReportService reportService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
		
	@RequestMapping(value = "/reportList.cut")
	public String reportList(HttpServletRequest request, Model model) throws Exception {
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 게시글 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1;
		
		int reportCount = reportService.countReportList();
		int pageBlock = 10;
		String url = "reportList.cut";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(reportCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Report> reportList = new ArrayList<Report>();
		List<Notice> noticeTopList = new ArrayList<Notice>();
		
		reportList = reportService.reportListPaging(START, END);

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		/* 상단 공지사항 불러오기 및 조건 */
		noticeTopList = noticeService.noticeListPaging(1, 3);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("noticeTopList", noticeTopList);
		
		return "reportList";
	}	
	
	@RequestMapping(value = "reportDetail.cut")
	public String reportDetail(int REPORTIDX, ReportCom reportCom, Report report, Model model, HttpServletRequest request) throws Exception {
	
		/* 게시글 상세 정보 읽어옴 */
		Report reportDetail = reportService.selectReportIDX(REPORTIDX);
		
		List<ReportCom> reportComList = new ArrayList<ReportCom>();
		reportComList = reportService.reportListComByReportIDX(REPORTIDX);
		
		model.addAttribute("reportDetail", reportDetail);
		model.addAttribute("reportComList", reportComList);
		
		return "reportDetail";
	}
	
	@ResponseBody
	@RequestMapping(value = "reportWrite.cut")
	public Map<String,String> reportWrite(String TITLE, String CONTENT, HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		Report report = new Report();
		String ID;
		  		
		ID = (String)request.getSession().getAttribute("id");
			
		report.setID(ID);
		report.setTITLE(TITLE);
		report.setCONTENT(CONTENT);
		
		reportService.insertReportList(report);
		
		msg.put("ID", ID);
		
		return msg;
	}
	
	@RequestMapping(value = "reportWriteForm.cut")
	public String reportWriteForm(Model model) throws Exception{
		
		return "reportWriteForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "reportModify.cut")
	public Map<String, String> reportModify(String TITLE, String CONTENT,int REPORTIDX, HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		Report report = new Report();
		
		report.setREPORTIDX(REPORTIDX);
		report.setTITLE(TITLE);
		report.setCONTENT(CONTENT);
		
		reportService.updateReportList(report);
		
		return msg;
	}
	
	@RequestMapping(value = "reportModifyForm.cut")
	public String reportModifyForm(HttpServletRequest request,Model model, int REPORTIDX) throws Exception{
		
		Report report = reportService.selectReportIDX(REPORTIDX);
		
		model.addAttribute("report",report);
		
		return "reportModifyForm";
	}
	
	@ResponseBody
	@RequestMapping("/reportDelete.cut")
	public Map<String, String> reportDelete(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 신고게시판의 게시글 삭제 : ISDEL을 'Y'로 변환
		reportService.updateReportListDel(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/reportComWrite.cut")
	public Map<String, String> reportComWrite(HttpServletRequest request, int REPORTIDX, String CONTENT) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		ReportCom reportCom = new ReportCom();
		
		reportCom.setREPORTIDX(REPORTIDX);
		reportCom.setCONTENT(CONTENT);
		
		reportService.insertReportListComByReportIDX(reportCom);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/reportComModify.cut")
	public Map<String, String> reportComModify(int REPORTCOMIDX, String CONTENT) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		ReportCom reportCom = new ReportCom();
		
		reportCom.setREPORTCOMIDX(REPORTCOMIDX);
		reportCom.setCONTENT(CONTENT);
		
		reportService.updateReportListComByReportIDX(reportCom);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/reportComDelete.cut")
	public Map<String, String> reportComDelete(int REPORTCOMIDX, HttpServletRequest request) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
				
		reportService.deleteReportListComByReportIDX(REPORTCOMIDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/reportArticle.cut")
	public Map<String, Object> reportArticle(String TYPE, int IDX, HttpServletRequest request) throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();
		String id = (String)request.getSession().getAttribute("id");
		
		/* 신고로그 테이블에서 신고를 이미 했는지를 검사 */
		if(reportService.selectReportLog(TYPE, IDX, id) == null) { // 신고 횟수가 없음 신고 가능
		
			/* 신고횟수 저장 테이블의 신고횟수를 증가 */
			reportService.updateCountReport(TYPE, IDX);
			/* 신고로그 테이블에 신고 기록 */
			reportService.insertReportLog(TYPE, IDX, id);
			
			/* 해당 게시글의 신고횟수를 읽어와 msg에 저장 */
			int countReport = reportService.selectCountReport(TYPE, IDX);
			
			msg.put("countReport", countReport);		
		} else { // 이미 이 게시글을 신고 했음
			msg.put("ISREPORTED", "Y");			
		}
		return msg;
	}		
}
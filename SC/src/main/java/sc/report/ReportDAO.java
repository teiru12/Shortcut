package sc.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Report;
import sc.model.ReportCom;
import sc.model.ReportLog;

@Repository("reportDAO")
public class ReportDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/*신고 게시판 리스트 페이징*/
	public List<Report> reportListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("report.reportListPaging", map);
	}
	
	/* 신고 게시판 카운트 */
	public int countReportList() throws Exception {
		return sqlSessionTemplate.selectOne("report.countReportList");
	}
	
	/* 신고 게시판 입력 */
	public void insertReportList(Report report) throws Exception {
		sqlSessionTemplate.insert("report.insertReportList", report);
	}
	
	/* 신고 게시판 수정 */
	public void updateReportList(Report report) throws Exception {
		sqlSessionTemplate.update("report.updateReportList", report);
	}
	
	/* 신고 게시판 삭제 */
	public void updateReportListDel(int REPORTIDX) throws Exception {
		sqlSessionTemplate.update("report.updateReportListDel", REPORTIDX);
	}
	
	/* 신고 게시판 선택 */
	public Report selectReportIDX(int REPORTIDX) throws Exception {
		return sqlSessionTemplate.selectOne("report.selectReportIDX", REPORTIDX);
	}
	
	/* 신고 게시판 댓글 */
	/* 신고 게시판 댓글 리스트 */
	public List<ReportCom> reportListComByReportIDX(int REPORTIDX) throws Exception {
		return sqlSessionTemplate.selectList("report.reportListComByReportIDX", REPORTIDX);
	}
	
	/* 신고 게시판 댓글 입력 BY 게시글번호 */
	public void insertReportListComByReportIDX(ReportCom reportCom) throws Exception {
		sqlSessionTemplate.insert("report.insertReportListComByReportIDX", reportCom);
	}
	
	/* 신고 게시판 댓글 수정 BY 게시글번호 */
	public void updateReportListComByReportIDX(ReportCom reportCom) throws Exception {
		sqlSessionTemplate.update("report.updateReportListComByReportIDX", reportCom);
	}
	
	/* 신고 게시판 댓글 삭제 BY 게시글번호 */
	public void deleteReportListComByReportIDX(int REPORTCOMIDX) throws Exception {
		sqlSessionTemplate.delete("report.deleteReportListComByReportIDX", REPORTCOMIDX);
	}

	/* 신고 카운트 */
	/* 신고 횟수 초기화 생성 */
	public void insertCountReport(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.insert("report.insertCountReport", map);
	}	
	
	/* 신고 횟수 증가 */
	public void updateCountReport(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.update("report.updateCountReport", map);
	}
	
	/* 신고 횟수 조회 */
	public int selectCountReport(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("report.selectCountReport", map);
	}
	
	/* 신고 로그 */
	/* 신고 로그 조회 */
	public ReportLog selectReportLog(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("report.selectReportLog", map);
	}
	
	/* 신고 로그 입력 */
	public void insertReportLog(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.insert("report.insertReportLog", map);
	}	
	
	/* 자유 게시판의 최근 입력 게시글 번호 */
	public int selectMaxFREEIDX() throws Exception {
		return sqlSessionTemplate.selectOne("report.selectMaxFREEIDX");
	}
	
	/* 정보교류 게시판의 최근 입력 게시글 번호 */
	public int selectMaxINFOIDX() throws Exception {
		return sqlSessionTemplate.selectOne("report.selectMaxINFOIDX");
	}
	
	/* 뉴스 게시판의 최근 입력 게시글 번호 */
	public int selectMaxNEWSIDX() throws Exception {
		return sqlSessionTemplate.selectOne("report.selectMaxNEWSIDX");
	}
}
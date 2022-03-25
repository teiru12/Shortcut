package sc.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Report;
import sc.model.ReportCom;

@Repository("reportDAO")
public class ReportDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/*신고 게시판 리스트 페이징*/
	public List<Report> reportListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("report.reportListPaging", map);
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
	
	/* 신고 게시판 수정(삭제) */
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
		return sqlSessionTemplate.selectOne("report.reportListComByReportIDX", REPORTIDX);
	}
	
	/* 신고 게시판 댓글 입력 BY 게시글번호 */
	public void insertReportListComByReportIDX(ReportCom reportCom) throws Exception {
		sqlSessionTemplate.insert("report.insertReportListComByReportIDX", reportCom);
	}
	
	/* 신고 게시판 댓글 수정 BY 게시글번호 */
	public void updateReportListComByReportIDX(ReportCom reportCom) throws Exception {
		sqlSessionTemplate.update("report.updateReportListComByReportIDX", reportCom);
	}
	
	/* 신고 게시판 댓글 수정 (삭제) BY 게시글번호 */
	public void updateReportListComDelByReportIDX(int REPORTCOMIDX) throws Exception {
		sqlSessionTemplate.update("report.updateReportListComDelByReportIDX", REPORTCOMIDX);
	}
}

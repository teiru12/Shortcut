package sc.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Notice;

@Repository("noticeDAO")
public class NoticeDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 공지 게시판 리스트 페이징 */
	public List<Notice> noticeListPaging(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectOne("notice.noticeListPaging", map);
	}
	
	/* 공지 게시판 카운트 */
	public int countNoticeList() throws Exception {
		return sqlSessionTemplate.selectOne("notice.countNoticeList");
	}
	
	/* 공지 게시판 최신 3글 */
	public List<Notice> noticeListThree() throws Exception {
		return sqlSessionTemplate.selectList("notice.noticeListThree");
	}
	
	/* 공지 게시판 상세보기 */
	public Notice selectNoticeIDX(int NOTICEIDX) throws Exception {
		return sqlSessionTemplate.selectOne("notice.selectNoticeIDX", NOTICEIDX);
	}
	
	/* 공지 게시판 입력 */
	public void insertNoticeList(Notice notice) throws Exception {
		sqlSessionTemplate.insert("notice.insertNoticeList", notice);
	}
	
	/* 공지 게시판 수정 */
	public void updateNoticeList(Notice notice) throws Exception {
		sqlSessionTemplate.update("notice.updateNoticeList", notice);
	}
	
	/* 공지 게시판 수정(삭제) */
	public void updateNoticeListDel(int NOTICEIDX) throws Exception {
		sqlSessionTemplate.update("notice.updateNoticeListDel", NOTICEIDX);
	}
	
	/* 공지 게시판 조회수 */
	public void updateNoticeReadcount(int NOTICEIDX) throws Exception {
		sqlSessionTemplate.update("notice.updateNoticeReadcount", NOTICEIDX);
	}
	
	/* 공지 게시판 좋아요 */
	public void updateNoticeGood(int NOTICEIDX) {
		sqlSessionTemplate.update("notice.updateNoticeGood", NOTICEIDX);
	}
	
	/* 공지 게시판 싫어요 */
	public void updateNoticeBad(int NOTICEIDX) {
		sqlSessionTemplate.update("notice.updateNoticeBad", NOTICEIDX);
	}
}

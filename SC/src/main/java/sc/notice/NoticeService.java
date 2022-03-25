package sc.notice;

import java.util.List;

import sc.model.Notice;

public interface NoticeService {
	
	/* 공지 게시판 리스트 페이징 */
	public List<Notice> noticeListPaging(int START, int END) throws Exception;
	
	/* 공지 게시판 카운트 */
	public int countNoticeList() throws Exception;
	
	/* 공지 게시판 최신 3글 */
	public List<Notice> noticeListThree() throws Exception;
	
	/* 공지 게시판 상세보기 */
	public Notice selectNoticeIDX(int NOTICEIDX) throws Exception;
	
	/* 공지 게시판 입력 */
	public void insertNoticeList(Notice notice) throws Exception;
	
	/* 공지 게시판 수정 */
	public void updateNoticeList(Notice notice) throws Exception;
	
	/* 공지 게시판 수정(삭제) */
	public void updateNoticeListDel(int NOTICEIDX) throws Exception;
	
	/* 공지 게시판 조회수 */
	public void updateNoticeReadcount(int NOTICEIDX) throws Exception;
	
	/* 공지 게시판 좋아요 */
	public void updateNoticeGood(int NOTICEIDX) throws Exception;
	
	/* 공지 게시판 싫어요 */
	public void updateNoticeBad(int NOTICEIDX) throws Exception;

}

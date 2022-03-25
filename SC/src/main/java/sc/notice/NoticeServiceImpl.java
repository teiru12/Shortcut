package sc.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Resource(name="NoticeDAO")
	private NoticeDAO noticeDAO;
	
	/* 공지 게시판 리스트 페이징 */
	@Override
	public List<Notice> noticeListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return noticeDAO.noticeListPaging(map);
	}
	
	/* 공지 게시판 카운트 */
	@Override
	public int countNoticeList() throws Exception {
		return Integer.parseInt(String.valueOf(noticeDAO.countNoticeList()));
	}
	
	/* 공지 게시판 최신 3글 */
	@Override
	public List<Notice> noticeListThree() throws Exception {
		return noticeDAO.noticeListThree();
	}
	
	/* 공지 게시판 상세보기 */
	@Override
	public Notice selectNoticeIDX(int NOTICEIDX) throws Exception {
		return noticeDAO.selectNoticeIDX(NOTICEIDX);
	}

	/* 공지 게시판 입력 */
	@Override
	public void insertNoticeList(Notice notice) throws Exception {
		noticeDAO.insertNoticeList(notice);
		
	}

	/* 공지 게시판 수정 */
	@Override
	public void updateNoticeList(Notice notice) throws Exception {
		noticeDAO.updateNoticeList(notice);
		
	}

	/* 공지 게시판 수정(삭제) */
	@Override
	public void updateNoticeListDel(int NOTICEIDX) throws Exception {
		noticeDAO.updateNoticeListDel(NOTICEIDX);
		
	}

	/* 공지 게시판 조회수 */
	@Override
	public void updateNoticeReadcount(int NOTICEIDX) throws Exception {
		noticeDAO.updateNoticeReadcount(NOTICEIDX);
		
	}

	/* 공지 게시판 좋아요 */
	@Override
	public void updateNoticeGood(int NOTICEIDX) throws Exception {
		noticeDAO.updateNoticeGood(NOTICEIDX);
		
	}

	/* 공지 게시판 싫어요 */
	@Override
	public void updateNoticeBad(int NOTICEIDX) throws Exception {
		noticeDAO.updateNoticeBad(NOTICEIDX);
		
	}

}

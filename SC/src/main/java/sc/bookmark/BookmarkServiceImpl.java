package sc.bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Bookmark;

@Service("bookmarkService")
public class BookmarkServiceImpl implements BookmarkService {
	
	@Resource(name="bookmarkDAO")
	private BookmarkDAO bookmarkDAO;
	
	/* 회원의 즐겨찾기 단축키 리스트 페이징 */
	@Override
	public List<Bookmark> bookmarkShortListPaging(int START, int END, String ID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("ID", ID);
		
		return bookmarkDAO.bookmarkShortListPaging(map);
	}
	
	/* 회원의 즐겨찾기 단축키 카운트 */
	@Override
	public int countBookmarkShort(String ID) throws Exception {
		return Integer.parseInt(String.valueOf(bookmarkDAO.countBookmarkShort(ID)));
	}

	/* 회원의 즐겨찾기 게시판 리스트 페이징 */
	@Override
	public List<Bookmark> bookmarkBoardListPaging(int START, int END, String ID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("ID", ID);
		
		return bookmarkDAO.bookmarkBoardListPaging(map);
	}
	
	/* 회원의 즐겨찾기 게시판 리스트 카운트 */
	@Override
	public int countBookmarkBoard(String ID) throws Exception {
		return Integer.parseInt(String.valueOf(bookmarkDAO.countBookmarkBoard(ID)));
	}
	
	/* 회원의 즐겨찾기 추가 */
	@Override
	public void insertBookmarkById(Bookmark bookmark) throws Exception {
		bookmarkDAO.insertBookmarkById(bookmark);
	}
	
	/* 회원의 즐겨찾기 삭제 */
	@Override
	public void deleteBookmarkById(int BOOKMARKIDX) throws Exception {
		bookmarkDAO.deleteBookmarkById(BOOKMARKIDX);
	}
}
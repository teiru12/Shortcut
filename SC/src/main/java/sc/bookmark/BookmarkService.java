package sc.bookmark;

import java.util.List;

import sc.model.Bookmark;

public interface BookmarkService {

	/* 회원의 즐겨찾기 단축키 리스트 페이징 */
	public List<Bookmark> bookmarkShortListPaging(int START, int END, String ID) throws Exception;
	
	/* 회원의 즐겨찾기 단축키 카운트 */
	public int countBookmarkShort(String ID) throws Exception;

	/* 회원의 즐겨찾기 게시판 리스트 페이징 */
	public List<Bookmark> bookmarkBoardListPaging(int START, int END, String ID) throws Exception;
	
	/* 회원의 즐겨찾기 게시판 리스트 카운트 */
	public int countBookmarkBoard(String ID) throws Exception;
	
	/* 회원의 즐겨찾기 추가 */
	public void insertBookmarkById(Bookmark bookmark) throws Exception;
	
	/* 회원의 즐겨찾기 삭제 */
	public void deleteBookmarkById(int BOOKMARKIDX) throws Exception;
	
	/* 즐겨찾기 정보 검색 by ID, TYPE, IDX */
	public Bookmark selectBookmark(String ID, String TYPE, int IDX) throws Exception;
}
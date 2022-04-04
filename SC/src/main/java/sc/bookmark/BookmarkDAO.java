package sc.bookmark;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Bookmark;

@Repository("bookmarkDAO")
public class BookmarkDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/* 회원의 즐겨찾기 단축키 리스트 페이징 */
	public List<Bookmark> bookmarkShortListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("bookmark.bookmarkShortListPaging", map);
	}
	
	/* 회원의 즐겨찾기 단축키 카운트 */
	public int countBookmarkShort(String ID) throws Exception {
		return sqlSessionTemplate.selectOne("bookmark.countBookmarkShort", ID);
	}

	/* 회원의 즐겨찾기 게시판 리스트 페이징 */
	public List<Bookmark> bookmarkBoardListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("bookmark.bookmarkBoardListPaging", map);
	}
	
	/* 회원의 즐겨찾기 게시판 리스트 카운트 */
	public int countBookmarkBoard(String ID) throws Exception {
		return sqlSessionTemplate.selectOne("bookmark.countBookmarkBoard", ID);
	}
	
	/* 회원의 즐겨찾기 추가 */
	public void insertBookmarkById(Bookmark bookmark) throws Exception {
		sqlSessionTemplate.insert("bookmark.insertBookmarkById", bookmark);
	}
	
	/* 회원의 즐겨찾기 삭제 */
	public void deleteBookmarkById(int BOOKMARKIDX) throws Exception {
		sqlSessionTemplate.delete("bookmark.deleteBookmarkById", BOOKMARKIDX);
	}
	
	/* 즐겨찾기 정보 검색 by ID, TYPE, IDX */
	public Bookmark selectBookmark(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("bookmark.selectBookmark", map);
	}
}
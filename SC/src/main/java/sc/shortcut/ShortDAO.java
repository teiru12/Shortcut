package sc.shortcut;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.ShortCom;

@Repository("shortDAO")
public class ShortDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 단축키 게시판 리스트 페이징 */
	public List<Short> shortListPaging(Map<String,Object> map) throws Exception {
		return sqlSessionTemplate.selectList("short.shortListPaging", map);
	}

	/* 단축키 게시판 카운트 */
	public int countShortList() throws Exception{
		return sqlSessionTemplate.selectOne("short.countShortList");
	}
	
	/* 단축키 게시판 검색 리스트 페이징 */
	public List<Short> shortListSearchPaging(Map<String,Object> map) throws Exception{
		return sqlSessionTemplate.selectList("short.shortListSearchPaging",map);
	}
	
	/* 단축키 게시판 검색 카운트 */
	public int countShortListSearch() throws Exception{
		return sqlSessionTemplate.selectOne("short.countShortListSearch");
	}
	
	/* 단축키 게시판 입력 */
	public void insertShortList(Short SHORTCUT) throws Exception{
		sqlSessionTemplate.insert("short.insertShortList",SHORTCUT);
	}
	
	/* 단축키 게시판 수정 */
	public void updateShortList(Short SHORTCUT) throws Exception{
		sqlSessionTemplate.update("short.updateShortList",SHORTCUT);
	}
	
	/* 단축키 게시판 삭제 */
	public void deleteShortList(int SHORTIDX) throws Exception{
	 sqlSessionTemplate.delete("short.deleteShortList",SHORTIDX);
	}
	
	/* 단축키 게시판 선택 */
	public Short selectShortIDX(int SHORTIDX) throws Exception{
		return sqlSessionTemplate.selectOne("short.selectShortIDX",SHORTIDX);
	}
	
	/* 단축키 게시판 조회수 */
	public void updateShortReadcount(int SHORTIDX) throws Exception{
		sqlSessionTemplate.update("info.updateShortReadcount", SHORTIDX);
	}
	
	/* 단축키 게시판 좋아요 */
	public void updateShortGood(int SHORTIDX) throws Exception{
		sqlSessionTemplate.update("info.updateShortGood", SHORTIDX);
	}	
	
	/* 단축키 게시판 싫어요 */
	public void updateShortBad(int SHORTIDX) throws Exception{
		sqlSessionTemplate.update("info.updateShortBad", SHORTIDX);
	}
	
	/* 단축키 게시판 댓글 리스트 페이징 BY 게시글번호 */
	public List<ShortCom> shortListComPagingByShortIDX(Map<String,Object> map) throws Exception {
		return sqlSessionTemplate.selectList("short.shortListComPagingByShortIDX", map);
	}
	
	/* 단축키 게시판 댓글 카운트 BY 게시글번호 */
	public int countShortListComByShortIDX() throws Exception{
		return sqlSessionTemplate.selectOne("short.countShortListComByShortIDX");
	}
	
	/* 단축키 게시판 댓글 입력 BY 게시글번호 */
	public void insertShortListComByShortIDX(Short SHORTCUM) throws Exception{
		sqlSessionTemplate.insert("short.insertShortListComByShortIDX",SHORTCUM);
	}
	
	/* 단축키 게시판 댓글 수정 BY 게시글번호 */
	public void updateShortListComByShortIDX(Short SHORTCUM) throws Exception{
		sqlSessionTemplate.update("short.updateShortListComByShortIDX",SHORTCUM);
	}
	
	/* 단축키 게시판 댓글 수정 (삭제) BY 게시글번호 */
	public void updateShortListComDelByShortIDX(int SHORTIDX)throws Exception{
		sqlSessionTemplate.update("short.updateShortListComDelByShortIDX",SHORTIDX);
	}
	
}

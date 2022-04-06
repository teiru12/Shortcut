package sc.free;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Free;
import sc.model.FreeCom;

@Repository("freeDAO")
public class FreeDAO {
	
	@Resource(name="sqlSessionTemplate")
	   private SqlSessionTemplate sqlSessionTemplate;


	/*자유 게시판 리스트 페이징*/
	public List<Free> freeListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("free.freeListPaging", map);
	}
	
	
	/*자유 게시판 카운트*/
	public int countFreeList() throws Exception {
		return sqlSessionTemplate.selectOne("free.countFreeList");
	}
	
	/*자유 게시판 검색 리스트 페이징*/
	public List<Free> freeListSearchPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("free.freeListSearchPaging", map);
	}
	
	/*자유 게시판 검색 카운트*/
	public int countFreeListSearch(String KEYWORD) throws Exception {
		return sqlSessionTemplate.selectOne("free.countFreeListSearch", KEYWORD);
	}
	
	/*자유 게시판 입력*/
	public void insertFreeList(Free free) throws Exception {
		sqlSessionTemplate.insert("free.insertFreeList", free);
	}
	
	/*자유 게시판 수정*/
	public void updateFreeList(Free free) throws Exception {
		sqlSessionTemplate.update("free.updateFreeList", free);
	}
	
	/*자유 게시판 수정(삭제)*/
	public void updateFreeListDel(int FREEIDX) throws Exception {
		sqlSessionTemplate.update("free.updateFreeListDel", FREEIDX);
	}
	
	/*자유 게시판 선택*/
	public Free selectFreeIDX(int FREEIDX) throws Exception {
		return sqlSessionTemplate.selectOne("free.selectFreeIDX", FREEIDX);
	}
	
	/*자유 게시판 조회수*/
	public void updateFreeReadcount(int FREEIDX) throws Exception {
		sqlSessionTemplate.update("free.updateFreeReadcount", FREEIDX);
	}
	
	/*자유 게시판 좋아요*/
	public void updateFreeGood(int FREEIDX) throws Exception {
		sqlSessionTemplate.update("free.updateFreeGood", FREEIDX);
	}
	
	/*자유 게시판 싫어요*/
	public void updateFreeBad(int FREEIDX) throws Exception {
		sqlSessionTemplate.update("free.updateFreeBad", FREEIDX);
	}
	
	//자유 게시판 댓글
	/*자유 게시판 댓글 리스트 페이징 BY 게시글번호*/
	public List<FreeCom> freeListComPagingByFreeIDX(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("free.freeListComPagingByFreeIDX", map);
	}
	
	/*자유 게시판 댓글 카운트 BY 게시글번호*/
	public int countFreeComByFreeIDX(int FREEIDX) throws Exception {
		return sqlSessionTemplate.selectOne("free.countFreeComByFreeIDX", FREEIDX);
	}
	
	/*자유 게시판 댓글 입력 BY 게시글번호*/
	public void insertFreeListComByFreeIDX(FreeCom freeCom) throws Exception {
		sqlSessionTemplate.insert("free.insertFreeListComByFreeIDX", freeCom);
	}
	
	/*자유 게시판 대댓글이 아닌 댓글 입력 BY 게시글번호*/
	public void insertFreeListFirstComByFreeIDX(FreeCom freeCom) throws Exception {
		sqlSessionTemplate.insert("free.insertFreeListFirstComByFreeIDX", freeCom);
	}
	
	/*다음 RESTEP값을 찾음*/
	public Object nextReStep(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("free.nextReStep", map);
	}
	
	/*자유 게시판 댓글 하나의 정보 BY FREECOMIDX*/
	public FreeCom selectFreeCom(int FREECOMIDX) throws Exception {
		return sqlSessionTemplate.selectOne("free.selectFreeCom", FREECOMIDX);
	}
	
	/*RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	public void increaseReStepEqAndGreater(Map<String, Object> map) throws Exception {
		sqlSessionTemplate.update("free.increaseReStepEqAndGreater", map);
	}
	
	/*자유 게시판 댓글 수정 BY 게시글번호*/
	public void updateFreeListComByFreeIDX(FreeCom freeCom) throws Exception {
		sqlSessionTemplate.update("free.updateFreeListComByFreeIDX", freeCom);
	}
	
	/*자유 게시판 댓글 수정 (삭제) BY 게시글번호*/
	public void updateFreeListComDelByFreeIDX(int FREECOMIDX) throws Exception {
		sqlSessionTemplate.update("free.updateFreeListComDelByFreeIDX", FREECOMIDX);
	}
}

package sc.free;

import java.util.List;

import sc.model.Free;
import sc.model.FreeCom;

public interface FreeService {

	/*자유 게시판 리스트 페이징*/
	public List<Free> freeListPaging(int START, int END) throws Exception;
	
	/*자유 게시판 카운트*/
	public int countFreeList() throws Exception;
	
	/*자유 게시판 검색 리스트 페이징*/
	public List<Free> freeListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception;
	
	/*자유 게시판 검색 카운트*/
	public int countFreeListSearch() throws Exception;
	
	/*자유 게시판 입력*/
	public void insertFreeList(Free free) throws Exception;
	
	/*자유 게시판 수정*/
	public void updateFreeList(Free free) throws Exception;
	
	/*자유 게시판 수정(삭제)*/
	public void updateFreeListDel(int FREEIDX) throws Exception;
	
	/*자유 게시판 선택*/
	public Free selectFreeIDX(int FREEIDX) throws Exception;
	
	/*자유 게시판 조회수*/
	public void updateFreeReadcount(int FREEIDX) throws Exception;
	
	/*자유 게시판 좋아요*/
	public void updateFreeGood(int FREEIDX) throws Exception;
	
	/*자유 게시판 싫어요*/
	public void updateFreeBad(int FREEIDX) throws Exception;
	
	
//자유 게시판 댓글

	/*자유 게시판 댓글 리스트 페이징 BY 게시글번호*/
	public List<FreeCom> freeListComPagingByFreeIDX(int START, int END, int FREEIDX) throws Exception;
	
	/*자유 게시판 댓글 카운트 BY 게시글번호*/
	public int countFreeComByFreeIDX(int FREEIDX) throws Exception;
	
	/*자유 게시판 댓글 입력 BY 게시글번호*/
	public void insertFreeListComByFreeIDX(Free free) throws Exception;
	
	/*자유 게시판 댓글 수정 BY 게시글번호*/
	public void updateFreeListComByFreeIDX(Free free) throws Exception;
	
	/*자유 게시판 댓글 수정 (삭제) BY 게시글번호*/
	public void updateFreeListComDelByFreeIDX(int FREECOMIDX) throws Exception;
}

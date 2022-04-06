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
	public int countFreeListSearch(String KEYWORD) throws Exception;
	
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
	public void insertFreeListComByFreeIDX(FreeCom freeCom) throws Exception;
	
	/*자유 게시판 대댓글이 아닌 댓글 입력 BY 게시글번호*/
	public void insertFreeListFirstComByFreeIDX(FreeCom freeCom) throws Exception;
	
	/*다음 RESTEP값을 찾음*/
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int ARTICLEIDX) throws Exception;

	/*자유 게시판 댓글 하나의 정보 BY FREECOMIDX*/
	public FreeCom selectFreeCom(int FREECOMIDX) throws Exception;
	
	/*RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	public void increaseReStepEqAndGreater(int RESTEP, int RETYPE, int ARTICLEIDX) throws Exception;
	
	/*자유 게시판 댓글 수정 BY 게시글번호*/
	public void updateFreeListComByFreeIDX(FreeCom freeCom) throws Exception;
	
	/*자유 게시판 댓글 수정 (삭제) BY 게시글번호*/
	public void updateFreeListComDelByFreeIDX(int FREECOMIDX) throws Exception;
}

package sc.free;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Free;
import sc.model.FreeCom;

@Service("freeService")
public class FreeServiceImpl implements FreeService {

	@Resource(name="freeDAO")
	private FreeDAO freeDAO;

	/*자유 게시판 리스트 페이징*/
	@Override
	public List<Free> freeListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return freeDAO.freeListPaging(map);
	}

	/*자유 게시판 카운트*/
	@Override
	public int countFreeList() throws Exception {
		return Integer.parseInt(String.valueOf(freeDAO.countFreeList()));
	}

	/*자유 게시판 검색 리스트 페이징*/
	@Override
	public List<Free> freeListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("KEYWORD", KEYWORD);
		map.put("ORDER", ORDER);
		
		return freeDAO.freeListSearchPaging(map);
		
	}

	/*자유 게시판 검색 카운트*/
	@Override
	public int countFreeListSearch(String KEYWORD) throws Exception {
		return Integer.parseInt(String.valueOf(freeDAO.countFreeListSearch(KEYWORD)));
	}

	/*자유 게시판 입력*/
	@Override
	public void insertFreeList(Free free) throws Exception {
		freeDAO.insertFreeList(free);		
	}

	/*자유 게시판 수정*/
	@Override
	public void updateFreeList(Free free) throws Exception {
		freeDAO.updateFreeList(free);
	}

	/*자유 게시판 수정(삭제)*/
	@Override
	public void updateFreeListDel(int FREEIDX) throws Exception {
		freeDAO.updateFreeListDel(FREEIDX);
	}

	/*자유 게시판 선택*/
	@Override
	public Free selectFreeIDX(int FREEIDX) throws Exception {
		return freeDAO.selectFreeIDX(FREEIDX);
	}

	/*자유 게시판 조회수*/
	@Override
	public void updateFreeReadcount(int FREEIDX) throws Exception {
		freeDAO.updateFreeReadcount(FREEIDX);
	}

	/*자유 게시판 좋아요*/
	@Override
	public void updateFreeGood(int FREEIDX) throws Exception {
		freeDAO.updateFreeGood(FREEIDX);
	}

	/*자유 게시판 싫어요*/
	@Override
	public void updateFreeBad(int FREEIDX) throws Exception {
		freeDAO.updateFreeBad(FREEIDX);
	}

	//자유 게시판 댓글

	/*자유 게시판 댓글 리스트 페이징 BY 게시글번호*/
	@Override
	public List<FreeCom> freeListComPagingByFreeIDX(int START, int END, int FREEIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("FREEIDX", FREEIDX);
		
		return freeDAO.freeListComPagingByFreeIDX(map);
		
	}

	/*자유 게시판 댓글 카운트 BY 게시글번호*/
	@Override
	public int countFreeComByFreeIDX(int FREEIDX) throws Exception {
		return Integer.parseInt(String.valueOf(freeDAO.countFreeComByFreeIDX(FREEIDX)));
	}

	/*자유 게시판 댓글 입력 BY 게시글번호*/
	@Override
	public void insertFreeListComByFreeIDX(Free free) throws Exception {
		freeDAO.insertFreeListComByFreeIDX(free);
	}

	/*자유 게시판 댓글 수정 BY 게시글번호*/
	@Override
	public void updateFreeListComByFreeIDX(Free free) throws Exception {
		freeDAO.updateFreeListComByFreeIDX(free);
	}

	/*자유 게시판 댓글 수정 (삭제) BY 게시글번호*/
	@Override
	public void updateFreeListComDelByFreeIDX(int FREECOMIDX) throws Exception {
		freeDAO.updateFreeListComDelByFreeIDX(FREECOMIDX);
	}

	
	
}

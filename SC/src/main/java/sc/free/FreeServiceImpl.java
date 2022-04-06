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
	public void insertFreeListComByFreeIDX(FreeCom freeCom) throws Exception {
		freeDAO.insertFreeListComByFreeIDX(freeCom);
	}
	
	/*자유 게시판 대댓글이 아닌 댓글 입력 BY 게시글번호*/
	@Override
	public void insertFreeListFirstComByFreeIDX(FreeCom freeCom) throws Exception {
		freeDAO.insertFreeListFirstComByFreeIDX(freeCom);
	}
	
	/*다음 RESTEP값을 찾음*/
	@Override
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int FREEIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RETYPE", RETYPE);
		map.put("RELEVEL", RELEVEL);
		map.put("PCOMIDX", PCOMIDX);
		map.put("FREEIDX", FREEIDX);
		
		Object obj =  freeDAO.nextReStep(map);
		
		if(obj == null) {
			return 0;
		} else {
			return (int) obj;
		}
	}
	
	/*자유 게시판 댓글 하나의 정보 BY FREECOMIDX*/
	@Override
	public FreeCom selectFreeCom(int FREECOMIDX) throws Exception {
		return freeDAO.selectFreeCom(FREECOMIDX);
	}
	
	/*RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	@Override
	public void increaseReStepEqAndGreater(int RESTEP, int RETYPE, int FREEIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RESTEP", RESTEP);
		map.put("RETYPE", RETYPE);
		map.put("FREEIDX", FREEIDX);
		
		freeDAO.increaseReStepEqAndGreater(map);
	}

	/*자유 게시판 댓글 수정 BY 게시글번호*/
	@Override
	public void updateFreeListComByFreeIDX(FreeCom freeCom) throws Exception {
		freeDAO.updateFreeListComByFreeIDX(freeCom);
	}

	/*자유 게시판 댓글 수정 (삭제) BY 게시글번호*/
	@Override
	public void updateFreeListComDelByFreeIDX(int FREECOMIDX) throws Exception {
		freeDAO.updateFreeListComDelByFreeIDX(FREECOMIDX);
	}
}
package sc.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Info;
import sc.model.InfoCom;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
	
	@Resource(name="infoDAO")
	private InfoDAO infoDAO;

	/* 정보교류 게시판 리스트 페이징 */
	@Override
	public List<Info> infoListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return infoDAO.infoListPaging(map);
	}
	
	/* 정보교류 게시판 카운트 */
	@Override
	public int countInfoList() throws Exception {
		return Integer.parseInt(String.valueOf(infoDAO.countInfoList()));
	}
	
	/* 정보교류 게시판 검색 리스트 페이징 */
	@Override
	public List<Info> infoListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("KEYWORD", KEYWORD);
		map.put("ORDER", ORDER);
		
		return infoDAO.infoListSearchPaging(map);
	}
	
	/* 정보교류 게시판 검색 카운트 */
	@Override
	public int countInfoListSearch(String KEYWORD) throws Exception {
		return Integer.parseInt(String.valueOf(infoDAO.countInfoListSearch(KEYWORD)));
	}
	
	/* 정보교류 게시판 입력 */
	@Override
	public void insertInfoList(Info info) throws Exception {
		infoDAO.insertInfoList(info);
	}
	
	/* 정보교류 게시판 수정 */
	@Override
	public void updateInfoList(Info info) throws Exception {
		infoDAO.updateInfoList(info);
	}
	
	/* 정보교류 게시판 수정(삭제) */
	@Override
	public void updateInfoListDEL(int INFOIDX) throws Exception {
		infoDAO.updateInfoListDEL(INFOIDX);
	}
	
	/* 정보교류 게시판 선택 */
	@Override
	public Info selectInfoIDX(int INFOIDX) throws Exception {
		return infoDAO.selectInfoIDX(INFOIDX);
	}
	
	/* 정보교류 게시판 조회수 */
	@Override
	public void updateInfoReadcount(int INFOIDX) throws Exception {
		infoDAO.updateInfoReadcount(INFOIDX);
	}
	
	/* 정보교류 게시판 좋아요 */
	@Override
	public void updateInfoGood(int INFOIDX) throws Exception {
		infoDAO.updateInfoGood(INFOIDX);
	}
	
	/* 정보교류 게시판 싫어요 */
	@Override
	public void updateInfoBad(int INFOIDX) throws Exception {
		infoDAO.updateInfoBad(INFOIDX);
	}

	/* 정보교류 게시판 댓글 리스트 페이징 BY 게시글번호 */
	@Override
	public List<InfoCom> infoListComPagingByInfoIDX(int START, int END, int INFOIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("INFOIDX", INFOIDX);
		
		return infoDAO.infoListComPagingByInfoIDX(map);
	}
	
	/* 정보교류 게시판 댓글 카운트 BY 게시글번호 */
	@Override
	public int countInfoComByInfoIDX(int INFOIDX) throws Exception {
		return Integer.parseInt(String.valueOf(infoDAO.countInfoComByInfoIDX(INFOIDX)));
	}
	
	/* 정보교류 게시판 댓글 입력 BY 게시글번호 */
	@Override
	public void insertInfoListComByInfoIDX(InfoCom infoCom) throws Exception {
		infoDAO.insertInfoListComByInfoIDX(infoCom);
	}
	
	/* 정보교류 게시판 대댓글이 아닌 댓글 입력 BY 게시글번호*/
	@Override
	public void insertInfoListFirstComByInfoIDX(InfoCom infoCom) throws Exception {
		infoDAO.insertInfoListFirstComByInfoIDX(infoCom);
	}
	
	/* 다음 RESTEP값을 찾음*/
	@Override
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int INFOIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RETYPE", RETYPE);
		map.put("RELEVEL", RELEVEL);
		map.put("PCOMIDX", PCOMIDX);
		map.put("INFOIDX", INFOIDX);
		
		Object obj =  infoDAO.nextReStep(map);
		
		if(obj == null) {
			return 0;
		} else {
			return (int) obj;
		}
	}
	
	/* 정보교류 게시판 댓글 하나의 정보 BY INFOCOMIDX*/
	@Override
	public InfoCom selectInfoCom(int INFOCOMIDX) throws Exception {
		return infoDAO.selectInfoCom(INFOCOMIDX);
	}
	
	/* RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	@Override
	public void increaseReStepEqAndGreater(int RESTEP, int RETYPE, int INFOIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RESTEP", RESTEP);
		map.put("RETYPE", RETYPE);
		map.put("INFOIDX", INFOIDX);
		
		infoDAO.increaseReStepEqAndGreater(map);
	}

	/* 정보교류 게시판 댓글 수정 BY 게시글번호*/
	@Override
	public void updateInfoListComByInfoIDX(InfoCom infoCom) throws Exception {
		infoDAO.updateInfoListComByInfoIDX(infoCom);
	}

	/* 정보교류 게시판 댓글 수정 (삭제) BY 게시글번호*/
	@Override
	public void updateInfoListComDelByInfoIDX(int INFOCOMIDX) throws Exception {
		infoDAO.updateInfoListComDelByInfoIDX(INFOCOMIDX);
	}
}

package sc.shortcut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.ShortCom;

@Service("shortService")
public class ShortServiceImpl implements ShortService {
	
	@Resource(name="shortDAO")
	private ShortDAO shortDAO;

	@Override
	public List<Short> shortListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("START", START);
			map.put("END", END);
		
		return shortDAO.shortListPaging(map);
	}

	@Override
	public int countShortList() throws Exception {
		
		return  Integer.parseInt(String.valueOf(shortDAO.countShortList()));
	}

	@Override
	public List<Short> shortListSearchPaging(int START, int END, String KEYWORD, String STYPE) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		char s;
		
		if(STYPE == null) {
			s = ' ';
		}else {
			s = STYPE.charAt(0);
		}
		
		map.put("START", START);
		map.put("END", END);
		map.put("KEYWORD", KEYWORD);
		map.put("STYPE", s);

		return shortDAO.shortListSearchPaging(map);
	}

	@Override
	public int countShortListSearch(String KEYWORD, String STYPE) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		char s;
		
		if(STYPE == null) {
			s = ' ';
		}else {
			s = STYPE.charAt(0);
		}
		
		map.put("KEYWORD", KEYWORD);
		map.put("STYPE", s);

		return Integer.parseInt(String.valueOf(shortDAO.countShortListSearch(map)));
	}

	@Override
	public void insertShortList(Short SHORTCUT) throws Exception {
		shortDAO.insertShortList(SHORTCUT);
		
	}

	@Override
	public void updateShortList(Short SHORTCUT) throws Exception {
		shortDAO.updateShortList(SHORTCUT);
	}

	@Override
	public void deleteShortList(int SHORTIDX) throws Exception {
		shortDAO.deleteShortList(SHORTIDX);
	}

	@Override
	public sc.model.Short selectShortIDX(int SHORTIDX) throws Exception {
		return shortDAO.selectShortIDX(SHORTIDX);
	}

	@Override
	public void updateShortReadcount(int SHORTIDX) throws Exception {
		shortDAO.updateShortReadcount(SHORTIDX);
	}

	@Override
	public void updateShortGood(int SHORTIDX) throws Exception {
		shortDAO.updateShortGood(SHORTIDX);
	}

	@Override
	public void updateShortBad(int SHORTIDX) throws Exception {
		shortDAO.updateShortBad(SHORTIDX);
	}

	@Override
	public List<ShortCom> shortListComPagingByShortIDX(int START, int END, int SHORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("SHORTIDX", SHORTIDX);
		
		return shortDAO.shortListComPagingByShortIDX(map);
	}

	@Override
	public int countShortListComByShortIDX(int SHORTIDX) throws Exception {
		return shortDAO.countShortListComByShortIDX(SHORTIDX);
	}

	@Override
	public void insertShortListComByShortIDX(ShortCom SHORTCUM) throws Exception {
		shortDAO.insertShortListComByShortIDX(SHORTCUM);
	}

	@Override
	public void updateShortListComByShortIDX(ShortCom SHORTCUM) throws Exception {
		shortDAO.updateShortListComByShortIDX(SHORTCUM);
	}

	@Override
	public void updateShortListComDelByShortIDX(int SHORTIDX) throws Exception {
		shortDAO.updateShortListComDelByShortIDX(SHORTIDX);
	}

	@Override
	public ShortCom selectShortCom(int SHORTIDX) throws Exception {
		return shortDAO.selectShortCom(SHORTIDX);
	}
	
	/*다음 RESTEP값을 찾음*/
	@Override
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int SHORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RETYPE", RETYPE);
		map.put("RELEVEL", RELEVEL);
		map.put("PCOMIDX", PCOMIDX);
		map.put("SHORTIDX", SHORTIDX);
		
		Object obj =  shortDAO.nextReStep(map);
		
		if(obj == null) {
			return 0;
		} else {
			return (int) obj;
		}
	}
	
	/*RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	@Override
	public void increaseReStepEqAndGreaterShort(int RESTEP, int RETYPE, int SHORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RESTEP", RESTEP);
		map.put("RETYPE", RETYPE);
		map.put("SHORTIDX", SHORTIDX);
		
		shortDAO.increaseReStepEqAndGreaterShort(map);
	}

	//단축키 게시판 대댓글 입력
	@Override
	public void insertShortListFirstComByShortIDX(ShortCom SHORTCUM) throws Exception {
		shortDAO.insertShortListFirstComByShortIDX(SHORTCUM);
	}
}

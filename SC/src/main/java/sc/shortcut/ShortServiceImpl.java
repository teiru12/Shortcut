package sc.shortcut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("shortService")
public class ShortServiceImpl implements ShortService {
	
	@Resource(name="shortDAO")
	private ShortDAO shortDAO;

	@Override
	public List<Map<String, Object>> shortListPaging(int START, int END) throws Exception {
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
	public List<Map<String, Object>> shortListSearchPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return shortDAO.shortListPaging(map);
	}

	@Override
	public int countShortListSearch() throws Exception {
	
		return Integer.parseInt(String.valueOf(shortDAO.countShortListSearch()));
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
	public Short selectShortIDX(int SHORTIDX) throws Exception {
		
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
	public List<Map<String, Object>> shortListComPagingByShortIDX(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return shortDAO.shortListComPagingByShortIDX(map);
	}

	@Override
	public int countShortListComByShortIDX() throws Exception {
		return shortDAO.countShortListComByShortIDX();
	}

	@Override
	public void insertShortListComByShortIDX(Short SHORTCUM) throws Exception {
		shortDAO.insertShortListComByShortIDX(SHORTCUM);
	}

	@Override
	public void updateShortListComByShortIDX(Short SHORTCUM) throws Exception {
		shortDAO.updateShortListComByShortIDX(SHORTCUM);
	}

	@Override
	public void updateShortListComDelByShortIDX(int SHORTIDX) throws Exception {
		shortDAO.updateShortListComDelByShortIDX(SHORTIDX);
	}
}

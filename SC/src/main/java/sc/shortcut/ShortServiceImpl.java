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
	public void insertShortList(sc.model.Short SHORTCUT) throws Exception {
		shortDAO.insertShortList(SHORTCUT);
		
	}

	@Override
	public void updateShortList(sc.model.Short SHORTCUT) throws Exception {
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
	
	/* STYPE ????????? ????????? */
	@Override
	public List<sc.model.Short> shortListSTYPE(String STYPE) throws Exception {
		return shortDAO.shortListSTYPE(STYPE);
	}

	@Override
	public List<ShortCom> shortListComPagingByShortIDX(int START, int END, int SHORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("SHORTIDX", SHORTIDX);
		
		return shortDAO.shortListComPagingByShortIDX(map);
	}

	/* ??????????????? ?????? ????????? BY ???????????????*/
	@Override
	public int countShortListComByShortIDX(int SHORTIDX) throws Exception {
		return Integer.parseInt(String.valueOf(shortDAO.countShortListComByShortIDX(SHORTIDX)));
	}
	
	/* ????????? ????????? ?????? ?????? BY ???????????????*/
	@Override
	public void insertShortListComByShortIDX(ShortCom shortCom) throws Exception {
		shortDAO.insertShortListComByShortIDX(shortCom);
	}
	
	//????????? ????????? ???????????? ?????? ?????? ?????? BY ???????????????
	@Override
	public void insertShortListFirstComByShortIDX(ShortCom shortCom) throws Exception {
		shortDAO.insertShortListFirstComByShortIDX(shortCom);
	}

	/* ????????? ????????? ?????? ?????? BY ???????????????*/
	@Override
	public void updateShortListComByShortIDX(ShortCom shortCom) throws Exception {
		shortDAO.updateShortListComByShortIDX(shortCom);
	}

	/* ????????? ????????? ?????? ?????? (??????) BY ???????????????*/
	@Override
	public void updateShortListComDelByShortIDX(int SHORTIDX) throws Exception {
		shortDAO.updateShortListComDelByShortIDX(SHORTIDX);
	}

	/* ????????? ????????? ?????? ????????? ?????? BY SHORTCOMIDX */
	@Override
	public ShortCom selectShortCom(int SHORTCOMIDX) throws Exception {
		return shortDAO.selectShortCom(SHORTCOMIDX);
	}
	
	/*?????? RESTEP?????? ??????*/
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
	
	/*RETYPE??? ?????? ???????????? RESTEP????????? ????????? ??? ?????? ???????????? RESTEP??? 1??? ??????*/
	@Override
	public void increaseReStepEqAndGreaterShort(int RESTEP, int RETYPE, int SHORTIDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("RESTEP", RESTEP);
		map.put("RETYPE", RETYPE);
		map.put("SHORTIDX", SHORTIDX);
		
		shortDAO.increaseReStepEqAndGreaterShort(map);
	}


}

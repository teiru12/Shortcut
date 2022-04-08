package sc.shortcut;

import java.util.List;

import sc.model.ShortCom;

public interface ShortService {
	
	public List<Short> shortListPaging(int START, int END) throws Exception;
	
	public int countShortList() throws Exception;
	
	public List<Short> shortListSearchPaging(int START, int END, String KEYWORD, String STYPE) throws Exception;
	
	public int countShortListSearch(String KEYWORD, String STYPE) throws Exception;
	
	public void insertShortList(Short SHORTCUT) throws Exception;
	
	public void updateShortList(Short SHORTCUT) throws Exception;
	
	public void deleteShortList(int SHORTIDX) throws Exception;
	
	public sc.model.Short selectShortIDX(int SHORTIDX) throws Exception;
	
	public void updateShortReadcount(int SHORTIDX) throws Exception;
	
	public void updateShortGood(int SHORTIDX) throws Exception;
	
	public void updateShortBad(int SHORTIDX) throws Exception;
	
	public List<ShortCom> shortListComPagingByShortIDX(int START, int END, int SHORTIDX) throws Exception;
	
	public int countShortListComByShortIDX(int SHORTIDX) throws Exception;
	
	public void insertShortListComByShortIDX(ShortCom SHORTCUM) throws Exception;
	
	public void insertShortListFirstComByShortIDX(ShortCom SHORTCUM) throws Exception;
	
	public void updateShortListComByShortIDX(ShortCom SHORTCUM) throws Exception;
	
	public void updateShortListComDelByShortIDX(int SHORTIDX)throws Exception;
	
	public ShortCom selectShortCom(int SHORTIDX) throws Exception;
	
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int SHORTIDX) throws Exception;
	
	public void increaseReStepEqAndGreaterShort(int RESTEP, int RETYPE, int SHORTIDX) throws Exception;

}

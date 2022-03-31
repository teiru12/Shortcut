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
	
	public Short selectShortIDX(int SHORTIDX) throws Exception;
	
	public void updateShortReadcount(int SHORTIDX) throws Exception;
	
	public void updateShortGood(int SHORTIDX) throws Exception;
	
	public void updateShortBad(int SHORTIDX) throws Exception;
	
	public List<ShortCom> shortListComPagingByShortIDX(int START, int END) throws Exception;
	
	public int countShortListComByShortIDX() throws Exception;
	
	public void insertShortListComByShortIDX(Short SHORTCUM) throws Exception;
	
	public void updateShortListComByShortIDX(Short SHORTCUM) throws Exception;
	
	public void updateShortListComDelByShortIDX(int SHORTIDX)throws Exception;

}

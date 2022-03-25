package sc.goodbad;

import java.util.List;
import java.util.Map;

import sc.model.Goodbad;

public interface GoodbadService {

	//좋아요 입력
	public void insertGood(Goodbad goodbad) throws Exception;
	
	//싫어요 입력
	public void insertBad(Goodbad goodbad) throws Exception;
	
	//좋아요 선택
	public Goodbad selectGoodbad(int TYPE, int IDX, String ID) throws Exception;
	
	//좋아요 검색 자유게시판
	public List<Map<String, Object>> selectGoodFreeByDate(Map<String, Object> map) throws Exception;
	
	//좋아요 검색 정보교류게시판
	public List<Map<String, Object>> selectGoodInfoByDate(Map<String, Object> map) throws Exception;
		
	//좋아요 검색 단축키게시판
	public List<Map<String, Object>> selectGoodShortByDate(Map<String, Object> map)	throws Exception;
}

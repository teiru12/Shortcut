package sc.goodbad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Goodbad;

@Service("goodbadService")
public class GoodbadServiceImpl implements GoodbadService{
	
	@Resource(name = "goodbadDAO")
	private GoodbadDAO goodbadDAO;

	//좋아요 입력
	@Override
	public void insertGood(Goodbad goodbad) throws Exception {
		goodbadDAO.insertGood(goodbad);
		
	}

	//싫어요 입력
	@Override
	public void insertBad(Goodbad goodbad) throws Exception {
		goodbadDAO.insertBad(goodbad);
		
	}

	//좋아요 선택
	@Override
	public Goodbad selectGoodbad(String ID, String TYPE, int IDX) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("TYPE", TYPE);
		map.put("IDX", IDX);
		map.put("ID", ID);
		
		return goodbadDAO.selectGoodbad(map);
	}

	//좋아요 검색 자유게시판
	@Override
	public List<Map<String, Object>> selectGoodFreeByDate(Map<String, Object> map) throws Exception {
		
		return goodbadDAO.selectGoodFreeByDate(map);
	}

	//좋아요 검색 정보교류게시판
	@Override
	public List<Map<String, Object>> selectGoodInfoByDate(Map<String, Object> map) throws Exception {
		
		return goodbadDAO.selectGoodInfoByDate(map);
	}

	//좋아요 검색 단축키게시판
	@Override
	public List<Map<String, Object>> selectGoodShortByDate(Map<String, Object> map) throws Exception {
		
		return goodbadDAO.selectGoodShortByDate(map);
	}	
}
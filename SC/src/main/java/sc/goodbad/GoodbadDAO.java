package sc.goodbad;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Goodbad;

@Repository("goodbadDAO")
public class GoodbadDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//좋아요 입력
	public void insertGood(Goodbad goodbad) throws Exception {
		sqlSessionTemplate.insert("goodbad.insertGood", goodbad);
	}
	
	//싫어요 입력
	public void insertBad(Goodbad goodbad) throws Exception {
		sqlSessionTemplate.insert("goodbad.insertBad", goodbad);
	}
	
	//좋아요 선택
	public Goodbad selectGoodbad(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("goodbad.selectGoodbad", map);
	}
	
	//좋아요 검색 자유게시판
	public List<Map<String, Object>> selectGoodFreeByDate(Map<String, Object> map)
		throws Exception {
		return sqlSessionTemplate.selectList("goodbad.selectGoodFreeByDate", map);
	}
	
	//좋아요 검색 정보교류게시판
	public List<Map<String, Object>> selectGoodInfoByDate(Map<String, Object> map)
		throws Exception {
		return sqlSessionTemplate.selectList("goodbad.selectGoodInfoByDate", map);
	}
	
	//좋아요 검색 단축키게시판
	public List<Map<String, Object>> selectGoodShortByDate(Map<String, Object> map)
		throws Exception {
		return sqlSessionTemplate.selectList("goodbad.selectGoodShortByDate", map);
		}
}

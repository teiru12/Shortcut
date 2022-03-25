package sc.info;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Info;
import sc.model.InfoCom;

@Repository("infoDAO")
public class InfoDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 정보교류 게시판 리스트 페이징 */
	public List<Info> infoListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("info.infoListPaging", map);
	}
	
	/* 정보교류 게시판 카운트 */
	public int countInfoList() throws Exception {
		return sqlSessionTemplate.selectOne("info.countInfoList");
	}
	
	/* 정보교류 게시판 검색 리스트 페이징 */
	public List<Info> infoListSearchPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("info.infoListSearchPaging", map);
	} 
	
	/* 정보교류 게시판 검색 카운트 */
	public int countInfoListSearch(String KEYWORD) throws Exception {
		return sqlSessionTemplate.selectOne("info.countInfoListSearch", KEYWORD);
	}
	
	/* 정보교류 게시판 입력 */
	public void insertInfoList(Info info) throws Exception {
		sqlSessionTemplate.insert("info.insertInfoList", info);
	}
	
	/* 정보교류 게시판 수정 */
	public void updateInfoList(Info info) throws Exception {
		sqlSessionTemplate.update("info.updateInfoList", info);
	}	
	
	/* 정보교류 게시판 수정(삭제) */
	public void updateInfoListDEL(int INFOIDX) throws Exception {
		sqlSessionTemplate.update("info.updateInfoListDEL", INFOIDX);
	}	
	
	/* 정보교류 게시판 선택 */
	public Info selectInfoIDX(int INFOIDX) throws Exception {
		return sqlSessionTemplate.selectOne("info.selectInfoIDX", INFOIDX);
	}
	
	/* 정보교류 게시판 조회수 */
	public void updateInfoReadcount(int INFOIDX) throws Exception {
		sqlSessionTemplate.update("info.updateInfoReadcount", INFOIDX);
	}
	
	/* 정보교류 게시판 좋아요 */
	public void updateInfoGood(int INFOIDX) throws Exception {
		sqlSessionTemplate.update("info.updateInfoGood", INFOIDX);
	}	
	
	/* 정보교류 게시판 싫어요 */
	public void updateInfoBad(int INFOIDX) throws Exception {
		sqlSessionTemplate.update("info.updateInfoBad", INFOIDX);
	}	

	/* 정보교류 게시판 댓글 리스트 페이징 BY 게시글번호 */
	public List<InfoCom> infoListComPagingByInfoIDX(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("info.infoListComPagingByInfoIDX", map);
	}
	
	/* 정보교류 게시판 댓글 카운트 BY 게시글번호 */
	public int countInfoComByInfoIDX(int INFOIDX) throws Exception {
		return sqlSessionTemplate.selectOne("info.countInfoComByInfoIDX", INFOIDX);
	}
	
	/* 정보교류 게시판 댓글 입력 BY 게시글번호 */
	public void insertInfoListComByInfoIDX(InfoCom infoCom) throws Exception {
		sqlSessionTemplate.insert("info.insertInfoListComByInfoIDX", infoCom);
	}
	
	/* 정보교류 게시판 댓글 수정 BY 게시글번호 */
	public void updateInfoListComByInfoIDX(InfoCom infoCom) throws Exception {
		sqlSessionTemplate.update("info.updateInfoListComByInfoIDX", infoCom);
	}
	
	/* 정보교류 게시판 댓글 수정 (삭제) BY 게시글번호 */
	public void updateInfoListComDelByInfoIDX(int INFOCOMIDX) throws Exception {
		sqlSessionTemplate.update("info.updateInfoListComDelByInfoIDX", INFOCOMIDX);
	}
}
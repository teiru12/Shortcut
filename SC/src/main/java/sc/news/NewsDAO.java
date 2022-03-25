package sc.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.News;

@Repository("newsDAO")
public class NewsDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//뉴스 게시판 리스트 페이징
	public List<News> newsListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("news.newsListPaging", map);
	}
	
	//뉴스 게시판 글 수
	public int countNewsList() throws Exception {
		return sqlSessionTemplate.selectOne("news.countNewsList");
	}
	
	//뉴스 게시판 검색 리스트 페이징
	public List<News> newsListSearchPaging(Map<String,Object> map) throws Exception {
		return sqlSessionTemplate.selectList("news.newsListSearchPaing", map);
	}
	
	//뉴스 게시판 검색한 글 수
	public int countNewsListSearch() throws Exception {
		return sqlSessionTemplate.selectOne("news.countNewsListSearch");
	}
	
	//뉴스 게시판 글 입력
	public void insertNewsList(News news) throws Exception {
		sqlSessionTemplate.insert("nesw.insertNewsList", news);
	}
	
	//뉴스 게시판 수정
	public void updateNewsList(News news)throws Exception {
		sqlSessionTemplate.update("news.updateNewsList", news);
	}
	
	//뉴스 게시판 수정(삭제)
	public void updateNewsListDel(int NEWSIDX) throws Exception {
		sqlSessionTemplate.update("news.updateNewsListDel", NEWSIDX);
	}
	
	//뉴스 게시판 선택
	public News selectNewsIDX(int NEWSIDX) throws Exception {
		return sqlSessionTemplate.selectOne("news.selectNewsIDX", NEWSIDX);
	}
	
	//뉴스 게시판 조회수
	public void updateNewsReadcount(int NEWSIDX) throws Exception {
		sqlSessionTemplate.update("news.updateNewsReadcount", NEWSIDX);
	}
	
	//뉴스 게시판 좋아요
	public void updateNewsGood(int NEWSIDX) throws Exception {
		sqlSessionTemplate.update("news.updateNewsGood", NEWSIDX);
	}
	
	//뉴스 게시판 싫어요
	public void updateNewsBad(int NEWSIDX) throws Exception {
		sqlSessionTemplate.update("news.updateNewsBad", NEWSIDX);
	}
}

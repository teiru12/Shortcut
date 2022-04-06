package sc.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.News;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	@Resource(name = "newsDAO")
	private NewsDAO newsDAO;

	//뉴스 게시판 리스트 페이징
	@Override
	public List<News> newsListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return newsDAO.newsListPaging(map);
	}

	//뉴스 게시판 글 수 
	@Override
	public int countNewsList() throws Exception {
		return Integer.parseInt(String.valueOf(newsDAO.countNewsList()));
	}

	//뉴스 게시판 검색 리스트 페이징
	@Override
	public List<News> newsListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("KEYWORD", KEYWORD);
		map.put("ORDER", ORDER);
		
		return newsDAO.newsListSearchPaging(map);
	}

	//뉴스 게시판 검색 글 수
	@Override
	public int countNewsListSearch(String KEYWORD) throws Exception {
		return Integer.parseInt(String.valueOf(newsDAO.countNewsListSearch()));
	}

	//뉴스 게시판 등록
	@Override
	public void insertNewsList(News news) throws Exception {
		newsDAO.insertNewsList(news);
	}

	//뉴스 게시판 수정
	@Override
	public void updateNewsList(News news) throws Exception {
		newsDAO.updateNewsList(news);
	}

	//뉴스 게시판 수정(삭제)
	@Override
	public void updateNewsListDel(int NEWSIDX) throws Exception {
		newsDAO.updateNewsListDel(NEWSIDX);
		
	}

	//뉴스 게시판 선택
	@Override
	public News selectNewsIDX(int NEWSIDX) throws Exception {
		return newsDAO.selectNewsIDX(NEWSIDX);
	}

	//뉴스 게시판 조회수
	@Override
	public void updateNewsReadcount(int NEWSIDX) throws Exception {
		newsDAO.updateNewsReadcount(NEWSIDX);
	}

	//뉴스 게시판 좋아요
	@Override
	public void updateNewsGood(int NEWSIDX) throws Exception {
		newsDAO.updateNewsGood(NEWSIDX);
	}

	//뉴스 게시판 싫어요
	@Override
	public void updateNewsBad(int NEWSIDX) throws Exception {
		newsDAO.updateNewsBad(NEWSIDX);
		
	}

}

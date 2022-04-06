package sc.news;

import java.util.List;

import sc.model.News;

public interface NewsService {
	
	//뉴스 게시판 리스트 페이징
	public List<News> newsListPaging(int START, int END) throws Exception;
	
	//뉴스 게시판 글 수 
	public int countNewsList() throws Exception;
	
	//뉴스 게시판 검색 리스트
	public List<News> newsListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception;
	
	//뉴스 게시판 검색 글 수 
	public int countNewsListSearch(String KEYWORD) throws Exception;
	
	//뉴스 게시판 입력
	public void insertNewsList(News news) throws Exception;
	
	//뉴스 게시판 수정
	public void updateNewsList(News news)throws Exception;
	
	//뉴스 게시판 수정(삭제)
	public void updateNewsListDel(int NEWSIDX) throws Exception;
	
	//뉴스 게시판 선택
	public News selectNewsIDX(int NEWSIDX) throws Exception;
	
	//뉴스 게시판 조회수
	public void updateNewsReadcount(int NEWSIDX) throws Exception;
	
	//뉴스 게시판 좋아요
	public void updateNewsGood(int NEWSIDX) throws Exception;
	
	//뉴스 게시판 싫어요
	public void updateNewsBad(int NEWSIDX) throws Exception ;

}
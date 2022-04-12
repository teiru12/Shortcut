package sc.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.bookmark.BookmarkService;
import sc.goodbad.GoodbadService;
import sc.model.Bookmark;
import sc.model.Goodbad;
import sc.model.News;
import sc.model.Notice;
import sc.notice.NoticeService;
import sc.report.ReportService;
import sc.util.Paging;

@Controller
public class NewsController {

	@Resource(name = "newsService")
	private NewsService newsService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@Resource(name = "bookmarkService")
	private BookmarkService bookmarkService;
	
	@Resource(name = "reportService")
	private ReportService reportService;
	
	@RequestMapping(value = "/newsList.cut")
	public String newsList(HttpServletRequest request, Model model) throws Exception {
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 게시글 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1;
		
		int newsCount;
		int pageBlock = 10;
		String url = "newsList.cut";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 검색 조건일 경우 사용 변수 */
		String KEYWORD = null; // 검색어 (제목만 검색됨)
		
		KEYWORD = request.getParameter("KEYWORD");
		
		if(KEYWORD != null) {
			searchUrl += "&KEYWORD=" + KEYWORD;
		}
		
		if(KEYWORD == null || KEYWORD.trim() =="") { // 검색 미적용일때
			System.out.println("1");
			newsCount = newsService.countNewsList();
		} else { // 감색했을때
			System.out.println("2");
			newsCount = newsService.countNewsListSearch(KEYWORD);
		}

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(newsCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<News> newsList = new ArrayList<News>();
		List<Notice> noticeTopList = new ArrayList<Notice>();
		
		/* 페이징 리스트 불러오기 */
		if(KEYWORD == null || KEYWORD.trim() =="") {
			newsList = newsService.newsListPaging(START, END);
		}else {
			newsList = newsService.newsListSearchPaging(START, END, KEYWORD, KEYWORD);
		}

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		/* 상단 공지사항 불러오기 및 조건 */
		noticeTopList = noticeService.noticeListPaging(1, 3);
		
		model.addAttribute("newsList", newsList);
		model.addAttribute("noticeTopList", noticeTopList);
		
		model.addAttribute("KEYWORD", KEYWORD);
		
		return "newsList";
	}	
	
	@RequestMapping(value = "newsWrite.cut")
	@ResponseBody
	public Map<String,String> newsWrite(String TITLE, String CONTENT,String PASSWORD, HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		News news = new News();
		String ID = (String)request.getSession().getAttribute("id");
						
		news.setID(ID);
		news.setTITLE(TITLE);
		news.setCONTENT(CONTENT);
		
		/* 글 입력 */
		newsService.insertNewsList(news);
		
		/* 입력한 게시글의 글 번호를 가져옴 */
		int inputIDX = reportService.selectMaxNEWSIDX();
		
		/* 입력한 게시글의 신고 횟수 0으로 초기화 */
		reportService.insertCountReport("NEW", inputIDX);
		
		map.put("ID", ID);
		
		return map;
	}
	
	@RequestMapping(value = "newsWriteForm.cut")
	public String newsWriteForm(Model model) throws Exception{
		
		return "newsWriteForm";
	}
	
	@RequestMapping(value = "newsModify.cut")
	@ResponseBody
	public Map<String, String> newsModify(String TITLE, String CONTENT,int IDX, HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		News news = new News();
		
		news.setNEWSIDX(IDX);
		news.setTITLE(TITLE);
		news.setCONTENT(CONTENT);
		
		newsService.updateNewsList(news);
		
		return map;
	}
	
	@RequestMapping(value = "newsModifyForm.cut")
	public String newsModifyForm(HttpServletRequest request,Model model) throws Exception{
		int IDX = Integer.parseInt(request.getParameter("IDX"));
		
		News news = newsService.selectNewsIDX(IDX);
		model.addAttribute("news",news);
		
		return "newsModifyForm";
	}
	
	@RequestMapping(value = "newsDetail.cut")
	public String newsDetail(int NEWSIDX, News News, Model model, HttpServletRequest request) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");

		/* 조회수 증가 */
		newsService.updateNewsReadcount(NEWSIDX);		
		
		/* 게시글 상세 정보 읽어옴 */
		News newsDetail = newsService.selectNewsIDX(NEWSIDX);
		
		/* 좋아요 싫어요를 했는지, 즐겨찾기를 했는지 여부 검사 */
		Goodbad gb = new Goodbad();
		Bookmark bk = new Bookmark();
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "NEW", NEWSIDX);
			bk = bookmarkService.selectBookmark(id, "NEW", NEWSIDX);
		}
		
		/* 게시글의 신고 횟수 & id의 신고 여부 */
		int countReport = reportService.selectCountReport("NEW", NEWSIDX);
		String isReported = "N";
		if(id != null && reportService.selectReportLog("NEW", NEWSIDX, id) != null) {
			isReported = "Y";
		}	
		
		model.addAttribute("newsDetail", newsDetail);
		/* 좋아요/싫어요가 있을 경우 모델에 삽입 */
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		/* 즐겨찾기가 있을 경우 모델에 삽입 */
		if(id !=null && bk != null) {
			model.addAttribute("bookmark", bk);
		}
		
		/* 게시글의 신고 횟수 & id의 신고 여부 모델에 삽입*/
		model.addAttribute("countReport", countReport);
		model.addAttribute("isReported", isReported);
		
		return "newsDetail";
	}
	
	@ResponseBody
	@RequestMapping("/newsDelete.cut")
	public Map<String, String> newsDelete(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 뉴스게시판의 게시글 삭제 : ISDEL을 'Y'로 변환
		newsService.updateNewsListDel(IDX);
		
		return map;
	}
		
	@ResponseBody
	@RequestMapping("/newsGood.cut")
	public Map<String, String> newsGood(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 뉴스게시판의 IDX의 게시글의 좋아요 1 증가
		newsService.updateNewsGood(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/newsBad.cut")
	public Map<String, String> newsBad(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 뉴스게시판의 IDX의 게시글의 싫어요 1 증가
		newsService.updateNewsBad(IDX);
		
		return map;
	}
}

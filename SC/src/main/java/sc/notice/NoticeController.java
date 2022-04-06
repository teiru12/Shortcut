package sc.notice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.bookmark.BookmarkService;
import sc.goodbad.GoodbadService;
import sc.model.Notice;
import sc.util.Paging;

@Controller
public class NoticeController {

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@Resource(name = "bookmarkService")
	private BookmarkService bookmarkService;
	
	@RequestMapping(value = "/noticeList.cut")
	public String noticeList(HttpServletRequest request, Model model) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 게시글 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1;
		
		int noticeCount = noticeService.countNoticeList();
		int pageBlock = 10;
		String url = "noticeList.cut";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(noticeCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Notice> noticeList = new ArrayList<Notice>();
		
		noticeList = noticeService.noticeListPaging(START, END);


		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		model.addAttribute("noticeList", noticeList);
		
		return "noticeList";
	}	
	
}

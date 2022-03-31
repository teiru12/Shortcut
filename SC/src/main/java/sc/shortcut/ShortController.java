package sc.shortcut;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.model.Notice;
import sc.notice.NoticeService;
import sc.util.Paging;

@Controller
public class ShortController {
	
	@Resource(name = "shortService")
	private ShortService shortService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@RequestMapping(value = "/shortcutList.cut")
	public String shortcutList(HttpServletRequest request, Model model) throws Exception {
		
		/* 페이징 변수 설정 */
		int pageSize = 10;
		int START = 1;
		int END = pageSize;
		int currentPage = 1;
		
		
		int shortCount;
		int pageBlock = 10;
		String url = "shortcutList.cut";
		String searchUrl = "";
		

		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 검색 조건일 경우 사용 변수 */
		String KEYWORD = null; // 검색어 (제목만 검색됨)
		String STYPE = null;
		
		KEYWORD = request.getParameter("KEYWORD");
		STYPE = request.getParameter("STYPE");
		
		if(STYPE != null ) {
			searchUrl += "&STYPE=" + STYPE;
		}
		
		if((KEYWORD == null || KEYWORD.trim() =="") && (STYPE == null || STYPE.trim() =="")) { // 검색 미적용일때
			shortCount = shortService.countShortList();
		}else { // 감색했을때
			shortCount = shortService.countShortListSearch(KEYWORD, STYPE);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(shortCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Short> shortList = new ArrayList<Short>();
		List<Notice> noticeTopList = new ArrayList<Notice>();
		
		/* 페이징 리스트 불러오기 */
		if((KEYWORD == null || KEYWORD.trim() =="") && (STYPE == null || STYPE.trim() =="")) {
			shortList = shortService.shortListPaging(START, END);
		}else {
			shortList = shortService.shortListSearchPaging(START, END, KEYWORD, STYPE);
		}
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		
		/* 상단 공지사항 불러오기 및 조건 */
		noticeTopList = noticeService.noticeListPaging(1, 3);
		
		model.addAttribute("shortList", shortList);
		model.addAttribute("noticeTopList", noticeTopList);
		
		return "shortcutList";
	}
	
}

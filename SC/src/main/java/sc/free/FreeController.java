package sc.free;

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

import sc.goodbad.GoodbadService;
import sc.model.Free;
import sc.model.FreeCom;
import sc.model.Goodbad;
import sc.model.Notice;
import sc.notice.NoticeService;
import sc.util.Paging;

@Controller 
public class FreeController {
	
	@Resource(name = "freeService")
	private FreeService freeService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@RequestMapping(value = "/freeList.cut")
	public String freeList(HttpServletRequest request, Model model) throws Exception {
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 게시글 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1;
		
		int freeCount;
		int pageBlock = 10;
		String url = "freeList.cut";
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
		
		
		if(KEYWORD == null || KEYWORD.trim() =="") { // 검색 미적용일때
			freeCount = freeService.countFreeList();
		}else { // 감색했을때
			freeCount = freeService.countFreeListSearch(KEYWORD);
		}

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(freeCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Free> freeList = new ArrayList<Free>();
		List<Notice> noticeTopList = new ArrayList<Notice>();
		
		/* 페이징 리스트 불러오기 */
		if(KEYWORD == null || KEYWORD.trim() =="") {
			freeList = freeService.freeListPaging(START, END);
		}else {
			freeList = freeService.freeListSearchPaging(START, END, KEYWORD, KEYWORD);
		}

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		/* 상단 공지사항 불러오기 및 조건 */
		noticeTopList = noticeService.noticeListPaging(1, 3);
		
		model.addAttribute("freeList", freeList);
		model.addAttribute("noticeTopList", noticeTopList);
		
	return "freeList";
	}	
	
	@RequestMapping(value = "freeDetail.cut")
	public String freeDetail(int FREEIDX, Free free, FreeCom freeCom, Model model, HttpServletRequest request) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");
		
		/* 게시글 상세 정보 읽어옴 */
		Free freeDetail = freeService.selectFreeIDX(FREEIDX);
		
		Goodbad gb = new Goodbad();
		/* 좋아요 싫어요를 했는지 여부 검사 */
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "FRE", FREEIDX);
		}
		
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countFreeCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "freeDetail.cut";
		String searchUrl = "&FREEIDX=" + FREEIDX;
		
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 페이징을 위한 값 계산 */
		countFreeCom = freeService.countFreeComByFreeIDX(FREEIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countFreeCom, pageBlock, pageSize, currentPage, url, searchUrl);
		
		List<FreeCom> freeComList = new ArrayList<FreeCom>();
		freeComList = freeService.freeListComPagingByFreeIDX(START, END, FREEIDX);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);		
		model.addAttribute("freeComList", freeComList);

		model.addAttribute("freeDetail", freeDetail);
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		
		return "freeDetail";
	}
	
	@ResponseBody
	@RequestMapping("/freeGood.cut")
	public Map<String, String> freeGood(int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 자유게시판의 IDX의 게시글의 좋아요 1 증가
		freeService.updateFreeGood(IDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/freeBad.cut")
	public Map<String, String> freeBad(int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 자유게시판의 IDX의 게시글의 싫어요 1 증가
		freeService.updateFreeBad(IDX);
		
		return msg;
	}
}
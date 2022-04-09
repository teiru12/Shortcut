package sc.shortcut;

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
import sc.model.Notice;
import sc.model.ShortCom;
import sc.notice.NoticeService;
import sc.util.GetIP;
import sc.util.MakeComList;
import sc.util.Paging;

@Controller
public class ShortController {
	
	@Resource(name = "shortService")
	private ShortService shortService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@Resource(name = "bookmarkService")
	private BookmarkService bookmarkService;
	
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
		if(KEYWORD != null) {
			searchUrl += "&KEYWORD=" + KEYWORD;
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
		
		model.addAttribute("KEYWORD", KEYWORD);
		model.addAttribute("STYPE", STYPE);
		
		return "shortcutList";
	}
	
	@RequestMapping(value="shortDetail.cut")
	public String shortDetail(int SHORTIDX, HttpServletRequest request, Model model) throws Exception {
		String id = (String) request.getSession().getAttribute("id");
		
		shortService.updateShortReadcount(SHORTIDX);
		
		sc.model.Short shortDetail = shortService.selectShortIDX(SHORTIDX);
		
		/* 좋아요 싫어요를 했는지, 즐겨찾기를 했는지 여부 검사 */
		Goodbad gb = new Goodbad();
		Bookmark bk = new Bookmark();
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "SHO", SHORTIDX);
			bk = bookmarkService.selectBookmark(id, "SHO", SHORTIDX);
		}
		
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countShortCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "shortDetail.cut";
		String searchUrl = "&SHORTIDX=" + SHORTIDX;
		
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 페이징을 위한 값 계산 */
		countShortCom = shortService.countShortListComByShortIDX(SHORTIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countShortCom, pageBlock, pageSize, currentPage, url, searchUrl);
		
		List<ShortCom> shortComList = new ArrayList<ShortCom>();
		shortComList = shortService.shortListComPagingByShortIDX(START, END, SHORTIDX);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);		
		model.addAttribute("shortComList", shortComList);
		model.addAttribute("id", id);

		model.addAttribute("shortDetail", shortDetail);
		/* 좋아요/싫어요가 있을 경우 모델에 삽입 */
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		/* 즐겨찾기가 있을 경우 모델에 삽입 */
		if(id !=null && bk != null) {
			model.addAttribute("bookmark", bk);
		}
		
		return "shortDetail";
	}
	
	@ResponseBody
	@RequestMapping("/shortGood.cut")
	public Map<String, String> shortGood(int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 단축키게시판의 IDX의 게시글의 좋아요 1 증가
		shortService.updateShortGood(IDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/shortBad.cut")
	public Map<String, String> shortBad(int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 단축키게시판의 IDX의 게시글의 싫어요 1 증가
		shortService.updateShortBad(IDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/shortComWrite.cut")
	public Map<String, String> shortComWrite(HttpServletRequest request, int ARTICLEIDX, String CONTENT, 
			String ID, String PASSWORD, int RETYPE, int COMIDX, int currentPage) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		/* 입력에서 사용할 FreeCom 객체에 내용 입력 */
		ShortCom sCom = new ShortCom();
		sCom.setSHORTIDX(ARTICLEIDX);
		sCom.setCONTENT(CONTENT);
		sCom.setID(ID);
		String ip = null;
		if(ID.equals("비회원")) {
			ip = GetIP.get(request);
		}
		sCom.setIP(ip);
		if(PASSWORD.equals("")) {
			PASSWORD = null;
		}
		sCom.setPASSWORD(PASSWORD);
		
		/* 일반 댓글일 경우 */
		if(RETYPE == 0) {
			sCom.setRELEVEL(0);
			// 현재 게시글의 댓글 수를 구한뒤 +1을 한 값을 RESTEP값에 입력
			sCom.setRESTEP(shortService.countShortListComByShortIDX(ARTICLEIDX) + 1);
			
			shortService.insertShortListComByShortIDX(sCom);			
		/* 대댓글일 경우 */	
		} else {
			sCom.setRETYPE(RETYPE);
			sCom.setPCOMIDX(COMIDX);
			
			ShortCom reComInfo = shortService.selectShortCom(COMIDX);
			// 넘겨받은 COMIDX의 RELEVEL값의 +1의 값을 새로운 RELEVEL로 입력
			sCom.setRELEVEL(reComInfo.getRELEVEL() + 1);
			// 새로운 RESTEP값을 구한다
			int newReStep;			
			// 새로운 RELEVEL에 댓글이 없을 때
			if(shortService.nextReStep(RETYPE, sCom.getRELEVEL(), COMIDX, ARTICLEIDX) == 0) {
				newReStep = reComInfo.getRESTEP() + 1;
			} else { // 새로운 RELEVEL에 댓글이 있을 때
				newReStep = shortService.nextReStep(RETYPE, sCom.getRELEVEL(), COMIDX, ARTICLEIDX) + 1;
			}
					
			sCom.setRESTEP(newReStep);
			
			// 게시글의 댓글 중 새로운 RESTEP값보다 같거나 큰 댓글들의 모든 RESTEP값을 1씩 증가  
			shortService.increaseReStepEqAndGreaterShort(newReStep, RETYPE, ARTICLEIDX);
			
			shortService.insertShortListFirstComByShortIDX(sCom);
		}	
		/* jsp로 넘겨줄 페이징한 댓글 리스트 html을 생성 */
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		// currentPage는 parameter로 넘겨받음
		
		int countShortCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "shortDetail.cut";
		String searchUrl = "&SHORTIDX=" + ARTICLEIDX;
		
		START = 1 + pageSize * (currentPage - 1);
		END = pageSize * currentPage;
		/* 페이징을 위한 값 계산 */
		countShortCom = shortService.countShortListComByShortIDX(ARTICLEIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countShortCom, pageBlock, pageSize, currentPage, url, searchUrl);
		List<ShortCom> shortComList = new ArrayList<ShortCom>();
		shortComList = shortService.shortListComPagingByShortIDX(START, END, ARTICLEIDX);
		
		/* StringBuffer에 새로 출력해줄 댓글 리스트 생성 */
		String newComList = MakeComList.make(shortComList, request, "SHO", ARTICLEIDX, currentPage);
		
		/* 새로 출력할 페이징 생성*/
		StringBuffer pb = new StringBuffer();
		pb.append("<span id=\"pageBody\">");
		pb.append(paging.getPageHtml().toString());
		pb.append("</span>");
		String newComPage = pb.toString();
		
		msg.put("newComList", newComList);
		msg.put("newComPage", newComPage);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/shortComModify.cut")
	public Map<String, String> shortComModify(int COMIDX, String CONTENT) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		ShortCom sCom = new ShortCom();
		sCom.setSHORTCOMIDX(COMIDX);
		sCom.setCONTENT(CONTENT);
		
		shortService.updateShortListComByShortIDX(sCom);		
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/shortComDelete.cut")
	public Map<String, String> shortDelete(int COMIDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 단축키게시판의 댓글 삭제 : ISDEL을 'Y'로 변환
		shortService.updateShortListComDelByShortIDX(COMIDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/shortComPassword.cut")
	public String shortComPassword(int COMIDX) throws Exception {

		/* 단축키게시판 댓글의 SHORTCOMIDX의 비밀번호를 리턴 */
		ShortCom shortCom = shortService.selectShortCom(COMIDX);
		String password = null;
		if(shortCom != null) {
			password = shortCom.getPASSWORD();
		}
		
		return password;		
	}
	
	@RequestMapping("/shortSavePdf.cut")
	public String shortSavePdf(Model model, HttpServletRequest request) throws Exception {
		String STYPE = request.getParameter("STYPE");
		
		List<sc.model.Short> list = shortService.shortListSTYPE(STYPE);
		
		model.addAttribute("list", list);
		
		return "pdfView";
	}		
}

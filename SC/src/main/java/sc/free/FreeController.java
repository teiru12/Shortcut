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

import sc.bookmark.BookmarkService;
import sc.goodbad.GoodbadService;
import sc.model.Bookmark;
import sc.model.Free;
import sc.model.FreeCom;
import sc.model.Goodbad;
import sc.model.Notice;
import sc.notice.NoticeService;
import sc.util.GetIP;
import sc.util.MakeComList;
import sc.util.Paging;

@Controller 
public class FreeController {
	
	@Resource(name = "freeService")
	private FreeService freeService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@Resource(name = "bookmarkService")
	private BookmarkService bookmarkService;
	
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

		/* 조회수 증가 */
		freeService.updateFreeReadcount(FREEIDX);		
		
		/* 게시글 상세 정보 읽어옴 */
		Free freeDetail = freeService.selectFreeIDX(FREEIDX);
		
		/* 좋아요 싫어요를 했는지, 즐겨찾기를 했는지 여부 검사 */
		Goodbad gb = new Goodbad();
		Bookmark bk = new Bookmark();
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "FRE", FREEIDX);
			bk = bookmarkService.selectBookmark(id, "FRE", FREEIDX);
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
		/* 좋아요/싫어요가 있을 경우 모델에 삽입 */
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		/* 즐겨찾기가 있을 경우 모델에 삽입 */
		if(id !=null && bk != null) {
			model.addAttribute("bookmark", bk);
		}
		
		return "freeDetail";
	}
	
	@ResponseBody
	@RequestMapping("/freeDelete.cut")
	public Map<String, String> freeDelete(int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		// 자유게시판의 게시글 삭제 : ISDEL을 'Y'로 변환
		freeService.updateFreeListDel(IDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/freeDetailPassword.cut")
	public String freeDetailPassword(int IDX) throws Exception {

		/* 자유게시판의 IDX 게시글의 비밀번호를 리턴 */
		Free free = freeService.selectFreeIDX(IDX);
		String password = null;
		if(free != null) {
			password = free.getPASSWORD();
		}
		
		return password;		
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
	
	@ResponseBody
	@RequestMapping("/freeComWrite.cut")
	public Map<String, String> freeComWrite(HttpServletRequest request, int ARTICLEIDX, String CONTENT, 
			String ID, String PASSWORD, int RETYPE, int COMIDX, int currentPage) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		/* 입력에서 사용할 FreeCom 객체에 내용 입력 */
		FreeCom fCom = new FreeCom();
		fCom.setFREEIDX(ARTICLEIDX);
		fCom.setCONTENT(CONTENT);
		fCom.setID(ID);
		String ip = null;
		if(ID.equals("비회원")) {
			ip = GetIP.get(request);
		}
		fCom.setIP(ip);
		if(PASSWORD.equals("")) {
			PASSWORD = null;
		}
		fCom.setPASSWORD(PASSWORD);
		
		/* 일반 댓글일 경우 */
		if(RETYPE == 0) {
			fCom.setRELEVEL(0);
			// 현재 게시글의 댓글 수를 구한뒤 +1을 한 값을 RESTEP값에 입력
			fCom.setRESTEP(freeService.countFreeComByFreeIDX(ARTICLEIDX) + 1);
			
			freeService.insertFreeListFirstComByFreeIDX(fCom);			
		/* 대댓글일 경우 */	
		} else {
			fCom.setRETYPE(RETYPE);
			fCom.setPCOMIDX(COMIDX);
			
			FreeCom reComInfo = freeService.selectFreeCom(COMIDX);
			// 넘겨받은 COMIDX의 RELEVEL값의 +1의 값을 새로운 RELEVEL로 입력
			fCom.setRELEVEL(reComInfo.getRELEVEL() + 1);
			
			// 새로운 RESTEP값을 구한다
			int newReStep;			
			// 새로운 RELEVEL에 댓글이 없을 때
			if(freeService.nextReStep(RETYPE, fCom.getRELEVEL(), COMIDX, ARTICLEIDX) == 0) {
				newReStep = reComInfo.getRESTEP() + 1;
			} else { // 새로운 RELEVEL에 댓글이 있을 때
				newReStep = freeService.nextReStep(RETYPE, fCom.getRELEVEL(), COMIDX, ARTICLEIDX) + 1;
			}
					
			fCom.setRESTEP(newReStep);

			// 게시글의 댓글 중 새로운 RESTEP값보다 같거나 큰 댓글들의 모든 RESTEP값을 1씩 증가  
			freeService.increaseReStepEqAndGreater(newReStep, RETYPE, ARTICLEIDX);
			
			freeService.insertFreeListComByFreeIDX(fCom);
		}	
		
		/* jsp로 넘겨줄 페이징한 댓글 리스트 html을 생성 */
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		// currentPage는 parameter로 넘겨받음
		
		int countFreeCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "freeDetail.cut";
		String searchUrl = "&FREEIDX=" + ARTICLEIDX;
		
		/* 페이징을 위한 값 계산 */
		countFreeCom = freeService.countFreeComByFreeIDX(ARTICLEIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countFreeCom, pageBlock, pageSize, currentPage, url, searchUrl);
		List<FreeCom> freeComList = new ArrayList<FreeCom>();
		freeComList = freeService.freeListComPagingByFreeIDX(START, END, ARTICLEIDX);
		
		/* StringBuffer에 새로 출력해줄 댓글 리스트 생성 */
		String newComList = MakeComList.make(freeComList, request, "FRE", ARTICLEIDX, currentPage);
		
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
	@RequestMapping("/freeComPassword.cut")
	public String freeComPassword(int COMIDX) throws Exception {

		/* 자유게시판 댓글의 FREECOMIDX의 비밀번호를 리턴 */
		FreeCom freeCom = freeService.selectFreeCom(COMIDX);
		String password = null;
		if(freeCom != null) {
			password = freeCom.getPASSWORD();
		}
		
		return password;		
	}
	
	@ResponseBody
	@RequestMapping("/freeComModify.cut")
	public Map<String, String> freeComModify(int COMIDX, String CONTENT) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		FreeCom fCom = new FreeCom();
		fCom.setFREECOMIDX(COMIDX);
		fCom.setCONTENT(CONTENT);
		
		freeService.updateFreeListComByFreeIDX(fCom);		
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/freeComDelete.cut")
	public Map<String, String> freeComDelete(int COMIDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		freeService.updateFreeListComDelByFreeIDX(COMIDX);
		
		return msg;
	}
}
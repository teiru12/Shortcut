package sc.info;

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
import sc.model.Info;
import sc.model.InfoCom;
import sc.model.Notice;
import sc.notice.NoticeService;
import sc.util.GetIP;
import sc.util.MakeComList;
import sc.util.Paging;

@Controller
public class InfoController {

	@Resource(name = "infoService")
	private InfoService infoService;

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@Resource(name = "goodbadService")
	private GoodbadService goodbadService;
	
	@Resource(name = "bookmarkService")
	private BookmarkService bookmarkService;

	@RequestMapping(value = "infoList.cut")
	public String infoList(HttpServletRequest request, Model model) throws Exception {

		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 게시글 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1;

		int infoCount;
		int pageBlock = 10;
		String url = "infoList.cut";
		String searchUrl = "";

		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
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
		
		if (KEYWORD == null || KEYWORD.trim() == "") { // 검색 미적용일때
			infoCount = infoService.countInfoList();
		} else { // 감색했을때
			infoCount = infoService.countInfoListSearch(KEYWORD);
		}

		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소입력
		Paging paging = new Paging(infoCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Info> infoList = new ArrayList<Info>();
		List<Notice> noticeTopList = new ArrayList<Notice>();

		/* 페이징 리스트 불러오기 */
		if (KEYWORD == null || KEYWORD.trim() == "") {
			infoList = infoService.infoListPaging(START, END);
		} else {
			infoList = infoService.infoListSearchPaging(START, END, KEYWORD, KEYWORD);
		}

		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);

		/* 상단 공지사항 불러오기 및 조건 */
		noticeTopList = noticeService.noticeListPaging(1, 3);

		model.addAttribute("infoList", infoList);
		model.addAttribute("noticeTopList", noticeTopList);
		
		model.addAttribute("KEYWORD", KEYWORD);

		return "infoList";
	}

	@RequestMapping(value = "infoDetail.cut")
	public String infoDetail(int INFOIDX, Info info, InfoCom infoCom, Model model, HttpServletRequest request) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");
		
		/* 조회수 증가 */
		infoService.updateInfoReadcount(INFOIDX);
		
		/* 게시글 상세 정보 */
		Info infoDetail = infoService.selectInfoIDX(INFOIDX);
		
		/* 좋아요 싫어요를 했는지, 즐겨찾기를 했는지 여부 검사 */
		Goodbad gb = new Goodbad();
		Bookmark bk = new Bookmark();
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "INF", INFOIDX);
			bk = bookmarkService.selectBookmark(id, "INF", INFOIDX);
		}
		
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지
		
		int countInfoCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "infoDetail.cut";
		String searchUrl = "&INFOIDX=" + INFOIDX;
		
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 페이징을 위한 값 계산 */
		countInfoCom = infoService.countInfoComByInfoIDX(INFOIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countInfoCom, pageBlock, pageSize, currentPage, url, searchUrl);
		
		List<InfoCom> infoComList = new ArrayList<InfoCom>();
		infoComList = infoService.infoListComPagingByInfoIDX(START, END, INFOIDX);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);		
		model.addAttribute("infoComList", infoComList);
		
		model.addAttribute("infoDetail", infoDetail);
		
		/* 좋아요/싫어요가 있을 경우 모델에 삽입 */
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		/* 즐겨찾기가 있을 경우 모델에 삽입 */
		if(id !=null && bk != null) {
			model.addAttribute("bookmark", bk);
		}
		
		return "infoDetail";
	}
	
	@RequestMapping("/infoWriteForm.cut")
	public String infoWriteForm(Model model) throws Exception{
		
		return "infoWriteForm";
	}
	
	@ResponseBody
	@RequestMapping("/infoWrite.cut")
	public Map<String, String> infoWrite(String TITLE, String CONTENT,String PASSWORD, HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		Info info = new Info();
		String ID;
		String password;
		String ip;
		  
		if(request.getSession().getAttribute("id") == null ) {//비회원일때
			ID = "비회원";
			password = PASSWORD;
			ip = GetIP.get(request);
		}else {//회원일때
			ID = (String)request.getSession().getAttribute("id");
			password = null;
			ip = null;
		  }
			
		info.setID(ID);
		info.setPASSWORD(password);
		info.setCONTENT(CONTENT);
		info.setTITLE(TITLE);
		info.setIP(ip);
		
		infoService.insertInfoList(info);
		
		msg.put("ID", ID);
		
		return msg;
	}
	
	@RequestMapping("/infoModifyForm.cut")
	public String infoModifyForm(HttpServletRequest request,Model model) throws Exception{
		int IDX = Integer.parseInt(request.getParameter("IDX"));
		
		Info info = infoService.selectInfoIDX(IDX);
		model.addAttribute("info",info);
		
		return "infoModifyForm";
	}
	
	@ResponseBody
	@RequestMapping("/infoModify.cut")
	public Map<String, String> infoModify(String TITLE, String CONTENT,int IDX, HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		Info info = new Info();
		
		info.setINFOIDX(IDX);
		info.setTITLE(TITLE);
		info.setCONTENT(CONTENT);
		
		infoService.updateInfoList(info);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/infoDelete.cut")
	public Map<String, String> infoDelete(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		//정보교류게시판의 게시글 삭제 : ISDEL을 'Y'로 변환
		infoService.updateInfoListDEL(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/infoDetailPassword.cut")
	public String infoDetailPassword(int IDX) throws Exception {

		/* 정보교류게시판의 IDX 게시글의 비밀번호를 리턴 */
		Info info = infoService.selectInfoIDX(IDX);
		String password = null;
		if(info != null) {
			password = info.getPASSWORD();
		}
		
		return password;		
	}
	
	@ResponseBody
	@RequestMapping("/infoGood.cut")
	public Map<String, String> infoGood(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 정보교류게시판 IDX 게시글의 좋아요 1 증가
		infoService.updateInfoGood(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/infoBad.cut")
	public Map<String, String> infoBad(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 정보교류게시판 IDX 게시글의 좋아요 1 증가
		infoService.updateInfoBad(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/infoComWrite.cut")
	public Map<String, String> infoComWrite(HttpServletRequest request, int ARTICLEIDX, String CONTENT, 
			String ID, String PASSWORD, int RETYPE, int COMIDX, int currentPage) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		/* 입력에서 사용할 InfoCom 객체에 내용 입력 */
		InfoCom iCom = new InfoCom();
		iCom.setINFOIDX(ARTICLEIDX);
		iCom.setCONTENT(CONTENT);
		iCom.setID(ID);
		String ip = null;
		if(ID.equals("비회원")) {
			ip = GetIP.get(request);
		}
		iCom.setIP(ip);
		if(PASSWORD.equals("")) {
			PASSWORD = null;
		}
		iCom.setPASSWORD(PASSWORD);
		
		/* 일반 댓글일 경우 */
		if(RETYPE == 0) {
			iCom.setRELEVEL(0);
			// 현재 게시글의 댓글 수를 구한뒤 +1을 한 값을 RESTEP값에 입력
			iCom.setRESTEP(infoService.countInfoComByInfoIDX(ARTICLEIDX) + 1);
			
			infoService.insertInfoListFirstComByInfoIDX(iCom);			
		/* 대댓글일 경우 */	
		} else {
			iCom.setRETYPE(RETYPE);
			iCom.setPCOMIDX(COMIDX);
			
			InfoCom reComInfo = infoService.selectInfoCom(COMIDX);
			// 넘겨받은 COMIDX의 RELEVEL값의 +1의 값을 새로운 RELEVEL로 입력
			iCom.setRELEVEL(reComInfo.getRELEVEL() + 1);
			
			// 새로운 RESTEP값을 구한다
			int newReStep;			
			// 새로운 RELEVEL에 댓글이 없을 때
			if(infoService.nextReStep(RETYPE, iCom.getRELEVEL(), COMIDX, ARTICLEIDX) == 0) {
				newReStep = reComInfo.getRESTEP() + 1;
			} else { // 새로운 RELEVEL에 댓글이 있을 때
				newReStep = infoService.nextReStep(RETYPE, iCom.getRELEVEL(), COMIDX, ARTICLEIDX) + 1;
			}
					
			iCom.setRESTEP(newReStep);

			// 게시글의 댓글 중 새로운 RESTEP값보다 같거나 큰 댓글들의 모든 RESTEP값을 1씩 증가  
			infoService.increaseReStepEqAndGreater(newReStep, RETYPE, ARTICLEIDX);
			
			infoService.insertInfoListComByInfoIDX(iCom);
		}	
		
		/* jsp로 넘겨줄 페이징한 댓글 리스트 html을 생성 */
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		// currentPage는 parameter로 넘겨받음
		
		int countInfoCom; // 전체 댓글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "InfoDetail.cut";
		String searchUrl = "&INFOIDX=" + ARTICLEIDX;
		
		/* 페이징을 위한 값 계산 */
		countInfoCom = infoService.countInfoComByInfoIDX(ARTICLEIDX);
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countInfoCom, pageBlock, pageSize, currentPage, url, searchUrl);
		List<InfoCom> infoComList = new ArrayList<InfoCom>();
		infoComList = infoService.infoListComPagingByInfoIDX(START, END, ARTICLEIDX);
		
		/* StringBuffer에 새로 출력해줄 댓글 리스트 생성 */
		String newComList = MakeComList.make(infoComList, request, "INF", ARTICLEIDX, currentPage);
		
		/* 새로 출력할 페이징 생성*/
		StringBuffer pb = new StringBuffer();
		pb.append("<span id=\"pageBody\">");
		pb.append(paging.getPageHtml().toString());
		pb.append("</span>");
		String newComPage = pb.toString();
		
		map.put("newComList", newComList);
		map.put("newComPage", newComPage);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/infoComPassword.cut")
	public String infoComPassword(int COMIDX) throws Exception {

		/* 자유게시판 댓글의 INFOCOMIDX의 비밀번호를 리턴 */
		InfoCom infoCom = infoService.selectInfoCom(COMIDX);
		String password = null;
		if(infoCom != null) {
			password = infoCom.getPASSWORD();
		}
		
		return password;		
	}
	
	@ResponseBody
	@RequestMapping("/infoComModify.cut")
	public Map<String, String> infoComModify(int COMIDX, String CONTENT) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		InfoCom iCom = new InfoCom();
		iCom.setINFOCOMIDX(COMIDX);
		iCom.setCONTENT(CONTENT);
		
		infoService.updateInfoListComByInfoIDX(iCom);		
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/infoComDelete.cut")
	public Map<String, String> infoComDelete(int COMIDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		infoService.updateInfoListComDelByInfoIDX(COMIDX);
		
		return map;
	}
}
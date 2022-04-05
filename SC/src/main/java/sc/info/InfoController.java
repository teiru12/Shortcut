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

import sc.model.Info;
import sc.model.InfoCom;
import sc.model.Notice;
import sc.notice.NoticeService;
import sc.util.Paging;

@Controller
public class InfoController {

	@Resource(name = "infoService")
	private InfoService infoService;

	@Resource(name = "noticeService")
	private NoticeService noticeService;

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

		return "infoList";
	}

	@RequestMapping(value = "infoDetail.cut")
	public String infoDetail(int INFOIDX, Info info, InfoCom infoCom, Model model, HttpServletRequest request) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");
		
		
		Info infoDetail = infoService.selectInfoIDX(INFOIDX);
		
		model.addAttribute("infoDetail", infoDetail);
		
		
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

		
		return "infoDetail";
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
}

package sc.notice;

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
	
	@RequestMapping(value = "noticeDetail.cut")
	public String noticeDetail(int NOTICEIDX, Notice notice, Model model, HttpServletRequest request) throws Exception {
		
		String id = (String) request.getSession().getAttribute("id");

		/* 조회수 증가 */
		noticeService.updateNoticeReadcount(NOTICEIDX);		
		
		/* 게시글 상세 정보 읽어옴 */
		Notice noticeDetail = noticeService.selectNoticeIDX(NOTICEIDX);
		
		/* 좋아요 싫어요를 했는지, 즐겨찾기를 했는지 여부 검사 */
		Goodbad gb = new Goodbad();
		Bookmark bk = new Bookmark();
		if(id != null) {
			gb = goodbadService.selectGoodbad(id, "NOT", NOTICEIDX);
			bk = bookmarkService.selectBookmark(id, "NOT", NOTICEIDX);
		}
		
		model.addAttribute("noticeDetail", noticeDetail);
		/* 좋아요/싫어요가 있을 경우 모델에 삽입 */
		if(id != null && gb != null) {
			model.addAttribute("goodUsed", gb.getGOOD());
			model.addAttribute("badUsed", gb.getBAD());
		}
		/* 즐겨찾기가 있을 경우 모델에 삽입 */
		if(id !=null && bk != null) {
			model.addAttribute("bookmark", bk);
		}
		
		return "noticeDetail";
	}
	
	@ResponseBody
	@RequestMapping("/noticeDelete.cut")
	public Map<String, String> noticeDelete(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 공지게시판의 게시글 삭제 : ISDEL을 'Y'로 변환
		noticeService.updateNoticeListDel(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/noticeGood.cut")
	public Map<String, String> noticeGood(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 공지게시판의 IDX의 게시글의 좋아요 1 증가
		noticeService.updateNoticeGood(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/noticeBad.cut")
	public Map<String, String> noticeBad(int IDX) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		// 공지게시판의 IDX의 게시글의 싫어요 1 증가
		noticeService.updateNoticeBad(IDX);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/noticeWrite.cut")
	public Map<String, String> noticeWrite(String TITLE, String CONTENT, HttpServletRequest request)throws Exception  {
		Map<String, String> map = new HashMap<String, String>();
		
		Notice notice = new Notice();
		
		notice.setTITLE(TITLE);
		notice.setCONTENT(CONTENT);
		
		noticeService.insertNoticeList(notice);
		
		
		
		return map;
	}
	
	@RequestMapping("/noticeWriteForm.cut")
	public String noticeWriteForm(Model model) throws Exception {
		
		return "noticeWriteForm";
	}
	
	@ResponseBody
	@RequestMapping("noticeModify.cut")
	public Map<String, String> noticeModify(String TITLE, String CONTENT, int IDX, HttpServletRequest request)throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Notice notice = new Notice();
		
		notice.setNOTICEIDX(IDX);
		notice.setTITLE(TITLE);
		notice.setCONTENT(CONTENT);
		
		noticeService.updateNoticeList(notice);
		
		return msg;
	}
	
	@RequestMapping("noticeModifyForm.cut")
	public String noticeModifyForm(HttpServletRequest request, Model model)throws Exception {
		int IDX = Integer.parseInt(request.getParameter("IDX"));
		
		Notice notice = noticeService.selectNoticeIDX(IDX);
		model.addAttribute("notice", notice);
		
		return "noticeModifyForm";
	}
	
}

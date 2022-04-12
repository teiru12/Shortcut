package sc.bookmark;

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

import sc.model.Bookmark;
import sc.util.Paging;

@Controller
public class BookmarkController {
	
	@Resource(name="bookmarkService")
	private BookmarkService bookmarkService;
	
	@RequestMapping(value="/bookmarkList.cut")
	public String bookmarkList(Model model, HttpServletRequest request) throws Exception {
		List<Bookmark> bookmarklist = new ArrayList<Bookmark>();
		/* 세션으로부터 로그인 id를 읽어옴 */
		String ID = (String) request.getSession().getAttribute("id");
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int bookmarkshortListCount; // 전체 즐겨찾기 단축키의 수
		int bookmarkboardListCount; // 전체 즐겨찾기 게시판의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "bookmarkList.cut";
		String searchUrl = "";
		String STYPE = null;
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		STYPE = request.getParameter("STYPE");
		
		if(STYPE != null ) {
			searchUrl += "&STYPE=" + STYPE;
		}
		
		/* 페이징을 위한 값 계산 */
		bookmarkshortListCount = bookmarkService.countBookmarkShort(ID);
		bookmarkboardListCount = bookmarkService.countBookmarkBoard(ID);
		/* 회원의 followList 페이징 정보를 읽어옴 */
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging;
		Paging paging2;
		if(STYPE == null) {//단축키게시판
			paging = new Paging(bookmarkshortListCount, pageBlock, pageSize, currentPage, url, searchUrl);
			bookmarklist = bookmarkService.bookmarkShortListPaging(START, END, ID);
			model.addAttribute("bookmarklist", bookmarklist);
			model.addAttribute("paging", paging);
		}else {//즐겨찾기게시판
			paging2 = new Paging(bookmarkboardListCount, pageBlock, pageSize, currentPage, url, searchUrl);
			bookmarklist = bookmarkService.bookmarkBoardListPaging(START, END, ID);
			model.addAttribute("bookmarklist", bookmarklist);
			model.addAttribute("paging2", paging2);
		}
		
		
		model.addAttribute("currentPage", currentPage);
		
		model.addAttribute("STYPE", STYPE);
		
		return "bookmarkList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/bookmarkshortDelete.cut")
	@ResponseBody
	public Map<String, String> bookmarkshortDelete(HttpServletRequest request, int BOOKMARKIDX, String ID, int currentPage) throws Exception {
		
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 팔로우 삭제*/
		bookmarkService.deleteBookmarkById(BOOKMARKIDX);
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		// int currentPage // currentPage는 파라미터로 전달받음
		int START = 1 + pageSize * (currentPage - 1);
		int END = pageSize * currentPage;

		int bookmarkshortListCount; // 전체 팔로우의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "bookmarkList.cut";
		String searchUrl = "";
		/* 페이징을 위한 값 계산 */
		bookmarkshortListCount = bookmarkService.countBookmarkShort(ID);
		
		// 만약 삭제를 했을 때 currentPage의 마지막 아이템을 삭제했을 경우 currentPage를 이전 페이지로 설정
		if(bookmarkshortListCount <= ((currentPage-1)*pageSize)) {
			currentPage = currentPage-1;
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		Paging paging = new Paging(bookmarkshortListCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Bookmark> bookmarkshortList = bookmarkService.bookmarkShortListPaging(START, END, id);
		
		/* StringBuffer에 새로 생성할 팔로우 테이블 생성 */
		StringBuffer sb = new StringBuffer();

		sb.append("<tbody id=\"bookmarkBody\">");
		for(int i=0;i<bookmarkshortList.size();i++) {
			sb.append("<tr>");
			if(((Map<String, Object>)bookmarkshortList.get(i)).get("STYPE").equals("E")) {
				sb.append("<td width=\"15%\">이클립스</td>");
			}else if(((Map<String, Object>)bookmarkshortList.get(i)).get("STYPE").equals("H")) {
				sb.append("<td width=\"15%\">한글</td>");
			}else if(((Map<String, Object>)bookmarkshortList.get(i)).get("STYPE").equals("W")) {
				sb.append("<td width=\"15%\">왼도우</td>");
			}else if(((Map<String, Object>)bookmarkshortList.get(i)).get("STYPE").equals("N")) {
				sb.append("<td width=\"15%\">노트패드</td>");
			}else if(((Map<String, Object>)bookmarkshortList.get(i)).get("STYPE").equals("M")) {
				sb.append("<td width=\"15%\">오피스</td>");
			}
			sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkshortList.get(i)).get("TITLE") + "</td>");
			sb.append("<td width=\"30%\">" + ((Map<String, Object>)bookmarkshortList.get(i)).get("CONTENT") + "</td>");			
			sb.append("<td width=\"15%\">");
			sb.append("<button class=\"btn btn-sm btn-light\" onClick=\"bookmarkshortDelete(");
			sb.append(((Map<String, Object>)bookmarkshortList.get(i)).get("BOOKMARKIDX") + ",'" + ((Map<String, Object>)bookmarkshortList.get(i)).get("ID") + "'," + currentPage);
			sb.append(")\">삭제</button></td></tr>");
		}		
		sb.append("</tbody>");
		
		String newFollow = sb.toString();
		
		StringBuffer pb = new StringBuffer();
		
		pb.append("<span id=\"pageBody\">");
		pb.append(paging.getPageHtml().toString());
		pb.append("</span>");
		String newPage = pb.toString();
		
		msg.put("newFollow", newFollow);
		msg.put("newPage", newPage);
		
		msg.put("message", "북마크를 삭제하였습니다.");
		
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/bookmarkboardDelete.cut")
	@ResponseBody
	public Map<String, String> bookmarkboardDelete(HttpServletRequest request, int BOOKMARKIDX, String ID, int currentPage) throws Exception {
		
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 팔로우 삭제*/
		bookmarkService.deleteBookmarkById(BOOKMARKIDX);
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 5; // 페이지당 출력할 포인트 정보의 수
		// int currentPage // currentPage는 파라미터로 전달받음
		int START = 1 + pageSize * (currentPage - 1);
		int END = pageSize * currentPage;
		
		int bookmarkboardListCount; // 전체 팔로우의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "bookmarkList.cut";
		String searchUrl = "&STYPE=B";
		/* 페이징을 위한 값 계산 */
		bookmarkboardListCount = bookmarkService.countBookmarkBoard(ID);
		
		// 만약 삭제를 했을 때 currentPage의 마지막 아이템을 삭제했을 경우 currentPage를 이전 페이지로 설정
		if(bookmarkboardListCount <= ((currentPage-1)*pageSize)) {
			currentPage = currentPage-1;
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		Paging paging2 = new Paging(bookmarkboardListCount, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Bookmark> bookmarkboardList = bookmarkService.bookmarkBoardListPaging(START, END, id);
		/* StringBuffer에 새로 생성할 팔로우 테이블 생성 */
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tbody id=\"bookmarkBody\">");
		for(int i=0;i<bookmarkboardList.size();i++) {
			sb.append("<tr>");
			if(((Map<String, Object>)bookmarkboardList.get(i)).get("TYPE").equals("FRE")){
				sb.append("<td width=\"25%\">자유 게시판</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("FTITLE") + "</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("FID") + "</td>");				
			}else if(((Map<String, Object>)bookmarkboardList.get(i)).get("TYPE").equals("NEW")) {
				sb.append("<td width=\"25%\">뉴스 게시판</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("NTITLE") + "</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("NID") + "</td>");		
			}else {
				sb.append("<td width=\"25%\">정보교류 게시판</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("ITITLE") + "</td>");
				sb.append("<td width=\"20%\">" + ((Map<String, Object>)bookmarkboardList.get(i)).get("IID") + "</td>");	
			}			
			sb.append("<td width=\"15%\">");
			sb.append("<button class=\"btn btn-sm btn-light\" onClick=\"bookmarkboardDelete(");
			sb.append(((Map<String, Object>)bookmarkboardList.get(i)).get("BOOKMARKIDX") + ",'" + ((Map<String, Object>)bookmarkboardList.get(i)).get("BID") + "'," + currentPage);
			sb.append(")\">삭제</button></td></tr>");
		}		
		sb.append("</tbody>");
		
		String newFollow = sb.toString();
		
		StringBuffer pb = new StringBuffer();
		
		pb.append("<span id=\"pageBody2\">");
		pb.append(paging2.getPageHtml().toString());
		pb.append("</span>");
		String newPage = pb.toString();
		
		msg.put("newFollow", newFollow);
		msg.put("newPage", newPage);
		
		msg.put("message", "북마크를 삭제하였습니다.");
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/bookmarkDetailDelete.cut")
	public Map<String, String> bookmarkDetailDelete(String ID, String TYPE, int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Bookmark bk = bookmarkService.selectBookmark(ID, TYPE, IDX);
		
		if(bk == null) { // 검색된 즐겨찾기가 없으므로 삭제 불가
			msg.put("message", "즐겨찾기를 삭제할 수 없습니다.");
		} else {
			bookmarkService.deleteBookmarkById(bk.getBOOKMARKIDX());
			msg.put("message", "즐겨찾기를 삭제하였습니다.");
		}
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/bookmark.cut")
	public Map<String, String> bookmark(String ID, String TYPE, int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Bookmark bk = new Bookmark();
		bk.setID(ID);
		bk.setTYPE(TYPE);
		bk.setIDX(IDX);
		
		bookmarkService.insertBookmarkById(bk);
		
		return msg;		
	}	
}
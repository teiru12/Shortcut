package sc.follow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Follow;
import sc.util.Paging;

@Controller
public class FollowController {
	
	@Resource(name="followService")
	private FollowService followService;
	
	// 팔로우 리스트 페이징
	@RequestMapping("/followList.cut")
	public String followList(HttpServletRequest request, Model model) throws Exception {
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countFollowAll; // 전체 팔로우의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "followList.cut";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 페이징을 위한 값 계산 */
		countFollowAll = followService.countFollow(id);
		
		/* 회원의 followList 페이징 정보를 읽어옴 */
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		// 주소 입력
		Paging paging = new Paging(countFollowAll, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Follow> followList = followService.followListPaging(id, START, END);
		
		model.addAttribute("followList", followList);
		model.addAttribute("followCount", countFollowAll);
		
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		
		return "followList";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/followDelete.cut")
	public Map<String, String> followDelete(HttpServletRequest request,
			int FOLLOWIDX, String FOLLOWID, int currentPage) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 팔로우 삭제*/
		followService.deleteFollow(FOLLOWIDX);
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 포인트 정보의 수
		// int currentPage // currentPage는 파라미터로 전달받음
		int START = 1 + pageSize * (currentPage - 1);
		int END = pageSize * currentPage;

		int countFollowAll; // 전체 팔로우의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "followList.cut";
		String searchUrl = "";
		/* 페이징을 위한 값 계산 */
		countFollowAll = followService.countFollow(id);
		
		// 만약 삭제를 했을 때 currentPage의 마지막 아이템을 삭제했을 경우 currentPage를 이전 페이지로 설정
		if(countFollowAll <= ((currentPage-1)*pageSize)) {
			currentPage = currentPage-1;
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		Paging paging = new Paging(countFollowAll, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Follow> followList = followService.followListPaging(id, START, END);
		
		/* StringBuffer에 새로 생성할 팔로우 테이블 생성 */
		StringBuffer sb = new StringBuffer();
		sb.append("<tbody id=\"followBody\">");
		for(int i=0;i<followList.size();i++) {
			sb.append("<tr id=\"follow${follow.FOLLOWIDX}\">");
			
			
			
			sb.append("<td width=\"50%\">");
			sb.append("<div class=\"btn-group dropend me-1 mb-1\">");
			sb.append("<button type=\"button\" class=\"btn btn-icon-default\" data-bs-toggle=\"dropdown\" aria-haspopup=\"false\" ");
			sb.append("aria-expanded=\"false\">" + ((Map<String, Object>)followList.get(i)).get("FOLLOWID") + "</button>");
			sb.append("<div class=\"dropdown-menu\">");
			sb.append("<button class=\"btn btn-icon-default\" onClick=\"writerDetail('" + ((Map<String, Object>)followList.get(i)).get("FOLLOWID") + "')\">회원 정보</button><br>");
			sb.append("<button type=\"button\" class=\"openModal btn btn-icon-default\" data-bs-toggle=\"modal\" ");
			sb.append("data-bs-target=\"#inlineForm\" id=\"sendModal" + i + "\" data-s=\"${follow.ID}\" data-g=\""); 
			sb.append(((Map<String, Object>)followList.get(i)).get("FOLLOWID") + "\">쪽지 보내기</button><br>");
			sb.append("<a class=\"btn btn-icon-default\" href=\"chat.cut\">1:1 채팅</a></div></div></td>");
			sb.append("<td width=\"50%\">");
			sb.append("<button class=\"btn btn-sm btn-light\" onClick=\"followDelete(");
			sb.append(((Map<String, Object>)followList.get(i)).get("FOLLOWIDX") + ",'" + ((Map<String, Object>)followList.get(i)).get("FOLLOWID") + "'," + currentPage);
			sb.append(")\">삭제</button></td></tr>");		
		}		
		sb.append("<tbody>");
		String newFollow = sb.toString();
		
		StringBuffer pb = new StringBuffer();
		pb.append("<span id=\"pageBody\">");
		pb.append(paging.getPageHtml().toString());
		pb.append("</span>");
		String newPage = pb.toString();

		StringBuffer cb = new StringBuffer();
		cb.append("<td colspan=\"2\" style=\"text-align:right\" id=\"countBody\">");
		cb.append("<span>팔로우 : " + countFollowAll+ "명</span>");
		cb.append("</td>");
		String newCount = cb.toString(); 
		
		msg.put("newFollow", newFollow);
		msg.put("newPage", newPage);
		msg.put("newCount", newCount);
		
		msg.put("message", FOLLOWID + "회원을 팔로우 삭제하였습니다.");
		
		return msg;
	}	
	
	@ResponseBody
	@RequestMapping("/isFollow.cut")
	public boolean isFollow(HttpServletRequest request, String FOLLOWID) throws Exception {
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 이미 팔로우한 ID인지 검사 */
		// 팔로우 했을 경우 true, 아닐 경우 false
		if(followService.findFollowId(id, FOLLOWID) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("/addFollow.cut")
	public Map<String, String> addFollow(HttpServletRequest request, String FOLLOWID) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 팔로우할 FOLLOWID가 자기 자신인지를 검사 */
		if(id.equals(FOLLOWID)) {
			msg.put("checkMyId", "SAME");
			return msg;
		}
		
		/* 이미 팔로우한 ID인지 검사 */
		if(followService.findFollowId(id, FOLLOWID) != null) {
			/* 팔로우했을 경우 팔로우하지 않음, 오류 메세지 전달*/	
			msg.put("message", "이미 팔로우한 아이디입니다.");
		} else {
			/* 팔로우하지 않았을 경우 팔로우 */
			Follow fol = new Follow();
			fol.setID(id);
			fol.setFOLLOWID(FOLLOWID);
			
			followService.insertFollow(fol);
			
			msg.put("message", FOLLOWID + "님을 팔로우하였습니다.");
		}
		return msg;
	}
}
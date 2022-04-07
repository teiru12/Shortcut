package sc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Member;
import sc.util.CalculateExp;
import sc.util.Paging;

@Controller
public class AdminMemberController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@RequestMapping(value="adminMemberList.cut")
	public String adminMemberList(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CalculateExp level = new CalculateExp();
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countMember; // 전체 회원의 수
		int countSearchMember; // 검색 회원의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminMemberList.cut";
		String searchUrl = "";
		String KEYWORD = request.getParameter("KEYWORD");
		String STATUS = request.getParameter("STATUS");
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 페이징을 위한 값 계산 */
		countMember = memberService.countMember();
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		if(KEYWORD == null) {
			Paging paging = new Paging(countMember, pageBlock, pageSize, currentPage, url, searchUrl);
			List<Map<String, Object>> memberList = memberService.memberListPaging(START, END);
			for(int i = 0; i < memberList.size(); i++) {
				level.setExp(Integer.parseInt(String.valueOf(memberList.get(i).get("EXP"))));
				level.calculate();
				memberList.get(i).put("level", level.getLevel());
			}
			/* model.addAttribute("levelList", levelList); */
			model.addAttribute("memberList", memberList);
			model.addAttribute("paging", paging);
		}else if(KEYWORD != null){//검색버튼 눌렀을때
			if(STATUS != null) {//회원상태 선택후 검색시
				searchUrl += "&STATUS=" + STATUS;
				map.put("STATUS", STATUS);
			}
			searchUrl += "&KEYWORD=" + KEYWORD;
			map.put("KEYWORD", KEYWORD);
			
			List<Map<String, Object>> memberSearchList = memberService.memberListSearchPaging(START, END, 0, 0, KEYWORD, STATUS);
			countSearchMember = memberService.countMemberSearch(map);
			Paging paging = new Paging(countSearchMember, pageBlock, pageSize, currentPage, url, searchUrl);
			for(int i = 0; i < memberSearchList.size(); i++) {
				level.setExp(Integer.parseInt(String.valueOf(memberSearchList.get(i).get("EXP"))));
				level.calculate();
				memberSearchList.get(i).put("level", level.getLevel());
			}
			model.addAttribute("memberList", memberSearchList);
			model.addAttribute("paging", paging);
		}
		
		model.addAttribute("countMember", countMember);
		model.addAttribute("KEYWORD", KEYWORD);//검색 키워드
		model.addAttribute("STATUS", STATUS);//회원 상태
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		
		
		return "adminMemberList";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="searchLeveladminMemberList.cut")
	public String searchLeveladminMemberList(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CalculateExp level = new CalculateExp();
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10; // 페이지당 출력할 포인트 정보의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countSearchMember; // 검색 회원의 수
		int pageBlock = 5; // 표시할 페이지의 수
		int LEVEL1 = Integer.parseInt(request.getParameter("LEVEL1"));
		int LEVEL2 = Integer.parseInt(request.getParameter("LEVEL2"));
		String url = "searchLeveladminMemberList.cut";
		String searchUrl = "";
		/* 기본 페이지가 아닐 경우 */
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		map.put("LEVEL1", level.levelToMinExp(LEVEL1));
		map.put("LEVEL2", level.levelToMaxExp(LEVEL2));
		searchUrl += "&LEVEL1=" + LEVEL1 + "&LEVEL2=" + LEVEL2;
		/* 페이징을 위한 값 계산 */
		countSearchMember = memberService.countMemberSearch(map);
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할
		Paging paging = new Paging(countSearchMember, pageBlock, pageSize, currentPage, url, searchUrl);
		List<Map<String, Object>> memberList = memberService.memberListSearchPaging(START, END, level.levelToMinExp(LEVEL1), level.levelToMaxExp(LEVEL2), "", "");
		for(int i = 0; i < memberList.size(); i++) {
			level.setExp(Integer.parseInt(String.valueOf(memberList.get(i).get("EXP"))));
			level.calculate();
			memberList.get(i).put("level", level.getLevel());
		}
		/* model.addAttribute("levelList", levelList); */
		model.addAttribute("memberList", memberList);
		model.addAttribute("paging", paging);
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("LEVEL1", LEVEL1);
		model.addAttribute("LEVEL2", LEVEL2);
		
		
		return "adminMemberList";
	}
	
	@RequestMapping(value="adminMemberDetail.cut")
	public String adminMemberDetail(HttpServletRequest request, Model model) throws Exception {
		String ID = request.getParameter("ID");
		Member member = memberService.selectMemberId(ID);
		
		CalculateExp cal = new CalculateExp(member.getEXP());
		int level = cal.getLevel();
		
		model.addAttribute("level", level);
		model.addAttribute("member", member);
		
		return "adminMemberDetail";
	}
	
	@RequestMapping(value="adminMemberModify.cut")
	@ResponseBody
	public void adminMemberModify(String PASSWORD, String STATUS, String NAME, String NICKNAME, String MEMIDX) throws Exception {	
		int memidx = Integer.parseInt(MEMIDX);
		
		Member member = new Member();
		member.setPASSWORD(PASSWORD);
		member.setSTATUS(STATUS);
		member.setNAME(NAME);
		member.setNICKNAME(NICKNAME);
		member.setMEMIDX(memidx);
		
		memberService.updateMemberStatus(member);
	}
}

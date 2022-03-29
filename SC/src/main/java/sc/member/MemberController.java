package sc.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Member;
import sc.util.CalculateExp;

@Controller
public class MemberController {

	@Resource(name="memberService")
	private MemberService memberService;
	
	@RequestMapping(value = "/loginForm.cut")
	public String loginForm(Model model) throws Exception {
		return "loginForm";
	}
	
	@RequestMapping(value = "/login.cut")
	public String login(Member member, HttpServletRequest request, Model model) throws Exception {
		
		return "/member/login";
	}

	@ResponseBody
	@RequestMapping("/logout.cut")
	public Map<String, String> logout(HttpServletRequest request) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		String id = (String) request.getSession().getAttribute("id"); 
		
		request.getSession().invalidate();
		
		msg.put("message", id + "님 로그아웃 하셨습니다.");
		
		return msg;
	}
	
	@RequestMapping(value="/joinForm.cut", method = {RequestMethod.POST, RequestMethod.GET})
	public String joinForm(HttpServletRequest request, Model model) throws Exception{
		return "joinForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/join.cut", method = RequestMethod.POST)
	public void join(@RequestBody Map<String, Object> data, HttpServletRequest request, Model model) throws Exception{
		System.out.println("data : " + data);
	}
	
	//아이디 찾기 페이지
	@RequestMapping("/findIdForm.cut")
	public String findIdForm(Model model) throws Exception {
		
		return "findIdForm";
	}
	
	//아이디 찾기 실행
	@RequestMapping("/findId.cut")
	public String findId(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();		
		
		Member member = memberService.selectMemberFindId(map);
		
		model.addAttribute("id", member);
		
		return "findId";
	}
	
	//비밀번호 찾기 페이지
	@RequestMapping("/findPwForm.cut")
	public String main(Model model) {
		
		return "findPwForm";
	}
	
	@RequestMapping("/myPage.cut")
	public String myPage(HttpServletRequest request, Model model) throws Exception {

// 테스트를 위해 임의로 test1@sc.cut을 세션에 입력
request.getSession().setAttribute("id", "test1@sc.cut");
		
		/* 세션으로부터 로그인 id를 읽어와 해당 id의 회원정보를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		Member member = memberService.selectMemberId(id);
		
		/* 회원의 레벨 계산 */
		CalculateExp cal = new CalculateExp(member.getEXP());
		int level = cal.getLevel();
		
		model.addAttribute("member", member);
		model.addAttribute("level", level);
		
		return "myPage";
	}
	
	@RequestMapping("/userModifyForm.cut")
	public String userModifyForm(HttpServletRequest request, Model model) throws Exception {
		
		/* 세션으로부터 로그인 id를 읽어와 해당 id의 회원정보를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		Member member = memberService.selectMemberId(id);
		
		model.addAttribute("member", member);
		
		return "userModifyForm";
	}
	
	@ResponseBody
	@RequestMapping("/userDelete.cut")
	public Map<String, String> userDelete(HttpServletRequest request) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		/* 세션으로부터 로그인 id를 읽어와 해당 id의 회원정보를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		Member memUpdate = memberService.selectMemberId(id);	
		
		if(memUpdate != null) { // 일치하는 회원정보가 있어서 삭제
			memUpdate.setSTATUS("DEL");
			memberService.updateMemberStatus(memUpdate);
			
			msg.put("message", "삭제되었습니다.");
			
			// 세션 로그아웃
			request.getSession().invalidate();
		} else { // 일치하는 회원정보가 없어서 삭제되지 않음
			msg.put("message", "삭제되지 않았습니다.");
		}
		
		return msg;
	}	
}
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

import sc.mail.MailService;
import sc.model.Member;
import sc.util.CalculateExp;

@Controller
public class MemberController {

	private String TESTEMAIL = null;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="mailService")
	private MailService mailService;
	
	@RequestMapping(value = "/loginForm.cut")
	public String loginForm(Model model) throws Exception {
		return "loginForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login.cut")
	public Map<String, String> login(String ID, String PASSWORD, HttpServletRequest request) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Member m = new Member();
		m.setID(ID);
		m.setPASSWORD(PASSWORD);
		
		Member memberInfo = memberService.selectMemberId(ID);
		
		
		  if(memberInfo == null) { //로그인 실패 
			  msg.put("message", "notExist"); 
		  } else { 
			  if(memberInfo.getPASSWORD().equals(m.getPASSWORD())) {
				  if(!memberInfo.getSTATUS().equals("ON")) { 
					  msg.put("message", "fail"); 
				  } else {
					  msg.put("message", "success");
					  request.getSession().setAttribute("id", ID);
				  }
		  
			  } else {
				  msg.put("message", "invalidPw"); 
			  } 
		  }		
		return msg;
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
	
	//회원가입
	@ResponseBody
	@RequestMapping(value="/join.cut")
	public String join(String ID, String EMAIL, String PASSWORD, String NAME, String NICKNAME) throws Exception{
		Member member = new Member();
		member.setEMAIL(EMAIL);
		member.setID(ID);
		member.setNAME(NAME);
		member.setPASSWORD(PASSWORD);
		member.setNICKNAME(NICKNAME);
		TESTEMAIL = EMAIL;
		
		memberService.insertMember(member); 
		//mailService.sendAuthMail(EMAIL);
		
		System.out.println("ddddddddddddddddd");
		
		return EMAIL;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testemail.cut")
	public void testEmail(Model model) throws Exception {
		mailService.sendAuthMail(TESTEMAIL);
		System.out.println("123");
	}
	
	//회원가입 아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/checkID.cut")
	public boolean checkID(String ID) throws Exception{		
		
		if(memberService.selectMemberId(ID) != null) {
			return false;
		}
		
		return true;
	}
	
	// 메일 인증 후 결과 페이지 연결
	@RequestMapping(value="/testjoin.cut")
	public void testjoin(HttpServletRequest request) throws Exception {
		System.out.println("오나 " + request.getParameter("EMAIL"));
		memberService.testjoin(request.getParameter("EMAIL"));
	}
	
	// 아이디 찾기 페이지
	@RequestMapping("/findIdForm.cut")
	public String findIdForm(Model model) throws Exception {
		
		return "findIdForm";
	}
	
	// 아이디 찾기 실행
	@RequestMapping(value="/findId.cut")
	@ResponseBody
	public String findId(String NAME, String EMAIL, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("NAME", NAME);
		map.put("EMAIL", EMAIL);
		
		String ID = memberService.selectMemberFindId(map);

		return ID;
	}
	
	//비밀번호 찾기 페이지
	@RequestMapping("/findPwForm.cut")
	public String findPwForm(Model model) {
		
		return "findPwForm";
	}
	
	// 비밀번호 찾기 실행
	@ResponseBody
	@RequestMapping(value = "/findPw.cut")
	public String findPw(String ID,String EMAIL)throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		Member memberInfo = new Member();
		
		//EMAIL, ID 읽어옴
		map.put("EMAIL",EMAIL);
		map.put("ID",ID);
		
		memberInfo = memberService.selectMemberFindPw(map);
		String PASSWORD = memberService.selectMemberFindPw(map).getPASSWORD();
		mailService.sendAuthMail2(EMAIL,PASSWORD);
		return "findPw";
	
	}
	
	// 마이 페이지
	@RequestMapping("/myPage.cut")
	public String myPage(HttpServletRequest request, Model model) throws Exception {

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
	
	// 회원정보 수정 폼
	@RequestMapping("/userModifyForm.cut")
	public String userModifyForm(HttpServletRequest request, Model model) throws Exception {
		
		/* 세션으로부터 로그인 id를 읽어와 해당 id의 회원정보를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		Member member = memberService.selectMemberId(id);
		
		model.addAttribute("member", member);
		
		return "userModifyForm";
	}
	
	// 회원정보 수정
	@ResponseBody
	@RequestMapping("/userModify.cut")
	public Map<String, String> userModify(HttpServletRequest request,
			String ID,
			String PASSWORD,
			String NAME,
			String NICKNAME,
			String PROFILE) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
	
		/* ID에 해당하는 회원 정보를 읽어온다 */
		Member memUpdate = memberService.selectMemberId(ID);
		
		if(memUpdate != null) {
			memUpdate.setPASSWORD(PASSWORD);
			memUpdate.setNAME(NAME);
			memUpdate.setNICKNAME(NICKNAME);
			memUpdate.setPROFILE(PROFILE);
			
			memberService.updateMember(memUpdate);
			
			msg.put("message", "수정되었습니다.");
		} else {
			msg.put("message", "일치하는 회원정보가 없습니다.");
		}
		return msg;
	}
	
	// 회원 탈퇴
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
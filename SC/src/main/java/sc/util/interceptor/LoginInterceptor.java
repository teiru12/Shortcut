package sc.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("------------------ Interceptor : LoginInterceptor ------------------ ");
		
return true;
		
//		String email = (String) request.getSession().getAttribute("EMAIL");
//		String uri = request.getRequestURI();
//		
//		/* 로그인하지 않아도 사용할 수 있는 기능들 */
//		// main.al loginForm.al login.al joinForm.al joinSuccess.al 
//		// findId.al findIdResult.al findPw.al findPwResult.al confirmId.al
//		// allList.al aclList.al etcList.al
//		// noticeList.al noticeDetail.al qnaList.al qnaDetail.al
//
//		System.out.println("uri :" + uri);
//		if(email == null &&
//				(
//					uri.equals("/Jumo/main.al") || uri.equals("/Jumo/loginForm.al") || uri.equals("/Jumo/login.al") ||
//					uri.equals("/Jumo/joinForm.al") || uri.equals("/Jumo/joinSuccess.al") || uri.equals("/Jumo/findId.al") ||
//					uri.equals("/Jumo/findIdResult.al") || uri.equals("/Jumo/findPw.al") || uri.equals("/Jumo/findPwResult.al") ||
//					uri.equals("/Jumo/confirmId.al") || uri.equals("/Jumo/allList.al") || uri.equals("/Jumo/aclList.al") ||
//					uri.equals("/Jumo/etcList.al") || uri.equals("/Jumo/noticeList.al") || uri.equals("/Jumo/noticeDetail.al") ||
//					uri.equals("/Jumo/qnaList.al") || uri.equals("/Jumo/qnaDetail.al") || uri.equals("/Jumo/confirmIdAjax.al")
//				)) {
//			System.out.println("- 로그인하지 않음 -");
//			return true;
//		} else if(email != null) {
//			/* 로그인했을 경우 */
//
//				System.out.println("uri.substring(6, 11) : " + uri.substring(6, 11));
//			
//				/* 일반 사용자인 경우 관리자 페이지에 접근 불가 */
//				if( (!email.equals("ADMIN")) && (
//						uri.substring(6, 11).equals("admin") || uri.equals("/Jumo/memberList.al") || uri.equals("/Jumo/memberDetail.al") ||
//						uri.equals("/Jumo/memberModify.al") || uri.equals("/Jumo/memberDelete.al")
//						)) {
//					System.out.println("- 일반 사용자가 관리자 페이지에 접근 -");
//					response.sendRedirect("main.al");
//					return false;					
//				}
//				/* 관리자일 경우와 일반 사용자가 관리자 페이지가 아닌 페이지에 접근할 경우*/
//				else {
//					System.out.println("- 로그인 함 올바른 사용 -");
//					return true;				
//				}				
//		} else {
//			/* 로그인하지 않았을 때 사용할 수 없는 페이지에 접근할 경우 */
//			System.out.println("- 로그인하지않았는데 사용할 수 없는 페이지에 접근 -");
//			
//			/* 성인인증 메시지 출력 후 주소로 이동 */
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('로그인이 필요합니다. 회원가입을 해주세요'); location.href='/Jumo/loginForm.al';</script>");
//			out.flush();
//			
//			// response.sendRedirect("/Jumo/loginForm.al"); return false;
//			 
//			return false;
//		}
	}
}
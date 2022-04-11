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
		
		String id = (String) request.getSession().getAttribute("id");
		String uri = request.getRequestURI();
		
		// return true;
		
		/* 로그인하지 않아도 사용할 수 있는 기능들 */
		// main.cut loginForm.cut, login.cut, join.cut, joinForm.cut
		// testemail.cut checkID.cut testjoin.cut findIdForm.cut findId.cut
		// findPwForm.cut findPw.cut writerDetail.cut
		// free* (freeGood.cut, freeBad.cut 제외)
		// info* (infoGood.cut, infoBad.cut 제외)
		// news* (newsGood.cut, newsBad.cut 제외)
		// noticeList.cut, noticeDetail.cut
		// reportList.cut, reportDetail.cut
		// shortcutList.cut, shortDetail.cut, shortCom*, shortSavePdf.cut

		System.out.println("uri :" + uri);
		if(id == null &&
				(
					uri.equals("/SC/main.cut") || uri.equals("/SC/loginForm.cut") || uri.equals("/SC/login.cut") ||
					uri.equals("/SC/join.cut") || uri.equals("/SC/joinForm.cut") || uri.equals("/SC/testemail.cut") ||
					uri.equals("/SC/checkID.cut") || uri.equals("/SC/testjoin.cut") || uri.equals("/SC/findIdForm.cut") ||
					uri.equals("/SC/findId.cut") || uri.equals("/SC/findPwForm.cut") || uri.equals("/SC/findPw.cut") ||
					uri.equals("/SC/writerDetail.cut") ||
					(uri.substring(4, 8).equals("free") && (! uri.equals("/SC/freeGood.cut")) && (! uri.equals("/SC/freeBad.cut"))) ||
					(uri.substring(4, 8).equals("info") && (! uri.equals("/SC/infoGood.cut")) && (! uri.equals("/SC/infoBad.cut"))) ||
					(uri.substring(4, 8).equals("news") && (! uri.equals("/SC/newsGood.cut")) && (! uri.equals("/SC/newsBad.cut"))) ||
					uri.equals("/SC/noticeList.cut") || uri.equals("/SC/noticeDetail.cut") || uri.equals("/SC/reportList.cut") || uri.equals("/SC/reportDetail.cut") ||
					uri.substring(4, 12).equals("shortCom") ||
					uri.equals("/SC/shortcutList.cut") || uri.equals("/SC/shortDetail.cut") || uri.equals("/SC/shortSavePdf.cut")
				)) {
			System.out.println("- 로그인하지 않음 -");
			return true;
		} else if(id != null) {
			/* 로그인했을 경우 */
			
				/* 일반 사용자인 경우 관리자 페이지에 접근 불가 */
				// adminMainPage.cut adminMemberList.cut adminMemberDetail.cut adminMemberModify.cut adminShort*
				// searchLeveladminMemberList.cut 
				// noticeDelete.cut noticeWrite.cut noticeWriteForm.cut noticeModify.cut noticeModifyForm.cut
				// reportComWrite.cut reportComModify.cut reportComDelete.cut
				if( (!id.equals("ADMIN")) && (
						uri.substring(6, 11).equals("admin") || uri.equals("/SC/searchLeveladminMemberList.cut") || uri.equals("/SC/noticeDelete.cut") ||
						uri.equals("/SC/noticeWrite.cut") || uri.equals("/SC/noticeWriteForm.cut") || uri.equals("/SC/noticeModify.cut") ||
						uri.equals("/SC/noticeModifyForm.cut") || uri.equals("/SC/reportComWrite.cut") || uri.equals("/SC/reportComModify.cut") ||
						uri.equals("/SC/reportComDelete.cut")
						)) {
					System.out.println("- 일반 사용자가 관리자 페이지에 접근 -");
					response.sendRedirect("main.cut");
					return false;					
				}
				/* 관리자일 경우와 일반 사용자가 관리자 페이지가 아닌 페이지에 접근할 경우*/
				else {
					System.out.println("- 로그인 함 올바른 사용 -");
					return true;				
				}				
		} else {
			/* 로그인하지 않았을 때 사용할 수 없는 페이지에 접근할 경우 */
			System.out.println("- 로그인하지않았는데 사용할 수 없는 페이지에 접근 -");
			
			/* 성인인증 메시지 출력 후 주소로 이동 */
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다. 회원가입을 해주세요'); location.href='/SC/loginForm.cut';</script>");
			// 스위트 얼럿 적용 코드 - dom 생성 작업이 필요함 차후 구현 필요
//			out.println("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>");
//			out.println("<script>					swal({\r\n"
//					+ "						title				: '로그인이 필요합니다',\r\n"
//					+ "						text 				: '로그인을 해주세요.',\r\n"
//					+ "						closeOnClickOutside	: false, // alert 창 제외하고 밖 클릭해도 창 안 닫히게\r\n"
//					+ "						buttons				: {\r\n"
//					+ "							confirm : {\r\n"
//					+ "								text 		: '확인',\r\n"
//					+ "								value 		: true,\r\n"
//					+ "								className 	: 'btn btn-primary' \r\n"
//					+ "							}\r\n"
//					+ "						}\r\n"
//					+ "					}).then((result) => {\r\n"
//					+ "						location.href=\"/SC/loginForm.cut\";\r\n"
//					+ "					});</script>");
			out.flush();
			
			// response.sendRedirect("/SC/loginForm.cut"); return false;
			 
			return false;
		}
	}
}
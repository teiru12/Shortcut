package sc.message;

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

import sc.model.Message;
import sc.util.Paging;

@Controller
public class MessageController {

	@Resource(name="messageService")
	private MessageService messageService;
	
	
	@RequestMapping("/messageList.cut")
	public String messageList(HttpServletRequest request, Model model) throws Exception {
		
		/* 세션으로부터 로그인 id를 읽어옴 */
		String id = (String) request.getSession().getAttribute("id");
		
		/* 페이징 변수 설정 */
		int pageSize = 10;
		int START = 1;
		int END = pageSize;
		int currentPage = 1;		
		
		int messageCount = 0;
		int pageBlock = 10;
		String url = "messageList.cut";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize * (currentPage - 1);
			END = pageSize * currentPage;
		}
		
		/* 메세지 출력 테이블을 결정 */
		// 받은 메세지일 경우 get, 보낸 메세지일 경우 send
		String msg = request.getParameter("msg");
		if(msg == null) {
			msg = "get";
		}
		
		if(msg.equals("get")) {
			messageCount = messageService.countGetMessageList(id);
			searchUrl += "&msg=get";
		} else if(msg.equals("send")) {
			messageCount = messageService.countSendMessageList(id);
			searchUrl += "&msg=send";
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소입력
		Paging paging = new Paging(messageCount, pageBlock, pageSize, currentPage, url, searchUrl);
		
		List<Message> msgList = new ArrayList<Message>();
		
				
		/* 페이징 리스트 불러오기 */
		if(msg.equals("get")) {
			msgList = messageService.getMessageListPagingById(START, END, id);
		} else if(msg.equals("send")) {
			msgList = messageService.sendMessageListPagingById(START, END, id);
		}
				
				
		/* 페이징을 위한 값 삽입 */
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
				
		model.addAttribute("msgList", msgList);		
		model.addAttribute("status", msg);
		
		return "messageList";
	}
	
	@RequestMapping("/messageDetail.cut")
	public String messageDetail(HttpServletRequest request, Model model) throws Exception{
		
		/* 파라미터로 전달된 MESSAGEIDX를 이용해 메세지 정보를 읽어옴 */
		int MSGIDX = Integer.parseInt(request.getParameter("MSGIDX"));
		Message msg = messageService.selectMessageByMsgIDX(MSGIDX);
		
		String status = request.getParameter("msg"); // 메세지를 보낸 리스트를 표시, msg=send면 보낸 메세지 리스트이므로 답장을 표시하지 않음
		
		model.addAttribute("msg", msg);
		model.addAttribute("status", status);
		
		return "messageDetail";				
	}
	
	@ResponseBody
	@RequestMapping("/messageDelete.cut")
	public Map<String, String> messageDelete(int MSGIDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		messageService.updateMessageByMsgIDX(MSGIDX);
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/messageSend.cut")
	public Map<String, String> messageSend(String SENDID, String GETID, String CONTENT) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Message message = new Message();
		
		message.setGETID(GETID);
		message.setSENDID(SENDID);
		message.setCONTENT(CONTENT);
		
		messageService.insertMessage(message);

		msg.put("GETID", GETID);
		
		return msg;
	}	
}
package sc.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Message;

@Controller
public class MessageController {

	@Resource(name="messageService")
	private MessageService messageService;
	
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
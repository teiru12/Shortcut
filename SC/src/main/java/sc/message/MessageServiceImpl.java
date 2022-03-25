package sc.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Message;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource(name = "messageDAO")
	private MessageDAO messageDAO;
	
	@Override
	public List<Map<String, Object>> sendMessageListPagingById(int START, int END, String SENDID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("SENDID", SENDID);
		
		return messageDAO.sendMessageListPagingById(map);
	}

	@Override
	public int countSendMessageList(String SENDID) throws Exception {
		return messageDAO.countSendMessageList(SENDID);
	}

	@Override
	public List<Map<String, Object>> getMessageListPagingById(int START, int END, String GETID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("SENDID", GETID);
		
		return messageDAO.getMessageListPagingById(map);
	}

	@Override
	public int countGetMessageList(String GETID) throws Exception {
		return messageDAO.countGetMessageList(GETID);
	}

	@Override
	public Message selectMessageByMsgIDX(int MSGIDX) throws Exception {
		return messageDAO.selectMessageByMsgIDX(MSGIDX);
	}

	@Override
	public void updateMessageByMsgIDX(int MEMIDX) throws Exception {
		messageDAO.updateMessageByMsgIDX(MEMIDX);
	}

	@Override
	public void insertMessage(Message message) throws Exception {
		messageDAO.insertMessage(message);
	}

}

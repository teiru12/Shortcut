package sc.message;

import java.util.List;
import java.util.Map;

import sc.model.Message;

public interface MessageService {
	public List<Message> sendMessageListPagingById(int START, int END, String SENDID) throws Exception;
	
	public int countSendMessageList(String SENDID) throws Exception;
	
	public List<Message> getMessageListPagingById(int START, int END, String GETID) throws Exception;
	
	public int countGetMessageList(String GETID) throws Exception;
	
	public Message selectMessageByMsgIDX(int MSGIDX) throws Exception;
	
	public void updateMessageByMsgIDX(int MEMIDX) throws Exception;
	
	public void insertMessage(Message message) throws Exception;
}

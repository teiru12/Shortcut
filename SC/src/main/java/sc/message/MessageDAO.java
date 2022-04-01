package sc.message;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sc.model.Message;

@Repository("messageDAO")
public class MessageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 회원의 보낸 쪽지 리스트 페이징 */
	public List<Message> sendMessageListPagingById(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("message.sendMessageListPagingById", map);
	}
	
	/* 회원의 보낸 쪽지 카운트 */
	public int countSendMessageList(String SENDID) throws Exception {
		return sqlSessionTemplate.selectOne("message.countSendMessageList", SENDID);
	}
	
	/* 회원의 받은 쪽지 리스트 페이징 */
	public List<Message> getMessageListPagingById(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("message.getMessageListPagingById", map);
	}
	
	/* 회원의 받은 쪽지 카운트 */
	public int countGetMessageList(String GETID) throws Exception {
		return sqlSessionTemplate.selectOne("message.countSendMessageList", GETID);
	}
	
	/* 회원의 쪽지 정보 1개 선택 (SELECT) BY MSGIDX */
	public Message selectMessageByMsgIDX(int MSGIDX) throws Exception {
		return sqlSessionTemplate.selectOne("message.selectMessageByMsgIDX", MSGIDX);
	}
	
	/* 회원의 쪽지 정보 수정 (삭제) */
	public void updateMessageByMsgIDX(int MEMIDX) throws Exception {
		sqlSessionTemplate.update("message.updateMessageByMsgIDX", MEMIDX);
	}
	
	/* 쪽지 작성 */
	public void insertMessage(Message message) throws Exception {
		sqlSessionTemplate.insert("message.insertMessage", message);
	}
}

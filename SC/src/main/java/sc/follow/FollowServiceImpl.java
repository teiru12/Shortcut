package sc.follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Follow;

@Service("folloService")
public class FollowServiceImpl implements FollowService {

	@Resource(name="folloDAO")
	private FollowDAO followDAO;
	
	/*회원의 팔로우 리스트 페이징*/
	@Override
	public List<Follow> followListPaging(String ID, int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ID", ID);
		map.put("START", START);
		map.put("END", END);
		
		return followDAO.followListPaging(map);
	}
	
	/*회원의 팔로우 카운트*/
	@Override
	public int countFollow(String ID) throws Exception {
		return Integer.parseInt(String.valueOf(followDAO.countFollow(ID)));
	}
	
	/*내 아이디의 팔로우 아이디 검색*/
	@Override
	public String findFollowId(String ID, String FOLLOWID) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ID", ID);
		map.put("FOLLOWID", FOLLOWID);
		
		return followDAO.findFollowId(map);
	}
	
	/*회원의 팔로우 추가*/
	@Override
	public void insertFollow(Follow follow) throws Exception {
		followDAO.insertFollow(follow);
	}
	
	/*회원의 팔로우 삭제*/
	@Override
	public void deleteFollow(int FOLLOWIDX) throws Exception {
		followDAO.deleteFollow(FOLLOWIDX);
	}
}

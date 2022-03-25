package sc.follow;

import java.util.List;
import java.util.Map;

import sc.model.Follow;

public interface FollowService {

	/*회원의 팔로우 리스트 페이징*/
	public List<Follow> followListPaging(String ID, int START, int END) throws Exception;
	
	/*회원의 팔로우 카운트*/
	public int countFollow(String ID) throws Exception;
	
	/*내 아이디의 팔로우 아이디 검색*/
	public String findFollowId(String ID, String FOLLOWID) throws Exception;
	
	/*회원의 팔로우 추가*/
	public void insertFollow(Follow follow) throws Exception;
	
	/*회원의 팔로우 삭제*/
	public void deleteFollow(int FOLLOWIDX) throws Exception;
}

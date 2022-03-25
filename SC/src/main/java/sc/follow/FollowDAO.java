package sc.follow;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Follow;

@Repository("followDAO")
public class FollowDAO {

	@Resource(name="sqlSessionTemplate")
	   private SqlSessionTemplate sqlSessionTemplate;
	
	/*회원의 팔로우 리스트 페이징*/
	public List<Follow> followListPaging(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("follow.followListPaging", map);
	}
	
	/*회원의 팔로우 카운트*/
	public int countFollow(String ID) throws Exception {
		return sqlSessionTemplate.selectOne("follow.countFollow", ID);
	}
	
	/*내 아이디의 팔로우 아이디 검색*/
	public String findFollowId(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("follow.findFollowId", map);
	}
	
	/*회원의 팔로우 추가*/
	public void insertFollow(Follow follow) throws Exception {
		sqlSessionTemplate.insert("follow.insertFollow", follow);
	}
	
	/*회원의 팔로우 삭제*/
	public void deleteFollow(int FOLLOWIDX) throws Exception {
		sqlSessionTemplate.delete("follow.deleteFollow", FOLLOWIDX);
	}
}

package sc.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("adminDAO")
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//자유게시판 비회원 리스트 갯수 가져오기 
	public int guestFreeCount() throws Exception{
		
		return sqlSessionTemplate.selectOne("admin.guestFreeCount");
	}

	//자유게시판 회원 리스트 갯수 가져오기 
	public int memberFreeCount() throws Exception{
		
		return sqlSessionTemplate.selectOne("admin.memberFreeCount");
	}
	
	//정보교류게시판 비회원 리스트 갯수 가져오기
	public int guestInfoCount() throws Exception{
		
		return sqlSessionTemplate.selectOne("admin.guestInfoCount");
	}
	
	//정보교류게시판 회원 리스트 갯수 가져오기
	public int memberInfoCount() throws Exception{
		
		return sqlSessionTemplate.selectOne("admin.memberInfoCount");
	}
}

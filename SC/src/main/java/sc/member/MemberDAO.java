package sc.member;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sc.model.Member;

@Repository("memberDAO")
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 회원 정보 입력 */
	public void insertMember(Member member) throws Exception {
		sqlSessionTemplate.insert("member.insertMember", member);
	}
	
	/* 회원 정보 수정 */
	public void updateMember(Member member) throws Exception {
		sqlSessionTemplate.update("member.updateMember", member);
	}
	
	/* 회원 상태 수정 (활동, 정지, 삭제) 추가 - 관리자용/탈퇴 - 일반회원 */
	public void updateMemberStatus(Member member) throws Exception {
		sqlSessionTemplate.update("member.updateMemberStatus", member);
	}
	
	/* 회원 정보 1개 선택 (SELECT) BY MEMIDX */
	public Member selectMemberIDX(int MEMIDX) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberIDX", MEMIDX);
	}
	
	/* 회원 정보 1개 선택 (SELECT) BY ID */
	public Member selectMemberId(String ID) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberId", ID);
	}
	
	/* 회원 정보 1개 선택 (SELECT) BY 이름, 이메일 : 아이디 찾기 */
	public Member selectMemberFindId(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberFindId", map);
	}
	
	/* 회원 정보 1개 선택 (SELECT) BY 이름, 이메일, ID : 비밀번호 찾기 */
	public Member selectMemberFindPw(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectOne("member.selectMemberFindPw", map);
	}
	
	/* 회원 정보 리스트 페이징 */
	public List<Map<String, Object>> memberListPaging(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("member.memberListPaging", map);
	}
	
	/* 회원 정보 카운트 */
	public int countMember() throws Exception {
		return sqlSessionTemplate.selectOne("member.countMember");
	}
	
	/* 회원 정보 검색 페이징 */
	public List<Map<String, Object>> memberListSearchPaging(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("member.memberListSearchPaging", map);
	}
	
	/* 회원 정보 검색 페이징 */
	public int countMemberSearch(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectOne("member.countMemberSearch", map);
	}
}

package sc.member;

import java.util.List;
import java.util.Map;

import sc.model.Member;

public interface MemberService {
	
	public void insertMember(Member member) throws Exception;
	
	public void updateMember(Member member) throws Exception;
	
	public void updateMemberStatus(Member member) throws Exception;
	
	public Member selectMemberIDX(int MEMIDX) throws Exception;
	
	public Member selectMemberId(String ID) throws Exception;
	
	public String selectMemberFindId(Map<String, Object> map) throws Exception;
	
	public Member selectMemberFindPw(Map<String, Object> map) throws Exception;
		
	public List<Map<String, Object>> memberListPaging(int START, int END) throws Exception;
	
	public int countMember() throws Exception;

	public List<Map<String, Object>> memberListSearchPaging(int START, int END, int MINEXP, int MAXEXP, String KEYWORD, String STATUS) throws Exception;
	
	public int countMemberSearch(Map<String, Object> map) throws Exception;
	
	public void testjoin(String Email) throws Exception;
}

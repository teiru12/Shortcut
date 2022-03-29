package sc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Resource(name = "memberDAO")
	private MemberDAO memberDAO;

	@Override
	public void insertMember(Member member) throws Exception {
		memberDAO.insertMember(member);
	}

	@Override
	public void updateMember(Member member) throws Exception {
		memberDAO.updateMember(member);
	}

	@Override
	public void updateMemberStatus(Member member) throws Exception {
		memberDAO.updateMemberStatus(member);
	}

	@Override
	public Member selectMemberIDX(int MEMIDX) throws Exception {
		return memberDAO.selectMemberIDX(MEMIDX);
	}

	@Override
	public Member selectMemberId(String ID) throws Exception {
		return memberDAO.selectMemberId(ID);
	}

	@Override
	public String selectMemberFindId(Map<String, Object> map) throws Exception {
		return memberDAO.selectMemberFindId(map);
	}

	@Override
	public Member selectMemberFindPw(Map<String, Object> map) throws Exception {
		return memberDAO.selectMemberFindPw(map);
	}

	@Override
	public List<Map<String, Object>> memberListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return memberDAO.memberListPaging(map);
	}

	@Override
	public int countMember() throws Exception {
		return memberDAO.countMember();
	}

	@Override
	public List<Map<String, Object>> memberListSearchPaging(int START, int END, int MINEXP, int MAXEXP, String KEYWORD) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		map.put("MINEXP", MINEXP);
		map.put("MAXEXP", MAXEXP);
		map.put("KEYWORD", KEYWORD);
		
		return memberDAO.memberListSearchPaging(map);
	}

	@Override
	public int countMemberSearch(Map<String, Object> map) throws Exception {
		return memberDAO.countMemberSearch(map);
	}
	
}

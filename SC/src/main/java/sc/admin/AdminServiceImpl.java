package sc.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	public int guestFreeCount() throws Exception{
		
		return adminDAO.guestFreeCount();
	}
	
	public int memberFreeCount() throws Exception{
		
		return adminDAO.memberFreeCount();
	}
	
	public int guestInfoCount() throws Exception{
		
		return adminDAO.guestInfoCount();
	}
	
	public int memberInfoCount() throws Exception{
		
		return adminDAO.memberInfoCount();
	}

}

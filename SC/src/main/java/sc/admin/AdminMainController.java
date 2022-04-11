package sc.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminMainController {

	@Resource(name = "adminService")
	private AdminService adminService;

	@RequestMapping(value = "adminMainPage.cut")
	public String adminMainPage(Model model) throws Exception {

		int guestFreeCount;
		int memberFreeCount;
		int guestInfoCount;
		int memberInfoCount;

		guestFreeCount = adminService.guestFreeCount();
		memberFreeCount = adminService.memberFreeCount();
		guestInfoCount = adminService.guestInfoCount();
		memberInfoCount = adminService.memberInfoCount();
		
		model.addAttribute("guestFreeCount",guestFreeCount);
		model.addAttribute("memberFreeCount",memberFreeCount);
		model.addAttribute("guestInfoCount",guestInfoCount);
		model.addAttribute("memberInfoCount",memberInfoCount);
		
		return "adminMainPage";
	}

}

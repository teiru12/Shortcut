package sc.free;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.model.Free;
import sc.model.Notice;
import sc.notice.NoticeService;

@Controller
public class FreeController {
	
	@Resource(name = "freeService")
	private FreeService freeService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	
	@RequestMapping(value = "/freeList.cut")
	public String freeList(Model model) throws Exception {
		
		int START = 1;
		int END = 10;
		int freeCount;
		
		List<Free> freeList = new ArrayList<Free>();
		List<Notice> noticeTopList = new ArrayList<Notice>();
		
		freeList = freeService.freeListPaging(START, END);
		freeCount = freeService.countFreeList();
		noticeTopList = noticeService.noticeListPaging(1, 3);
		
		model.addAttribute("freeList", freeList);
		
		model.addAttribute("noticeTopList", noticeTopList);
		
		
	return "freeList";
	}
	
	
	@RequestMapping(value = "freeDetail.cut")
	public String freeDetail(Model model) throws Exception {
		
		
		return "freeDetail";
	}
}

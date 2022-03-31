package sc.shortcut;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.notice.NoticeService;

@Controller
public class ShortController {
	
	@Resource(name = "shortService")
	private ShortService shortService;
	
	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@RequestMapping(value = "/shortcutList.cut")
	public String shortcutList(Model model) throws Exception {
		
		return "shortcutList";
	}

}

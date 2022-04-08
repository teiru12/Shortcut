package sc.shortcut;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminShortController {

	@Resource(name="shortService")
	private ShortService shortService;
	
	@RequestMapping(value = "adminShortWriteForm.cut")
	public String adminShortWriteForm(Model model) throws Exception{
		
		return "adminShortWriteForm";
	}
	
	@RequestMapping(value = "adminShortWrite.cut")
	@ResponseBody
	public Map<String,String> freeWrite(String TITLE, String CONTENT, String STYPE, HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		sc.model.Short shortItem = new sc.model.Short();
		  
		shortItem.setTITLE(TITLE);
		shortItem.setCONTENT(CONTENT);
		shortItem.setSTYPE(STYPE);
		
		shortService.insertShortList(shortItem);
		
		return msg;
	}
	
	@RequestMapping(value = "adminShortModifyForm.cut")
	public String freeModifyForm(HttpServletRequest request, Model model) throws Exception{
		int IDX = Integer.parseInt(request.getParameter("SHORTIDX"));
		
		sc.model.Short shortItem = shortService.selectShortIDX(IDX);
		model.addAttribute("shortItem", shortItem);
		
		return "adminShortModifyForm";
	}
	
	@RequestMapping(value = "adminShortModify.cut")
	@ResponseBody
	public Map<String, String> adminShortModify(String TITLE, String CONTENT, String STYPE, int IDX,
			HttpServletRequest request) throws Exception{
		Map<String, String> msg = new HashMap<String, String>();
		
		sc.model.Short shortItem = new sc.model.Short();
		
		shortItem.setSHORTIDX(IDX);
		shortItem.setTITLE(TITLE);
		shortItem.setSTYPE(STYPE);
		shortItem.setCONTENT(CONTENT);
		
		shortService.updateShortList(shortItem);
		
		return msg;
	}
}
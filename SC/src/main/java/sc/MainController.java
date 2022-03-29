package sc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.free.FreeService;
import sc.goodbad.GoodbadService;
import sc.info.InfoService;
import sc.model.Free;
import sc.model.Info;
import sc.shortcut.ShortService;


@Controller
public class MainController {
	
	@Resource(name="shortService")
	private ShortService shortService;
	@Resource(name="freeService")
	private FreeService freeService;
	@Resource(name="infoService")
	private InfoService infoService;
	@Resource(name="goodbadService")
	private GoodbadService goodbadService;


	@RequestMapping(value = "/main.cut")
	public String main(Model model) throws Exception {

		int START = 1;
		int END = 10;
		
		
		/* 단축키게시판 최신글 */
		List<Short> shortList = new ArrayList<Short>();
		
		shortList = shortService.shortListPaging(START, END);
		
		model.addAttribute("shortList", shortList);
		
		/* 단축키게시판 10추글 */
		
		List<Map<String, Object>> goodShortList = goodbadService.selectGoodShortByDate(null);
		
		model.addAttribute("goodShortList", goodShortList);
		
		/* 자유게시판 최신글 */
		
		int freeReadCount = 0;
		
		List<Free> freeList = new ArrayList<Free>();
		
		freeList = freeService.freeListPaging(START, END);
		freeService.updateFreeReadcount(freeReadCount);
		

		model.addAttribute("freeList", freeList);
		model.addAttribute("readCount", freeReadCount);
		
		/* 자유게시판 10추글 */
		
		List<Map<String, Object>> goodFreeList = goodbadService.selectGoodFreeByDate(null);
		
		model.addAttribute("goodFreeList", goodFreeList);
		
		
		/* 정보교류게시판 최신글 */
		
		int infoReadCount = 0;
		
		List<Info> infoList = new ArrayList<Info>();
		
		infoList = infoService.infoListPaging(START, END);
		infoService.updateInfoReadcount(infoReadCount);
		

		model.addAttribute("infoList", infoList);
		model.addAttribute("infoReadCount", infoReadCount);
		
		/* 자유게시판 10추글 */
		
		List<Map<String, Object>> goodInfoList = goodbadService.selectGoodInfoByDate(null);
		
		model.addAttribute("goodInfoList", goodInfoList);
		
		return "main_layout";
	}
}
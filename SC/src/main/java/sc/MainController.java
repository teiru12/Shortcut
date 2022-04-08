package sc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sc.free.FreeService;
import sc.goodbad.GoodbadService;
import sc.info.InfoService;
import sc.model.Free;
import sc.model.Info;
import sc.model.Visit;
import sc.shortcut.ShortService;
import sc.visit.VisitService;


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
	@Resource(name="visitService")
	private VisitService visitService;


	@RequestMapping(value = "/main.cut")
	public String main(HttpServletRequest request,Model model) throws Exception {

		int START = 1;
		int END = 10;
		/* 방문자 수 불러오기 */
		int totalCount = visitService.totalVisitCount();
		int todayCount = visitService.todayVisitCount();
		
		request.getSession().setAttribute("totalCount", totalCount);
		request.getSession().setAttribute("todayCount", todayCount);
		
		/* 단축키게시판 최신글 */

		
		List<Short> shortList = new ArrayList<Short>();
		
		shortList = shortService.shortListPaging(START, END);
		
		model.addAttribute("shortList", shortList);
		
		/* 단축키게시판 10추글 */
		
		
		List<Map<String, Object>> goodShortList = goodbadService.selectGoodShortByDate(null);
		
		
		model.addAttribute("goodShortList", goodShortList);
		
		/* 자유게시판 최신글 */
		
		
		List<Free> freeList = new ArrayList<Free>();
		
		freeList = freeService.freeListPaging(START, END);
		

		model.addAttribute("freeList", freeList);
		
		/* 자유게시판 10추글 */
		
		
		List<Map<String, Object>> goodFreeList = goodbadService.selectGoodFreeByDate(null);
		
		model.addAttribute("goodFreeList", goodFreeList);
		
		
		/* 정보교류게시판 최신글 */
		
		
		List<Info> infoList = new ArrayList<Info>();
		
		infoList = infoService.infoListPaging(START, END);
		

		model.addAttribute("infoList", infoList);
		
		/* 자유게시판 10추글 */
		
		List<Map<String, Object>> goodInfoList = goodbadService.selectGoodInfoByDate(null);
		
		model.addAttribute("goodInfoList", goodInfoList);
		
		return "main_layout";
	}
}
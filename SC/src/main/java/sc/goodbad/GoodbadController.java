package sc.goodbad;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sc.model.Goodbad;

@Controller
public class GoodbadController {

	@Resource(name="goodbadService")
	private GoodbadService goodbadService;
	
	@ResponseBody
	@RequestMapping("/selectGoodbad.cut")
	public Map<String, String> selectGoodbad(String ID, String TYPE, int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		
		Goodbad goodbad = goodbadService.selectGoodbad(ID, TYPE, IDX);
		
		if(goodbad == null) { // 좋아요 싫어요를 하지 않음
			msg.put("used", "false");			
		} else { // 좋아요 또는 싫어요를 함
			msg.put("used", "true");
			
			if(goodbad.getGOOD().equals("Y")) {
				msg.put("message", "이미 좋아요를 하였습니다.");
			} else if(goodbad.getBAD().equals("Y")) {
				msg.put("message", "이미 싫어요를 하였습니다.");
			}			
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/insertGood.cut")
	public Map<String, String> insertGood(String ID, String TYPE, int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
	
		Goodbad newGood = new Goodbad();
		newGood.setID(ID);
		newGood.setTYPE(TYPE);
		newGood.setIDX(IDX);
		
		// ID 사용자가 TYPE 게시판의 IDX 게시글에 좋아요를 누름 기록
		goodbadService.insertGood(newGood);
		
		return msg;
	}	
	
	@ResponseBody
	@RequestMapping("/insertBad.cut")
	public Map<String, String> insertBad(String ID, String TYPE, int IDX) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
	
		Goodbad newBad = new Goodbad();
		newBad.setID(ID);
		newBad.setTYPE(TYPE);
		newBad.setIDX(IDX);
		
		// ID 사용자가 TYPE 게시판의 IDX 게시글에 싫어요를 누름 기록
		goodbadService.insertBad(newBad);
		
		return msg;
	}
}
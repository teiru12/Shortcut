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
	public Map<String, Object> selectGoodbad(String ID, String TYPE, int IDX) throws Exception {
		Map<String, Object> msg = new HashMap<String, Object>();
		
		Goodbad goodbad = goodbadService.selectGoodbad(ID, TYPE, IDX);
		
		if(goodbad == null) { // 좋아요 싫어요를 하지 않음
			msg.put("uesd", false);			
		} else { // 좋아요 또는 싫어요를 함
			msg.put("uesd", true);
			
			if(goodbad.getGOOD().equals("Y")) {
				msg.put("message", "이미 좋아요를 하였습니다.");
			} else if(goodbad.getBAD().equals("Y")) {
				msg.put("message", "이미 싫어요를 하였습니다.");
			}			
		}
		return msg;
	}
	
}
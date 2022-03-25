package sc.alarm;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("alarmService")
public class AlarmServiceImpl implements AlarmService{

	@Resource(name = "alarmDAO")
	private AlarmDAO alarmDAO;
	
	@Override
	public List<Map<String, Object>> alarmList(String id) throws Exception {
		return alarmDAO.alarmList(id);
	}

	@Override
	public void deleteAlarm(int ALARMIDX) throws Exception {
		alarmDAO.deleteAlarm(ALARMIDX);
	}

}

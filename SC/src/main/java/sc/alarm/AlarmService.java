package sc.alarm;

import java.util.List;
import java.util.Map;

public interface AlarmService {
	
	public List<Map<String, Object>> alarmList(String id) throws Exception;
	
	public void deleteAlarm(int ALARMIDX) throws Exception;
}

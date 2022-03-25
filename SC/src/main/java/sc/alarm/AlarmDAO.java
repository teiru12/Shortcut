package sc.alarm;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("alarmDAO")
public class AlarmDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* 알람 리스트 */
	public List<Map<String, Object>> alarmList(String id) throws Exception {
		return sqlSessionTemplate.selectList("alarm.alarmList", id);
	}
	
	/* 알람 삭제 */
	public void deleteAlarm(int ALARMIDX) throws Exception {
		sqlSessionTemplate.delete("alarm.deleteAlarm", ALARMIDX);
	}

}

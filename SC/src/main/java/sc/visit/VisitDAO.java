package sc.visit;

import java.sql.Date;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Visit;

@Repository("vistDAO")
public class VisitDAO {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//오늘 방문 기록 있는지 선택
	public Visit selectVisitTodayByID(String ID , Date VISITDATE)throws Exception {
		return sqlSessionTemplate.selectOne("visit.selectVisitTodayByID",ID);
	}
	
	//방문 기록 입력
	public void insertVisit(Visit visit)throws Exception{
		sqlSessionTemplate.insert("visit.insertVisit",visit);
	}
	
	//전체 방문자 수 카운트
	public int totalVisitCount() throws Exception{
		return sqlSessionTemplate.selectOne("visit.totalVisitCount");
	}
	
	//오늘 방문자 수 카운트
	public int todayVisitCount() throws Exception{
		return sqlSessionTemplate.selectOne("visit.totalVisitCount");
	}
}

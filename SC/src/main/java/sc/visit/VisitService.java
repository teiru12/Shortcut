package sc.visit;

import java.sql.Date;

import sc.model.Visit;

public interface VisitService {

	public Visit selectVisitTodayByID(String ID , Date VISITDATE)throws Exception;
	
	public void insertVisit(Visit visit)throws Exception;
	
	public int totalVisitCount() throws Exception;
	
	public int todayVisitCount() throws Exception;
	
	
}

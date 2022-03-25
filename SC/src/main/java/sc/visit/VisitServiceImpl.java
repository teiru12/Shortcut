package sc.visit;

import java.sql.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Visit;

@Service("visetService")
public class VisitServiceImpl implements VisitService{

	@Resource(name="visitDAO")
	private VisitDAO visitDAO;

	@Override
	public Visit selectVisitTodayByID(String ID, Date VISITDATE) throws Exception {
	
		return visitDAO.selectVisitTodayByID(ID, VISITDATE);
	}

	@Override
	public void insertVisit(Visit visit) throws Exception {
		visitDAO.insertVisit(visit);		
	}

	@Override
	public int totalVisitCount() throws Exception {
		return Integer.parseInt(String.valueOf(visitDAO.totalVisitCount()));
	}

	@Override
	public int todayVisitCount() throws Exception {
		return Integer.parseInt(String.valueOf(visitDAO.todayVisitCount()));
	}
}

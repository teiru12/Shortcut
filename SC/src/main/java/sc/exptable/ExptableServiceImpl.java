package sc.exptable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sc.model.Exptable;

@Service("ExptableService")
public class ExptableServiceImpl implements ExptableService {

	@Resource(name = "ExptableDAO")
	private ExptableDAO exptableDAO;
	
	@Override
	public void insertExp(Exptable exptable) throws Exception {
		exptableDAO.insertExp(exptable);
		
	}
	
}

package sc.exptable;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sc.model.Exptable;

@Repository("exptableDAO")
public class ExptableDAO {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//경험치 생성/수정
	public void insertExp(Exptable exptable) throws Exception {
		sqlSessionTemplate.insert("exptable.insertExp", exptable);
	}
}

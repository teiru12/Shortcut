package sc.info;

import java.util.List;

import sc.model.Info;
import sc.model.InfoCom;

public interface InfoService {

	/* 정보교류 게시판 리스트 페이징 */
	public List<Info> infoListPaging(int START, int END) throws Exception;
	
	/* 정보교류 게시판 카운트 */
	public int countInfoList() throws Exception;
	
	/* 정보교류 게시판 검색 리스트 페이징 */
	public List<Info> infoListSearchPaging(int START, int END, String KEYWORD, String ORDER) throws Exception;
	
	/* 정보교류 게시판 검색 카운트 */
	public int countInfoListSearch(String KEYWORD) throws Exception;
	
	/* 정보교류 게시판 입력 */
	public void insertInfoList(Info info) throws Exception;
	
	/* 정보교류 게시판 수정 */
	public void updateInfoList(Info info) throws Exception;
	
	/* 정보교류 게시판 수정(삭제) */
	public void updateInfoListDEL(int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 선택 */
	public Info selectInfoIDX(int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 조회수 */
	public void updateInfoReadcount(int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 좋아요 */
	public void updateInfoGood(int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 싫어요 */
	public void updateInfoBad(int INFOIDX) throws Exception;

	/* 정보교류 게시판 댓글 리스트 페이징 BY 게시글번호 */
	public List<InfoCom> infoListComPagingByInfoIDX(int START, int END, int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 댓글 카운트 BY 게시글번호 */
	public int countInfoComByInfoIDX(int INFOIDX) throws Exception;
	
	/* 정보교류 게시판 댓글 입력 BY 게시글번호 */
	public void insertInfoListComByInfoIDX(InfoCom infoCom) throws Exception;
	
	/* 정보교류 게시판 대댓글이 아닌 댓글 입력 BY 게시글번호*/
	public void insertInfoListFirstComByInfoIDX(InfoCom infoCom) throws Exception;
	
	/* 다음 RESTEP값을 찾음*/
	public int nextReStep(int RETYPE, int RELEVEL, int PCOMIDX, int ARTICLEIDX) throws Exception;

	/* 정보교류 게시판 댓글 하나의 정보 BY INFOCOMIDX*/
	public InfoCom selectInfoCom(int INFOCOMIDX) throws Exception;
	
	/* RETYPE이 같은 입력받은 RESTEP값보다 같거나 큰 모든 댓글들의 RESTEP을 1씩 증가*/
	public void increaseReStepEqAndGreater(int RESTEP, int RETYPE, int ARTICLEIDX) throws Exception;
	
	/* 정보교류 게시판 댓글 수정 BY 게시글번호*/
	public void updateInfoListComByInfoIDX(InfoCom infoCom) throws Exception;
	
	/* 정보교류 게시판 댓글 수정 (삭제) BY 게시글번호*/
	public void updateInfoListComDelByInfoIDX(int INFOCOMIDX) throws Exception;

}
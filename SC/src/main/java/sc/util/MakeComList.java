package sc.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MakeComList {
	
	/* 단축키, 자유, 정보교류 게시판 댓글 html 작성시 사용 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String make(List list, HttpServletRequest request, String TYPE, int ARTICLEIDX, int currentPage) {
		
		int listSize = list.size();
		String comIDX = "";
		String comDate = "";
		
		if(TYPE.equals("FRE")) {
			comIDX = "FREECOMIDX";
			comDate = "FREECOMDATE";
		} else if(TYPE.equals("SHO")) {
			comIDX = "SHORTCOMIDX";
			comDate = "SHORTCOMDATE";
		} else if(TYPE.equals("INF")) {
			comIDX = "INFOCOMIDX";
			comDate = "INFOCOMDATE";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<tbody id=\"comDiv\">");
		for(int i=0;i<listSize;i++) {
			sb.append("<tr id=\"comItemBody\">");
			sb.append("<td class=\"text-bold-500\" id=\"comItemDiv\">");
			sb.append("<div style=\"text-align:right;\">");
			
			sb.append("<a href=\"javascript:openComWriteForm(" + i + ")\" style=\"font-size:small\">답댓글</a>&nbsp;");
			
			if((request.getSession().getAttribute("id") == null) &&
					(((Map<String, Object>)list.get(i)).get("PASSWORD") != null)
					) { // 비회원 상태 & 해당 댓글의 비밀번호가 존재할 때
				sb.append("<a href=\"javascript:comModify('비회원','" + TYPE + "', "
					+ ((Map<String, Object>)list.get(i)).get(comIDX)
					+ ", " + i + ",'MOD')\" style=\"font-size:small\">수정</a>&nbsp;");
				sb.append("<a href=\"javascript:comModify('비회원','" + TYPE + "', "
					+ ((Map<String, Object>)list.get(i)).get(comIDX)
					+ ", " + i + ",'DEL')\" style=\"font-size:small\">삭제</a>&nbsp;");
			} else if((request.getSession().getAttribute("id") != null) &&
					(((String)(request.getSession().getAttribute("id"))).equals(((Map<String, Object>)list.get(i)).get("ID")))
					){ // 댓글의 작성자가 로그인 한 사람일 때 
				sb.append("<a href=\"javascript:comModify('" + (String)(request.getSession().getAttribute("id"))
					+ "','" + TYPE + "', "
					+ ((Map<String, Object>)list.get(i)).get(comIDX)
					+ ", " + i + ",'MOD')\" style=\"font-size:small\">수정</a>&nbsp;");
				sb.append("<a href=\"javascript:comModify('" + (String)(request.getSession().getAttribute("id"))
					+ "','" + TYPE + "', "
					+ ((Map<String, Object>)list.get(i)).get(comIDX)
					+ ", " + i + ",'DEL')\" style=\"font-size:small\">삭제</a>&nbsp;");
			}
			sb.append("</div>");
			sb.append("<div>");
			
			for(int j=0;j<=Integer.parseInt(String.valueOf(((Map<String, Object>)list.get(i)).get("RELEVEL")));j++) {
				sb.append("&nbsp;");
			}
			if(Integer.parseInt(String.valueOf(((Map<String, Object>)list.get(i)).get("RELEVEL")))>0) {
				sb.append("∟");
			}
			sb.append("<span class=\"text-subtitle text-muted\" id=\"comID" + i + "\">");
			if(((String)((Map<String, Object>)list.get(i)).get("ISDEL")).equals("N")) {
				sb.append(((Map<String, Object>)list.get(i)).get("ID"));
			} else {
				sb.append("삭제");
			}		
			sb.append("</span>");			
			sb.append("<p class=\"text-subtitle text-muted\" style=\"font-size:x-small\">"
				+ ((Map<String, Object>)list.get(i)).get(comDate) + "</p>");
			sb.append("<p class=\"card-text\" id=\"comCONTENT" + i + "\">");
			if(((String)((Map<String, Object>)list.get(i)).get("ISDEL")).equals("N")) {
				sb.append(((Map<String, Object>)list.get(i)).get("CONTENT"));
			} else {
				sb.append("삭제된 댓글입니다.");
			}			
			sb.append("</p></div>");
			
			sb.append("<div id=\"reForm" + i + "\" class=\"col-12 mx-auto\" style=\"display:none\">");
			sb.append("<div class=\"input-group mb-3\">");
			sb.append("<textarea class=\"form-control\" id=\"reply"
				+ ((Map<String, Object>)list.get(i)).get(comIDX) 
				+ "\" placeholder=\"댓글을 입력해주세요\"></textarea>");
			if(request.getSession().getAttribute("id") == null) {
				sb.append("<button class=\"btn btn-outline-secondary\" type=\"button\" ");
				sb.append(" id=\"button-addon2\" ");
				sb.append(" onClick=\"comWrite('비회원','" + TYPE + "', '" + ARTICLEIDX + "',");
				if(Integer.parseInt(String.valueOf(((Map<String, Object>)list.get(i)).get("RETYPE")))==0) {
					sb.append(((Map<String, Object>)list.get(i)).get(comIDX));
				} else {
					sb.append(((Map<String, Object>)list.get(i)).get("RETYPE"));
				}
				sb.append(", " + ((Map<String, Object>)list.get(i)).get(comIDX));
				sb.append(", " + currentPage + ")\">댓글입력</button>");
			} else {
				sb.append("<button class=\"btn btn-outline-secondary\" type=\"button\" ");
				sb.append(" id=\"button-addon2\" ");
				sb.append(" onClick=\"comWrite('" + request.getSession().getAttribute("id")
					+ "','" + TYPE + "', '" + ARTICLEIDX + "',");
				if(Integer.parseInt(String.valueOf(((Map<String, Object>)list.get(i)).get("RETYPE")))==0) {
					sb.append(((Map<String, Object>)list.get(i)).get(comIDX));
				} else {
					sb.append(((Map<String, Object>)list.get(i)).get("RETYPE"));
				}
				sb.append(", " + ((Map<String, Object>)list.get(i)).get(comIDX));
				sb.append(", " + currentPage + ")\">댓글입력</button>");
			}
			sb.append("</div></div></td></tr>");

		}
		sb.append("</tbody>");
		String newComList = sb.toString();
		
		return newComList;
	}
}
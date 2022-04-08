package sc.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfDownView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document doc, PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<sc.model.Short> list = (List<sc.model.Short>) model.get("list");
		
		// 테이블을 생성
		// 2열 list.size()+1 행으로 생성
		Table table = new Table(2, list.size()+1);
		// 여백 설정
		table.setPadding(5);
		
		// 기본 폰트 설정 - 폰트에 따라 한글 출력 여부가 결정
		BaseFont bfKorea = BaseFont.createFont(request.getSession().getServletContext().getRealPath("assets/font/malgun.ttf")
			, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bfKorea);
		
		Cell cell1 = new Cell(new Paragraph("단축키", font));
		cell1.setHeader(true);
		table.addCell(cell1);
		
		Cell cell2 = new Cell(new Paragraph("설명", font));
		cell2.setHeader(true);
		table.addCell(cell2);
		
		table.endHeaders();
		
		// 데이터를 테이블의 셀에 출력
		for(int i=0;i<list.size();i++) {
			@SuppressWarnings("unchecked")
			Cell data1 = new Cell(new Paragraph(((String)((Map<String, Object>)list.get(i)).get("TITLE")), font));
			@SuppressWarnings("unchecked")
			Cell data2 = new Cell(new Paragraph(((String)((Map<String, Object>)list.get(i)).get("CONTENT")), font));
			table.addCell(data1);
			table.addCell(data2);
		}
		
		// 문서에 테이블 추가
		doc.add(table);

	}
}
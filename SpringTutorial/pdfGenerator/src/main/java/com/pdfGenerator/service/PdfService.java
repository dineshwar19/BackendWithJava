package com.pdfGenerator.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.pdfGenerator.model.NewModel;
import com.pdfGenerator.model.PdfModel;
import com.pdfGenerator.repository.PdfRepo;

import jakarta.transaction.Transactional;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("pdfGenerator")
public class PdfService {

    @Autowired
    private PdfRepo pdfRepo;
    
    @Value("${pdfDir}")
	private String pdfDir;
	
	@Value("${reportFileName}")
	private String reportFileName;
	
	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;
	
	@Value("${localDateFormat}")
	private String localDateFormat;
	
	@Value("${table_noOfColumns}")
	private int noOfColumns;
	
	@Value("${table.columnNames}")
	private List<String> columnNames;
	Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2024, 3, 1, 0, 0)); 
    Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2024, 3, 10, 0, 0)); 
    public void generatePdf() {
//        List<PdfModel> pdfDataList = pdfRepo.getGeneratedList(startDate,endDate);
        Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(getPdfNameWithDate()));
            doc.open();
            createTable(doc, noOfColumns);
            doc.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getPdfNameWithDate() {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MMMM_yyyy"));
		return "F:/Intership/"+"DataPdf"+"-"+localDateString+".pdf";
	}
    private void createTable(Document document, int noOfColumns) throws DocumentException {
		Paragraph paragraph = new Paragraph();
//		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		
		for(int i=0; i<noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}

		table.setHeaderRows(1);
		getDbData(table);
		document.add(table);
	}
//    
//    private static void leaveEmptyLine(Paragraph paragraph, int number) {
//		for (int i = 0; i < number; i++) {
//			paragraph.add(new Paragraph(" "));
//		}
//	}
    	@Transactional
    	private void getDbData(PdfPTable table) {
		List<PdfModel> list = pdfRepo.getGeneratedList(startDate, endDate);
		System.out.println(list);
		for (PdfModel data : list) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);			
			table.addCell(data.getDates().toString());
			table.addCell(data.getUnit().toString());
		}		
	}	
}

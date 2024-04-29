package com.pdfGenerator.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.BufferedInputStream;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pdfGenerator.model.PdfModel;
import com.pdfGenerator.repository.PdfRepo;
import jakarta.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// dynamic data from database and generate that data as a table format.
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
        Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(getPdfNameWithDate()));
            doc.open();
            createTable(doc, noOfColumns);
        	String htmlContent = parseThymeleafTemplate();
            generatePdfFromHtml(htmlContent);
            doc.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getPdfNameWithDate() {
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MMMM_yyyy"));
        return pdfDir + "DataPdf-" + localDateString + ".pdf";
    }
    private void createTable(Document document, int noOfColumns) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		
		for(int i=0; i<noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}
		table.setHeaderRows(1);
		getDbData(table);
		document.add(table);
	}
    
    private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
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
    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("chartData", dbData() );

        return templateEngine.process("index", context);
    }
    public void generatePdfFromHtml(String htmlContent) throws IOException {
    	FileOutputStream outputStream = new FileOutputStream(getPdfNameWithDate());

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
    
//    TO get data from a database as an entity and convert it into List<List<Object>>
    public List<List<Object>> dbData(){
		List<PdfModel> list = pdfRepo.getGeneratedList(startDate, endDate);
		List<List<Object>> res = new ArrayList<>();
		for(PdfModel data : list) {
			List<Object> inner = new ArrayList<>();
	        inner.add(data.getDates());
	        inner.add(data.getUnit());
	        res.add(inner);
		}
		System.out.println(res);
		return res;
	}
    
//    to download a HTML file
    public static void downloadHtml(String urlString, String destinationFile) throws IOException, URISyntaxException {
    	URL url = new URI(urlString).toURL();        
    	URLConnection connection = url.openConnection();
        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

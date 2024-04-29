package com.pdfGenerator.service;

import java.awt.Graphics2D;

import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.pdfGenerator.model.PdfModel;
import com.pdfGenerator.repository.PdfRepo;

// generate data as pie chart pdf.
@Component
public class ChartPdf {
	@Autowired
	private PdfRepo pdfRepo;
	Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(2024, 3, 1, 0, 0));
    Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(2024, 3, 10, 0, 0));
	public JFreeChart generatePieChart() {
		DefaultPieDataset<String> ds = new DefaultPieDataset<>();
		for(PdfModel ls : pdfRepo.getGeneratedList(startDate , endDate)) {
			ds.setValue(ls.getDates().toString(), ls.getUnit());
		}
		JFreeChart chart = ChartFactory.createPieChart(
                "sold unit with date", ds, true, true, false);
		return chart;
	}

	public void generatePdf(JFreeChart chart, int width, int height, String fileName) {
		PdfWriter writer = null;
	    Document document = new Document();
	    try {
	        writer = PdfWriter.getInstance(document, new FileOutputStream(
	                fileName));
	        document.open();
	        PdfContentByte contentByte = writer.getDirectContent();
	        PdfTemplate template = contentByte.createTemplate(width, height);
	        Graphics2D graphics2d = template.createGraphics(width, height,
	                new DefaultFontMapper());
	        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
	                height);
	        chart.draw(graphics2d, rectangle2d);
	        
	        graphics2d.dispose();
	        contentByte.addTemplate(template, 0, 0);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    document.close();		
	}
}

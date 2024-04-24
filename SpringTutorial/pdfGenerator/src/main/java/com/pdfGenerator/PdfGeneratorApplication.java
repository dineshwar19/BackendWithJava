package com.pdfGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.pdfGenerator.service.PdfService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pdfGenerator"})
public class PdfGeneratorApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(PdfGeneratorApplication.class, args);
		
		PdfService pDFGenerator = ac.getBean("pdfGenerator",PdfService.class);
		
		pDFGenerator.generatePdf();
		}

}

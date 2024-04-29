package com.pdfGenerator.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pdfGenerator.service.ChartPdf;
import com.pdfGenerator.service.PdfService;

@Controller
@RequestMapping("/")
public class PdfController {

	@Autowired
	private PdfService pdfService;
    
    @Autowired
    private ChartPdf chartPdf;
    
    @Value("${pdfDir}")
	private String pdfDir;
	
    @GetMapping("/get")
    public String getData(Model model) throws IOException, URISyntaxException {
        model.addAttribute("chartData", pdfService.dbData());
        chartPdf.generatePdf(chartPdf.generatePieChart(), 500, 400, "F://Intership/data-pdf.pdf");
        return "index";
    }     
}

package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ExcelHelper.ExcelHelper;
import com.example.model.ExcelModel;
import com.example.service.ExcelService;

@RestController

public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/getData")
	  public List<ExcelModel> getData() {
	      List<ExcelModel> db = excelService.getData();
	      return db;
	  }
	
	@PostMapping("/postData")
	public String postData(@RequestParam("file") MultipartFile file) {
		if(ExcelHelper.isExcelFormat(file)) {
			excelService.saveData(file);
			return "your data is inserted";
		}
		else {
			return "Upload an excel file";
		}
	}
}

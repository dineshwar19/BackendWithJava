package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ExcelHelper.ExcelHelper;
import com.example.model.ExcelModel;
import com.example.repository.ExcelRepository;

@Service
public class ExcelService {
	
	@Autowired
	private ExcelRepository excelRepo;
	
	public void saveData(MultipartFile file) {
		try {
			if (file == null || file.isEmpty()) {
				throw new IllegalArgumentException("File is null or empty.");
			}

			List<ExcelModel> sheets = ExcelHelper.excelTODB(file.getInputStream());
			excelRepo.saveAll(sheets);
		} catch (IOException e) {
			throw new RuntimeException("Fail to read Excel file: " + e.getMessage());
		} catch (Exception e) {
		     throw new RuntimeException("Fail to store Excel data: " + e.getMessage());
		}
	}
	public List<ExcelModel> getData(){
		return excelRepo.findAll();
	}
}

package com.pdfGenerator.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pdfGenerator.model.PdfModel;

public interface PdfRepo extends JpaRepository<PdfModel, Integer> {
	@Query(value = "SELECT dates , CASE WHEN unit IS NULL THEN 0 ELSE unit END AS unit FROM generate_series(:startDate ::::timestamp, :endDate ::::timestamp, '1 day' ::::interval) AS dates LEFT JOIN sales ON dates = saledate", nativeQuery = true)
	List<PdfModel> getGeneratedList(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}

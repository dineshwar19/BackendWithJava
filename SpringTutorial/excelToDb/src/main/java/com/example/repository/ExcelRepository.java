package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.ExcelModel;

public interface ExcelRepository extends JpaRepository<ExcelModel, Integer>{

}

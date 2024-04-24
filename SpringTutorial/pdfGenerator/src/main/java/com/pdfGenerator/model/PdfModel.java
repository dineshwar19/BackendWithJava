package com.pdfGenerator.model;

//import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class PdfModel {
	@Id
	private Integer unit;
	private Timestamp dates;
	
	
	public Timestamp getDates() {
		return dates;
	}
	public void setDates(Timestamp dates) {
		this.dates = dates;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
}

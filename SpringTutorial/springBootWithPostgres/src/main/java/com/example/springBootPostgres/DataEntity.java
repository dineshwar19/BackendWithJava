package com.example.springBootPostgres;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class DataEntity {
	@Id
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private Long phone;
	@Column(name = "year")
	private Integer year;
	@Transient
	private String newName; 
	@Transient
    private int newAge;  
	
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getNewAge() {
		return newAge;
	}
	public void setNewAge(int newAge) {
		this.newAge = newAge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Integer getYear() {
	    return year != null ? year : 0; // Assuming 0 is your default value
	}

	public void setYear(int year) {
		this.year = year;
	}
}	

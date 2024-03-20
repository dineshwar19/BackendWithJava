package com.example.springBootPostgres;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepositary extends CrudRepository<DataEntity, String>{
	public List<DataEntity> findByAge(int age);
	public List<DataEntity> findByNameAndPhone(String name,  Long phone);
}

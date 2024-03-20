package com.example.springBootPostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepositary extends JpaRepository<DataEntity , String>{

}

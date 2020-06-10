package com.airlife.wsAirLife.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Datatype;

@Repository
public interface DatatypeDao extends JpaRepository<Datatype, Integer>{
	public Datatype findById(int id);
	
	@Query(value = "SELECT seuil FROM datatype WHERE iddatatype = ?1", nativeQuery = true)
	public double findSeuilByIdDataType(int iddatatype);
}

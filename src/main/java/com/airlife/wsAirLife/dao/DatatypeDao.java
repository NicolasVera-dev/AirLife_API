package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Datatype;

@Repository
public interface DatatypeDao extends JpaRepository<Datatype, Integer>{
	public Datatype findById(int id);
}

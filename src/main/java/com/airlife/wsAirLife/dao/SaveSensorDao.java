package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.SaveSensor;

@Repository
public interface SaveSensorDao extends JpaRepository<SaveSensor, Integer>{

	public List<SaveSensor> findAll();
	
	@Query(value = "SELECT * FROM save_sensor WHERE id_sensor = ?1", nativeQuery = true)
	public SaveSensor findSensorById(int id);
}

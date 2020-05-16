package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Sensor;
import com.airlife.wsAirLife.model.SensorByUsers;
import com.airlife.wsAirLife.model.Users;

@Repository
public interface SensorDao extends JpaRepository<Sensor, Integer>{
	public List<Sensor> findAll();
	public Sensor findById(int id);
	
	@Query("SELECT new com.airlife.wsAirLife.model.SensorByUsers("
			+ " s.idsensor, s.namesensor, dt.libelle)\r\n" + 
			"	FROM Sensor s\r\n" + 
			"		JOIN ToCapture tc ON tc.sensor = s.idsensor \r\n" + 
			"		JOIN Datatype dt ON dt.iddatatype = tc.datatype\r\n" + 
			"		JOIN Users u ON u.iduser = s.user\r\n" +
			"	WHERE u.email = ?1" + 
			"   GROUP BY s.idsensor")
	public List<SensorByUsers> findSensorByUserEmail(String username);
	
	@Query("SELECT new com.airlife.wsAirLife.model.SensorByUsers("
			+ " s.idsensor, s.namesensor, dt.libelle)\r\n" + 
			"	FROM Sensor s\r\n" + 
			"		JOIN ToCapture tc ON tc.sensor = s.idsensor \r\n" + 
			"		JOIN Datatype dt ON dt.iddatatype = tc.datatype\r\n" + 
			"		JOIN Users u ON u.iduser = s.user\r\n" +
			"	WHERE u.login = ?1" + 
			"   GROUP BY s.idsensor")
	public List<SensorByUsers> findSensorByUserLogin(String username);
	
	@Query(value = "SELECT s.iduser FROM sensor s WHERE s.idsensor = ?1", nativeQuery = true)
	public int findUserBySensor(int idsensor);
	


}

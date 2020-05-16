package com.airlife.wsAirLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airlife.wsAirLife.model.Datas;
import com.airlife.wsAirLife.model.DatasByUsers;

@Repository
public interface DatasDao extends JpaRepository<Datas, Integer>{
	public List<Datas> findAll();
	public Datas findById(int id);
	
	@Query("SELECT new com.airlife.wsAirLife.model.DatasByUsers("
			+ " d.iddata, s.namesensor, d.datasensor, d.datetimedata, dt.libelle)\r\n" + 
			"	FROM Datas d\r\n" + 
			"		JOIN Sensor s ON d.sensor = s.idsensor \r\n" + 
			"		JOIN Users u ON s.user = u.iduser\r\n" +
			"       JOIN Datatype dt ON d.datatype = dt.iddatatype \r\n" + 
			"	WHERE u.email = ?1")
	public List<DatasByUsers> findDataByUserEmail(String username);
	
	@Query("SELECT new com.airlife.wsAirLife.model.DatasByUsers("
			+ " d.iddata, s.namesensor, d.datasensor, d.datetimedata, dt.libelle)\r\n" + 
			"	FROM Datas d\r\n" + 
			"		JOIN Sensor s ON d.sensor = s.idsensor \r\n" + 
			"		JOIN Users u ON s.user = u.iduser\r\n" + 
			"       JOIN Datatype dt ON d.datatype = dt.iddatatype \r\n" + 
			"	WHERE u.login = ?1")
	public List<DatasByUsers> findDataByUserLogin(String username);
	
}

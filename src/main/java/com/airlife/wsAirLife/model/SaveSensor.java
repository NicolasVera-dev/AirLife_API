package com.airlife.wsAirLife.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "save_sensor")
public class SaveSensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int id_sensor;
	
	private Timestamp date_heure_ajout;
	
	private String name_sensor;

	public SaveSensor() {
		
	}
	
	public SaveSensor(int id_sensor, String name_sensor) {
		this.id_sensor = id_sensor;
		this.date_heure_ajout = new Timestamp(System.currentTimeMillis());
		this.name_sensor = name_sensor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSensor() {
		return id_sensor;
	}

	public void setSensor(int id_sensor) {
		this.id_sensor = id_sensor;
	}

	public Timestamp getDate_heure_ajout() {
		return date_heure_ajout;
	}

	public void setDate_heure_ajout(Timestamp date_heure_ajout) {
		this.date_heure_ajout = date_heure_ajout;
	}

	public String getName_sensor() {
		return name_sensor;
	}

	public void setName_sensor(String name_sensor) {
		this.name_sensor = name_sensor;
	}

	@Override
	public String toString() {
		return "save_sensor [id=" + id + ", id_sensor=" + id_sensor + ", date_heure_ajout=" + date_heure_ajout
				+ ", name_sensor=" + name_sensor + "]";
	}
	
	
	
}

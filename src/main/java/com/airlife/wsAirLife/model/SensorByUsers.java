package com.airlife.wsAirLife.model;

public class SensorByUsers {
	
	private int idsensor;
	private String nameSensor;
	private String libelle;

	public SensorByUsers(int idsensor, String nameSensor, String libelle) {
		super();
		this.idsensor = idsensor;
		this.nameSensor = nameSensor;
		this.libelle = libelle;
	}
	 
	public SensorByUsers() {
		
	}

	public String getNameSensor() {
		return nameSensor;
	}

	public void setNameSensor(String nameSensor) {
		this.nameSensor = nameSensor;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getIdsensor() {
		return idsensor;
	}

	public void setIdsensor(int idsensor) {
		this.idsensor = idsensor;
	}
	
	
	
}

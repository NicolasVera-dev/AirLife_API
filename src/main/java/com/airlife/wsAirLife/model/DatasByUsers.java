package com.airlife.wsAirLife.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DatasByUsers {
	
	private int iddata;
	private String nameSensor;
	private double datasensor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimedata;
	
	private String libelle;

	public DatasByUsers(int iddata, String nameSensor, double datasensor, Date datetimedata, String libelle) {
		super();
		this.iddata = iddata;
		this.nameSensor = nameSensor;
		this.datasensor = datasensor;
		this.datetimedata = datetimedata;
		this.libelle = libelle;
	}
	
	public DatasByUsers() {
		
	}

	public int getIddata() {
		return iddata;
	}

	public void setIddata(int iddata) {
		this.iddata = iddata;
	}

	public String getNameSensor() {
		return nameSensor;
	}

	public void setNameSensor(String nameSensor) {
		this.nameSensor = nameSensor;
	}

	public double getDatasensor() {
		return datasensor;
	}

	public void setDatasensor(double datasensor) {
		this.datasensor = datasensor;
	}

	public Date getDatetimedata() {
		return datetimedata;
	}

	public void setDatetimedata(Date datetimedata) {
		this.datetimedata = datetimedata;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}

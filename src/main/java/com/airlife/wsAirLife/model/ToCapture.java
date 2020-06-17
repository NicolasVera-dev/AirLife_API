package com.airlife.wsAirLife.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ToCapture.class)
@Table(name = "to_capture")
public class ToCapture implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="idsensor")
	private Sensor sensor;
	
	@Id
	@ManyToOne
	@JoinColumn(name="iddatatype")
	private Datatype datatype;
	
	public ToCapture() {
		
	}
	
	public ToCapture(Sensor sensor, Datatype datatype) {
		this.sensor = sensor;
		this.datatype = datatype;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Datatype getDatatype() {
		return datatype;
	}

	public void setDatatype(Datatype datatype) {
		this.datatype = datatype;
	}
	
	
}

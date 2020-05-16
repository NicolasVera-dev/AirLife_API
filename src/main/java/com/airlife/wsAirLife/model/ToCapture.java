package com.airlife.wsAirLife.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "to_capture")
public class ToCapture implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDSENSOR")
	private Sensor sensor;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDDATATYPE")
	private Datatype datatype;
	
	public ToCapture() {
		
	}
}

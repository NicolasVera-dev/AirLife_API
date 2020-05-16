package com.airlife.wsAirLife.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Datas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int iddata;
	
	@OneToOne
	@JoinColumn(name="idsensor")
	Sensor sensor;
	
	@ManyToOne
	@JoinColumn(name="iddatatype")
	private Datatype datatype;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimedata;
	
	private double datasensor;
	
	public Datas() {
		
	}

	public Datas(Sensor sensor, Datatype datatype, double datasensor) {
		this.sensor = sensor;
		this.datatype = datatype;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//Set local timezone (-1h)
		timestamp.setTime(3600000+timestamp.getTime()); 
		this.datetimedata = timestamp;
		
		this.datasensor = datasensor;
	}

	public int getIddata() {
		return iddata;
	}

	public void setIddata(int iddata) {
		this.iddata = iddata;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Datatype getIddatatype() {
		return datatype;
	}

	public void setIddatatype(Datatype datatype) {
		this.datatype = datatype;
	}

	public Date getDatetimedate() {
		return datetimedata;
	}

	public void setDatetimedate(Date datetimedate) {
		this.datetimedata = datetimedate;
	}

	public double getDatasensor() {
		return datasensor;
	}

	public void setDatasensor(double datasensor) {
		this.datasensor = datasensor;
	}

	@Override
	public String toString() {
		return "Datas [iddata=" + iddata + ", sensor=" + sensor + ", datatype=" + datatype + ", datetimedata="
				+ datetimedata + ", datasensor=" + datasensor + "]";
	}
	
}

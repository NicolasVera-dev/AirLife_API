package com.airlife.wsAirLife.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class Sensor {
	
	@Id
	private int idsensor;
	
	@OneToOne
	@JoinColumn(name="iduser")
	private Users user;
	
	private String namesensor;
	
	public Sensor() {
		
	}

	public Sensor(int idsensor, Users user, String namesensor) {
		this.idsensor = idsensor;
		this.user = user;
		this.namesensor = namesensor;
	}

	public int getIdsensor() {
		return idsensor;
	}

	public void setIdsensor(int idsensor) {
		this.idsensor = idsensor;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getText() {
		return namesensor;
	}

	public void setText(String namesensor) {
		this.namesensor = namesensor;
	}

	@Override
	public String toString() {
		return "Sensor [idsensor=" + idsensor + ", user=" + user + ", namesensor=" + namesensor + "]";
	}
	
}

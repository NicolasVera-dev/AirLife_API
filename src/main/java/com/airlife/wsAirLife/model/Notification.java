package com.airlife.wsAirLife.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idnotif;
	
	@OneToOne
	@JoinColumn(name="userid")
	private Users user;
	
	private String textnotification;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datenotif;
	
	public Notification() {
		
	}

	public Notification(Users user, String textnotification, Date datenotif) {
		this.user = user;
		this.textnotification = textnotification;
		this.datenotif = datenotif;
	}

	public int getIdnotif() {
		return idnotif;
	}

	public void setIdnotif(int idnotif) {
		this.idnotif = idnotif;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getTextnotification() {
		return textnotification;
	}

	public void setTextnotification(String textnotification) {
		this.textnotification = textnotification;
	}

	public Date getDatenotif() {
		return datenotif;
	}

	public void setDatenotif(Date datenotif) {
		this.datenotif = datenotif;
	}

	@Override
	public String toString() {
		return "Notification [idnotif=" + idnotif + ", user=" + user + ", textnotification=" + textnotification
				+ ", datenotif=" + datenotif + "]";
	}
	
	
}

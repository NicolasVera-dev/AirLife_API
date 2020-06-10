package com.airlife.wsAirLife.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Datatype {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int iddatatype;
	
	private String libelle;
	private double seuil;
	
	public Datatype(int iddatatype, String libelle, double seuil) {
		super();
		this.iddatatype = iddatatype;
		this.libelle = libelle;
		this.seuil = seuil;
	}
	
	public Datatype() {
		
	}

	public int getIddatatype() {
		return iddatatype;
	}

	public void setIddatatype(int iddatatype) {
		this.iddatatype = iddatatype;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	@Override
	public String toString() {
		return "Datatype [iddatatype=" + iddatatype + ", libelle=" + libelle + ", seuil=" + seuil + "]";
	}

}

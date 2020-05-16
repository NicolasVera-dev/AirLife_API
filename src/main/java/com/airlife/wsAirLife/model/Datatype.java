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
	private int threshold;
	
	public Datatype(int iddatatype, String libelle, int threshold) {
		super();
		this.iddatatype = iddatatype;
		this.libelle = libelle;
		this.threshold = threshold;
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

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "Datatype [iddatatype=" + iddatatype + ", libelle=" + libelle + ", threshold=" + threshold + "]";
	}

}

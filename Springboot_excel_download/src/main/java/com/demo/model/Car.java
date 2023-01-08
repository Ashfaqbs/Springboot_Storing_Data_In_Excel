package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int cId;
	
	
	private String cName;
	
	private double cost;

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(int cId, String cName, double cost) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cost = cost;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Car [cId=" + cId + ", cName=" + cName + ", cost=" + cost + "]";
	}
	
	
	
	
	
	
}

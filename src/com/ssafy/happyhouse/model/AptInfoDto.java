package com.ssafy.happyhouse.model;

public class AptInfoDto {

	private int aptid;
	private String dong;
	private String bunzi;
	private String name;
	private double area;
	private String amount;
	private int floor;


	public AptInfoDto(int aptid, String dong, String bunzi, String name, double area, String amount, int floor) {
		super();
		this.aptid = aptid;
		this.dong = dong;
		this.bunzi = bunzi;
		this.name = name;
		this.area = area;
		this.amount = amount;
		this.floor = floor;
	}



	public AptInfoDto() {
	}



	public int getAptid() {
		return aptid;
	}



	public void setAptid(int aptid) {
		this.aptid = aptid;
	}



	public String getDong() {
		return dong;
	}



	public void setDong(String dong) {
		this.dong = dong;
	}



	public String getBunzi() {
		return bunzi;
	}



	public void setBunzi(String bunzi) {
		this.bunzi = bunzi;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getArea() {
		return area;
	}



	public void setArea(double area) {
		this.area = area;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public int getFloor() {
		return floor;
	}



	public void setFloor(int floor) {
		this.floor = floor;
	}



	

}

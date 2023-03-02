package com.rendShow.customerService.pojo;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String ghanaPostGPS;
	private String streetName;
	private String city;
	private String region;
	
	public String getGhanaPostGPS() {
		return ghanaPostGPS;
	}
	
	public void setGhanaPostGPS(String ghanaPostGPS) {
		this.ghanaPostGPS = ghanaPostGPS;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
		

}

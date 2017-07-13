/**
 * 文件名    :StudentAddress.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年5月15日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.chapter31;

/**
 * @author CycloneBoy
 *
 */
public class StudentAddress {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	
	/**
	 * 
	 */
	public StudentAddress() {
		// TODO Auto-generated constructor stub
	}


	public StudentAddress(String name, String street, String city, String state, String zip) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}

}

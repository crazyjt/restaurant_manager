package com.jt.domain;

import java.io.Serializable;
import java.sql.Date;

public class Restaurant implements Serializable {
	private String info_id;
	private String info_name;
	private String info_address;
	private Date info_startDate;
	private String info_businessHours;

	private Worker worker;
	
	
	public Restaurant() {
		super();
	}
	
	
	public Restaurant(String info_id, String info_name, String info_address, Date info_startDate,
			String info_businessHours) {
		super();
		this.info_id = info_id;
		this.info_name = info_name;
		this.info_address = info_address;
		this.info_startDate = info_startDate;
		this.info_businessHours = info_businessHours;
	}


	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_address() {
		return info_address;
	}
	public void setInfo_address(String info_address) {
		this.info_address = info_address;
	}
	public Date getInfo_startDate() {
		System.out.println(info_startDate);
		return info_startDate;
	}
	public void setInfo_startDate(Date info_startDate) {
		this.info_startDate = info_startDate;
	}
	public String getInfo_businessHours() {
		return info_businessHours;
	}
	public void setInfo_businessHours(String info_businessHours) {
		this.info_businessHours = info_businessHours;
	}
	@Override
	public String toString() {
		return "Restaurant [info_id=" + info_id + ", info_name=" + info_name + ", info_address=" + info_address
				+ ", info_startDate=" + info_startDate + ", info_businessHours=" + info_businessHours;
	}

}

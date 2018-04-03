package com.jt.domain;

import java.util.HashSet;
import java.util.Set;

public class Worker {
	private String w_id;
	private String w_password;
	private String w_name;
	private String w_type;
	private String w_sex;
	private float w_workTime;
	
	private String w_path;
	private String w_filename;
	
	
	private Set<Restaurant> restaurants = new HashSet<Restaurant>();
	private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>();
	private Set<Chargeinfo> chargeinfos = new HashSet<Chargeinfo>();
	
	
	public Worker (){
		
	}
	public Worker(String w_id, String w_password, String w_name, String w_type, String w_sex, float w_workTime) {
		super();
		this.w_id = w_id;
		this.w_password = w_password;
		this.w_name = w_name;
		this.w_type = w_type;
		this.w_sex = w_sex;
		this.w_workTime = w_workTime;
	}
	
	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public Set<OrderInfo> getOrderInfos() {
		return orderInfos;
	}
	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	
	public Set<Chargeinfo> getChargeinfos() {
		return chargeinfos;
	}
	public void setChargeinfos(Set<Chargeinfo> chargeinfos) {
		this.chargeinfos = chargeinfos;
	}
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
	}
	public String getW_password() {
		return w_password;
	}
	public void setW_password(String w_password) {
		this.w_password = w_password;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getW_type() {
		return w_type;
	}
	public void setW_type(String w_type) {
		this.w_type = w_type;
	}
	public String getW_sex() {
		return w_sex;
	}
	public void setW_sex(String w_sex) {
		this.w_sex = w_sex;
	}
	public float getW_workTime() {
		return w_workTime;
	}
	public void setW_workTime(float w_workTime) {
		this.w_workTime = w_workTime;
	}
	public String getW_path() {
		return w_path;
	}
	public void setW_path(String w_path) {
		this.w_path = w_path;
	}
	public String getW_filename() {
		return w_filename;
	}
	public void setW_filename(String w_filename) {
		this.w_filename = w_filename;
	}
	@Override
	public String toString() {
		return "Worker [w_id=" + w_id + ", w_password=" + w_password + ", w_name=" + w_name + ", w_type=" + w_type
				+ ", w_sex=" + w_sex + ", w_workTime=" + w_workTime;
	}
	
}

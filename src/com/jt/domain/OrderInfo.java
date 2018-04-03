package com.jt.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class OrderInfo implements Serializable {
	private String o_id;
	private float o_price;
	private Timestamp o_serverTime;
	private String o_pay;
	private String m_order;
	
	private Worker worker;
	private Diningtable diningtable;
	
	private Set<Menu> menus = new HashSet<Menu>();
	private Set<Chargeinfo> chargeinfos = new HashSet<Chargeinfo>();
	
	public OrderInfo() {
		super();
	}
	
	public OrderInfo(String o_id, float o_price, Timestamp o_serverTime) {
		super();
		this.o_id = o_id;
		this.o_price = o_price;
		this.o_serverTime = o_serverTime;
	}

	
	public Set<Chargeinfo> getChargeinfos() {
		return chargeinfos;
	}

	public void setChargeinfos(Set<Chargeinfo> chargeinfos) {
		this.chargeinfos = chargeinfos;
	}

	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public float getO_price() {
		return o_price;
	}
	public void setO_price(float o_price) {
		this.o_price = o_price;
	}
	public Timestamp getO_serverTime() {
		return o_serverTime;
	}
	public void setO_serverTime(Timestamp o_serverTime) {
		this.o_serverTime = o_serverTime;
	}
	
	public String getO_pay() {
		return o_pay;
	}

	public void setO_pay(String o_pay) {
		this.o_pay = o_pay;
	}

	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Diningtable getDiningtable() {
		return diningtable;
	}
	public void setDiningtable(Diningtable diningtable) {
		this.diningtable = diningtable;
	}

	public String getM_order() {
		return m_order;
	}

	public void setM_order(String m_order) {
		this.m_order = m_order;
	}

	@Override
	public String toString() {
		return "OrderInfo [o_id=" + o_id + ", o_price=" + o_price + ", o_serverTime=" + o_serverTime + ", o_pay="
				+ o_pay + ", worker=" + worker + ", diningtable=" + diningtable + ", menus=" + menus + ", chargeinfos="
				+ chargeinfos + "]";
	}
}

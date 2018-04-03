package com.jt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Serializable {
	private String m_id;
	private String m_name;
	private float m_price;
	private int m_inventory;
	private String m_type;
	
	private String m_path;
	private String m_filename; 
	
	private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>();
	
	public Menu() {
		super();
	}
	
	public Menu(String m_id, String m_name, float m_price, int m_inventory, String m_type) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_inventory = m_inventory;
		this.m_type = m_type;
	}

	
	public Set<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public float getM_price() {
		return m_price;
	}
	public void setM_price(float m_price) {
		this.m_price = m_price;
	}
	public int getM_inventory() {
		return m_inventory;
	}
	public void setM_inventory(int m_inventory) {
		this.m_inventory = m_inventory;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	
	public String getM_path() {
		return m_path;
	}

	public void setM_path(String m_path) {
		this.m_path = m_path;
	}

	public String getM_filename() {
		return m_filename;
	}

	public void setM_filename(String m_filename) {
		this.m_filename = m_filename;
	}

	@Override
	public String toString() {
		return "Menu [m_id=" + m_id + ", m_name=" + m_name + ", m_price=" + m_price + ", m_inventory=" + m_inventory
				+ ", m_type=" + m_type + "]";
	}
	
}

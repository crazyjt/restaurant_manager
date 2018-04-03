package com.jt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Diningtable implements Serializable {
	private String d_id;
	private String d_no;
	private String d_status;
	private int d_num;
	
	private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>();
	private Set<Chargeinfo> chargeinfos = new HashSet<Chargeinfo>();
	
	public Diningtable() {
		super();
	}
	
	public Diningtable(String d_id, String d_no, String d_status, int d_num) {
		super();
		this.d_id = d_id;
		this.d_no = d_no;
		this.d_status = d_status;
		this.d_num = d_num;
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

	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_no() {
		return d_no;
	}
	public void setD_no(String d_no) {
		this.d_no = d_no;
	}
	public String getD_status() {
		return d_status;
	}
	public void setD_status(String d_status) {
		this.d_status = d_status;
	}
	public int getD_num() {
		return d_num;
	}
	public void setD_num(int d_num) {
		this.d_num = d_num;
	}

	@Override
	public String toString() {
		return "Diningtable [d_id=" + d_id + ", d_no=" + d_no + ", d_status=" + d_status + ", d_num=" + d_num;
	}
	
	
}

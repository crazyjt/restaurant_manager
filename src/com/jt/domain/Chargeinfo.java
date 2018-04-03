package com.jt.domain;

import java.io.Serializable;
import java.sql.Date;


public class Chargeinfo implements Serializable {
	private String c_id;
	private float c_requestReceive;
	private float c_factReceive;
	private float c_returnMoney;
	private String c_giveBillflg;
	private String c_remark;
	
	private OrderInfo orderInfo;
	private Worker worker;
	private Diningtable diningtable;
	
	
	public Chargeinfo() {
		super();
	}
	
	public Chargeinfo(String c_id, float c_requestReceive, float c_factReceive, float c_returnMoney,
			String c_giveBillflg, String c_remark) {
		super();
		this.c_id = c_id;
		this.c_requestReceive = c_requestReceive;
		this.c_factReceive = c_factReceive;
		this.c_returnMoney = c_returnMoney;
		this.c_giveBillflg = c_giveBillflg;
		this.c_remark = c_remark;
	}

	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public float getC_requestReceive() {
		return c_requestReceive;
	}
	public void setC_requestReceive(float c_requestReceive) {
		this.c_requestReceive = c_requestReceive;
	}
	public float getC_factReceive() {
		return c_factReceive;
	}
	public void setC_factReceive(float c_factReceive) {
		this.c_factReceive = c_factReceive;
	}
	public float getC_returnMoney() {
		return c_returnMoney;
	}
	public void setC_returnMoney(float c_returnMoney) {
		this.c_returnMoney = c_returnMoney;
	}
	public String getC_giveBillflg() {
		return c_giveBillflg;
	}
	public void setC_giveBillflg(String c_giveBillflg) {
		this.c_giveBillflg = c_giveBillflg;
	}
	public String getC_remark() {
		return c_remark;
	}
	public void setC_remark(String c_remark) {
		this.c_remark = c_remark;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public Diningtable getDiningtable() {
		return diningtable;
	}
	public void setDiningtable(Diningtable diningtable) {
		this.diningtable = diningtable;
	}
	@Override
	public String toString() {
		return "Chargeinfo [c_id=" + c_id + ", c_requestReceive=" + c_requestReceive + ", c_factReceive="
				+ c_factReceive + ", c_returnMoney=" + c_returnMoney + ", c_giveBillflg=" + c_giveBillflg
				+ ", c_remark=" + c_remark;
	}
	
	
}

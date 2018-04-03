package com.jt.service.impl;

import java.util.List;

import com.jt.dao.OrderInfoDao;
import com.jt.dao.impl.OrderInfoDaoImpl;
import com.jt.domain.OrderInfo;
import com.jt.service.IOrderInfoService;
import com.sun.org.apache.bcel.internal.generic.DSTORE;

public class OrderInfoServiceImpl implements IOrderInfoService {
	
	private OrderInfoDao dao = new OrderInfoDaoImpl();

	@Override
	public int addOrder(OrderInfo orderInfo) {
		return dao.insertOrder(orderInfo);
	}

	@Override
	public int modifyOrder(OrderInfo orderInfo) {
		return dao.updateOrder(orderInfo);
	}

	@Override
	public int removeOrder(String o_id) {
		return dao.deleteOrder(o_id);
	}

	@Override
	public OrderInfo findOrderById(String o_id) {
		return dao.selectOrderById(o_id);
	}

	@Override
	public List<OrderInfo> findAllOrder() {
		return dao.selectAllOrder();
	}

	@Override
	public List<OrderInfo> findOrderByCondition(String o_id, String d_id, String o_pay) {
		return dao.selectOrderByCondition(o_id, d_id, o_pay);
	}

	@Override
	public OrderInfo findOrderByTable(String d_id, String o_pay) {
		return dao.selectOrderByTable(d_id, o_pay);
	}

	@Override
	public OrderInfo findLastOrder() {
		return dao.selectLastOrder();
	}

}

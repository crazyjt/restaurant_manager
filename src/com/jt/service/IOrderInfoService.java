package com.jt.service;

import java.util.List;

import com.jt.domain.OrderInfo;

public interface IOrderInfoService {

	/**
	 * 添加订单
	 * @param orderInfo
	 * @return
	 */
	int addOrder(OrderInfo orderInfo);
	
	/**
	 * 修改订单
	 * @param orderInfo
	 * @return
	 */
	int modifyOrder(OrderInfo orderInfo);
	
	/**
	 * 删除订单
	 * @param o_id
	 * @return
	 */
	int removeOrder(String o_id);
	
	/**
	 * 根据id查询订单
	 * @param o_id
	 * @return
	 */
	OrderInfo findOrderById(String o_id);
	
	/**
	 * 查询所有订单
	 * @return
	 */
	List<OrderInfo> findAllOrder();
	
	/**
	 * 根据条件查询订单
	 * @param o_id
	 * @param d_id
	 * @param o_pay
	 * @return
	 */
	List<OrderInfo> findOrderByCondition(String o_id, String d_id, String o_pay);
	
	/**
	 * 根据餐桌id和状态查询订单
	 * @param d_id
	 * @param d_status
	 * @return
	 */
	OrderInfo findOrderByTable(String d_id, String o_pay);
	
	/**
	 * 取得最后一条order
	 * @return
	 */
	OrderInfo findLastOrder();
}

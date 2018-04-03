package com.jt.dao;

import java.util.List;

import com.jt.domain.OrderInfo;

public interface OrderInfoDao {

	/**
	 * 插入订单数据
	 * @param orderInfo
	 * @return
	 */
	int insertOrder(OrderInfo orderInfo);

	/**
	 * 更新订单数据
	 * @param orderInfo
	 * @return
	 */
	int updateOrder(OrderInfo orderInfo);

	/**
	 * 删除订单数据
	 * @param o_id
	 * @return
	 */
	int deleteOrder(String o_id);
	
	/**
	 * 根据id查询订单数据
	 * @param o_id
	 * @return
	 */
	OrderInfo selectOrderById(String o_id);
	
	/**
	 * 查询所有订单数据
	 * @return
	 */
	List<OrderInfo> selectAllOrder();
	
	/**
	 * 根据条件查询订单数据
	 * @param o_id
	 * @param d_id
	 * @param o_pay
	 * @return
	 */
	List<OrderInfo> selectOrderByCondition(String o_id, String d_id, String o_pay);

	OrderInfo selectOrderByTable(String d_id, String o_pay);

	OrderInfo selectLastOrder();
}

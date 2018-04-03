package com.jt.dao;

import java.util.List;

import com.jt.domain.OrderInfo;

public interface OrderInfoDao {

	/**
	 * ���붩������
	 * @param orderInfo
	 * @return
	 */
	int insertOrder(OrderInfo orderInfo);

	/**
	 * ���¶�������
	 * @param orderInfo
	 * @return
	 */
	int updateOrder(OrderInfo orderInfo);

	/**
	 * ɾ����������
	 * @param o_id
	 * @return
	 */
	int deleteOrder(String o_id);
	
	/**
	 * ����id��ѯ��������
	 * @param o_id
	 * @return
	 */
	OrderInfo selectOrderById(String o_id);
	
	/**
	 * ��ѯ���ж�������
	 * @return
	 */
	List<OrderInfo> selectAllOrder();
	
	/**
	 * ����������ѯ��������
	 * @param o_id
	 * @param d_id
	 * @param o_pay
	 * @return
	 */
	List<OrderInfo> selectOrderByCondition(String o_id, String d_id, String o_pay);

	OrderInfo selectOrderByTable(String d_id, String o_pay);

	OrderInfo selectLastOrder();
}

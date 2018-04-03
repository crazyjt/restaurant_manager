package com.jt.service;

import java.util.List;

import com.jt.domain.OrderInfo;

public interface IOrderInfoService {

	/**
	 * ��Ӷ���
	 * @param orderInfo
	 * @return
	 */
	int addOrder(OrderInfo orderInfo);
	
	/**
	 * �޸Ķ���
	 * @param orderInfo
	 * @return
	 */
	int modifyOrder(OrderInfo orderInfo);
	
	/**
	 * ɾ������
	 * @param o_id
	 * @return
	 */
	int removeOrder(String o_id);
	
	/**
	 * ����id��ѯ����
	 * @param o_id
	 * @return
	 */
	OrderInfo findOrderById(String o_id);
	
	/**
	 * ��ѯ���ж���
	 * @return
	 */
	List<OrderInfo> findAllOrder();
	
	/**
	 * ����������ѯ����
	 * @param o_id
	 * @param d_id
	 * @param o_pay
	 * @return
	 */
	List<OrderInfo> findOrderByCondition(String o_id, String d_id, String o_pay);
	
	/**
	 * ���ݲ���id��״̬��ѯ����
	 * @param d_id
	 * @param d_status
	 * @return
	 */
	OrderInfo findOrderByTable(String d_id, String o_pay);
	
	/**
	 * ȡ�����һ��order
	 * @return
	 */
	OrderInfo findLastOrder();
}

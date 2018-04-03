package com.jt.dao;

import java.util.List;

import com.jt.domain.Restaurant;

public interface RestaurantDao {
	
	/**
	 * ��Ӳ���
	 * @param restaurant
	 * @return
	 */
	int insertRestaurant(Restaurant restaurant);

	/**
	 * ���²�����Ϣ
	 * @param restaurant
	 * @return
	 */
	int updateRestaurant(Restaurant restaurant);

	/**
	 * ���ݲ���idɾ������
	 * @param info_id
	 * @return
	 */
	int deleteRestaurant(String info_id);

	/**
	 * ���ݲ���id��ѯ������Ϣ
	 * @param info_id
	 * @return
	 */
	Restaurant selectRestaurantById(String info_id);

	/**
	 * �������в���
	 * @return
	 */
	List<Restaurant> selectAllRestaurant();

	/**
	 * ����������ѯ����
	 * @param w_name
	 * @param info_address
	 * @return
	 */
	List<Restaurant> selectRestaurantByCondition(String w_name, String info_address);

}

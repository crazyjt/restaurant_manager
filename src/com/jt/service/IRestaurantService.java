package com.jt.service;

import java.util.List;

import com.jt.domain.Restaurant;

public interface IRestaurantService {
	
	/**
	 * ��ѯ���в���
	 * @return
	 */
	List<Restaurant> findAllRestaurant();

	/**
	 * ����������ѯ����
	 * @param w_name
	 * @param info_address
	 * @return
	 */
	List<Restaurant> findRestaurantByCondition(String w_name, String info_address);

	/**
	 * ��Ӳ���
	 * @param restaurant
	 * @return 
	 */
	int addRestaurant(Restaurant restaurant);

	Restaurant findRestaurantById(String info_id);

	int modifyRestaurant(Restaurant restaurant);

	int removeRestaurant(String info_id);
}

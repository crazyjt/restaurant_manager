package com.jt.service;

import java.util.List;

import com.jt.domain.Restaurant;

public interface IRestaurantService {
	
	/**
	 * 查询所有餐厅
	 * @return
	 */
	List<Restaurant> findAllRestaurant();

	/**
	 * 根据条件查询餐厅
	 * @param w_name
	 * @param info_address
	 * @return
	 */
	List<Restaurant> findRestaurantByCondition(String w_name, String info_address);

	/**
	 * 添加餐厅
	 * @param restaurant
	 * @return 
	 */
	int addRestaurant(Restaurant restaurant);

	Restaurant findRestaurantById(String info_id);

	int modifyRestaurant(Restaurant restaurant);

	int removeRestaurant(String info_id);
}

package com.jt.dao;

import java.util.List;

import com.jt.domain.Restaurant;

public interface RestaurantDao {
	
	/**
	 * 添加餐厅
	 * @param restaurant
	 * @return
	 */
	int insertRestaurant(Restaurant restaurant);

	/**
	 * 更新餐厅信息
	 * @param restaurant
	 * @return
	 */
	int updateRestaurant(Restaurant restaurant);

	/**
	 * 根据餐厅id删除餐厅
	 * @param info_id
	 * @return
	 */
	int deleteRestaurant(String info_id);

	/**
	 * 根据餐厅id查询餐厅信息
	 * @param info_id
	 * @return
	 */
	Restaurant selectRestaurantById(String info_id);

	/**
	 * 查找所有餐厅
	 * @return
	 */
	List<Restaurant> selectAllRestaurant();

	/**
	 * 根据条件查询餐厅
	 * @param w_name
	 * @param info_address
	 * @return
	 */
	List<Restaurant> selectRestaurantByCondition(String w_name, String info_address);

}

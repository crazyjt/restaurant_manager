package com.jt.service.impl;

import java.util.List;

import com.jt.dao.RestaurantDao;
import com.jt.dao.impl.RestaurantDaoImpl;
import com.jt.domain.Restaurant;
import com.jt.service.IRestaurantService;

public class RestaurantServiceImpl implements IRestaurantService {
	
	private RestaurantDao dao = new RestaurantDaoImpl();
	
	@Override
	public List<Restaurant> findAllRestaurant() {
		return dao.selectAllRestaurant();
	}

	@Override
	public List<Restaurant> findRestaurantByCondition(String w_name, String info_address) {
		return dao.selectRestaurantByCondition(w_name, info_address); 
	}

	@Override
	public int addRestaurant(Restaurant restaurant) {
		return dao.insertRestaurant(restaurant);
	}

	@Override
	public Restaurant findRestaurantById(String info_id) {
		return dao.selectRestaurantById(info_id);
	}

	@Override
	public int modifyRestaurant(Restaurant restaurant) {
		return dao.updateRestaurant(restaurant);
	}

	@Override
	public int removeRestaurant(String info_id) {
		return dao.deleteRestaurant(info_id);
	}

}

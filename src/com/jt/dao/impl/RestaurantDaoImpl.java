package com.jt.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jt.dao.RestaurantDao;
import com.jt.domain.Restaurant;
import com.jt.utils.SessionUtils;


public class RestaurantDaoImpl implements RestaurantDao {

	@Override
	public int insertRestaurant(Restaurant restaurant) {
		try {
			int SECCUSS = 1;
			Session session = SessionUtils.getCurrentSession();
			session.save(restaurant);
			return SECCUSS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		try{
			int SUCCESS = 1;
			Session session = SessionUtils.getCurrentSession();
			Restaurant dbRestaurant = (Restaurant) session.get(Restaurant.class, restaurant.getInfo_id());
			dbRestaurant.setInfo_name(restaurant.getInfo_name());
			dbRestaurant.setInfo_address(restaurant.getInfo_address());
			dbRestaurant.setInfo_startDate(restaurant.getInfo_startDate());
			dbRestaurant.setInfo_businessHours(restaurant.getInfo_businessHours());
			dbRestaurant.setWorker(restaurant.getWorker());

			session.update(dbRestaurant);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteRestaurant(String info_id) {
		try{
			int SUCCESS = 1;
			Session session = SessionUtils.getCurrentSession();
			Restaurant dbRestaurant = (Restaurant) session.get(Restaurant.class, info_id);
			session.delete(dbRestaurant);
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Restaurant selectRestaurantById(String info_id) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Restaurant restaurant = (Restaurant) session.get(Restaurant.class, info_id);
			if (restaurant != null) {
				return restaurant;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Restaurant> selectAllRestaurant() {
		try{
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Restaurant");
			return query.list();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Restaurant> selectRestaurantByCondition(String w_name, String info_address) {
			Session session = SessionUtils.getCurrentSession();
			if(StringUtils.isBlank(w_name) && StringUtils.isBlank(info_address)) {
				return selectAllRestaurant();
			} else {
				try{
					String sqlStr = "from Restaurant";
					StringBuffer queryBuffer = new StringBuffer(sqlStr);
					queryBuffer.append(" where 1=1");
					if(StringUtils.isNotBlank(w_name)) {
						queryBuffer.append(" and w_name = '");
						queryBuffer.append(w_name.toString() + "'");
					}
					if(StringUtils.isNotBlank(info_address)) {
						queryBuffer.append(" and info_address like '%");
						queryBuffer.append(info_address.toString() + "%'");
					}
					Query query =  session.createQuery(queryBuffer.toString());
					return query.list();
				}catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
	}

}

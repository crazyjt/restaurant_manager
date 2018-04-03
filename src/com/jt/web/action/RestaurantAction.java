package com.jt.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jt.domain.Restaurant;
import com.jt.domain.Worker;
import com.jt.service.IRestaurantService;
import com.jt.service.IWorkerService;
import com.jt.service.impl.RestaurantServiceImpl;
import com.jt.service.impl.WorkerServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class RestaurantAction extends ActionSupport implements ModelDriven<Restaurant> {

	Restaurant restaurant = new Restaurant();

	@Override
	public Restaurant getModel() {
		return restaurant;
	}

	private IRestaurantService service = new RestaurantServiceImpl();
	private IWorkerService workerService = new WorkerServiceImpl();

	// 显示所有餐厅信息
	List<Restaurant> restaurants;

	public String listAllRestaurant() throws Exception {
		restaurants = service.findAllRestaurant();
		return SUCCESS;
	}

	// 根据条件查询餐厅
	public String listRestaurantByCondition() throws Exception {
		restaurants = service.findRestaurantByCondition(restaurant.getWorker().getW_name(),
				restaurant.getInfo_address());
		return SUCCESS;
	}

	// 添加餐厅
	public String addRestaurant() throws Exception {
		if (restaurant.getInfo_id() == null || StringUtils.isBlank(restaurant.getInfo_id())) {
			addActionError("id不能为空");
			return INPUT;
		}
		Worker worker = workerService.findWorkerByName(restaurant.getWorker().getW_name());
		if (worker == null) {
			addActionError("该经营者姓名不存在");
			return INPUT;
		}
		Restaurant dbRestaurant = service.findRestaurantById(restaurant.getInfo_id());
		if(dbRestaurant != null){
			addActionError("该id已经存在");
			return INPUT;
		}
		int res = service.addRestaurant(restaurant);
		return SUCCESS;
	}

	// 显示修改餐厅界面
	public String editRestaurantUI() throws Exception {
		restaurant = service.findRestaurantById(restaurant.getInfo_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(restaurant);
		return SUCCESS;
	}

	// 修改餐厅信息
	public String editRestaurant() throws Exception {
		int res = service.modifyRestaurant(restaurant);
		Worker worker = workerService.findWorkerByName(restaurant.getWorker().getW_name());
		if (worker == null) {
			addActionError("该经营者姓名不存在");
			return INPUT;
		}
		if(res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}

	//显示餐厅信息
	public String viewRestaurant() throws Exception {
		restaurant = service.findRestaurantById(restaurant.getInfo_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(restaurant);
		return SUCCESS;
	}
	
	//删除餐厅信息
	public String deleteRestaurant() throws Exception {
		int res = service.removeRestaurant(restaurant.getInfo_id());
		if(res > 0) {			
			return SUCCESS;
		} else {
			return null;
		}
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}

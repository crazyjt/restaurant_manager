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

	// ��ʾ���в�����Ϣ
	List<Restaurant> restaurants;

	public String listAllRestaurant() throws Exception {
		restaurants = service.findAllRestaurant();
		return SUCCESS;
	}

	// ����������ѯ����
	public String listRestaurantByCondition() throws Exception {
		restaurants = service.findRestaurantByCondition(restaurant.getWorker().getW_name(),
				restaurant.getInfo_address());
		return SUCCESS;
	}

	// ��Ӳ���
	public String addRestaurant() throws Exception {
		if (restaurant.getInfo_id() == null || StringUtils.isBlank(restaurant.getInfo_id())) {
			addActionError("id����Ϊ��");
			return INPUT;
		}
		Worker worker = workerService.findWorkerByName(restaurant.getWorker().getW_name());
		if (worker == null) {
			addActionError("�þ�Ӫ������������");
			return INPUT;
		}
		Restaurant dbRestaurant = service.findRestaurantById(restaurant.getInfo_id());
		if(dbRestaurant != null){
			addActionError("��id�Ѿ�����");
			return INPUT;
		}
		int res = service.addRestaurant(restaurant);
		return SUCCESS;
	}

	// ��ʾ�޸Ĳ�������
	public String editRestaurantUI() throws Exception {
		restaurant = service.findRestaurantById(restaurant.getInfo_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(restaurant);
		return SUCCESS;
	}

	// �޸Ĳ�����Ϣ
	public String editRestaurant() throws Exception {
		int res = service.modifyRestaurant(restaurant);
		Worker worker = workerService.findWorkerByName(restaurant.getWorker().getW_name());
		if (worker == null) {
			addActionError("�þ�Ӫ������������");
			return INPUT;
		}
		if(res > 0) {
			return SUCCESS;
		} else {
			return null;
		}
	}

	//��ʾ������Ϣ
	public String viewRestaurant() throws Exception {
		restaurant = service.findRestaurantById(restaurant.getInfo_id());
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(restaurant);
		return SUCCESS;
	}
	
	//ɾ��������Ϣ
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

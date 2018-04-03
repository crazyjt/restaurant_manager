package com.jt.service;

import java.util.List;

import com.jt.domain.Worker;

public interface IWorkerService {
	/**
	 * 根据用户id和密码登录
	 * @param userName
	 * @param password
	 * @return
	 */
	Worker login (String w_id,String w_password);
	
	/**
	 * 添加用户
	 * @param worker
	 * @return
	 */
	int addWorker (Worker worker);
	
	/**
	 * 修改用户信息
	 * @param worker
	 * @return
	 */
	int modifyWorker (Worker worker);
	
	/**
	 * 根据用户id删除用户信息
	 * @param w_id
	 * @return
	 */
	int removeWorker (String w_id);
	
	/**
	 * 根据id查询用户信息
	 * @param w_id
	 * @return
	 */
	Worker findWorkerById (String w_id);
	
	/**
	 * 查询所有用户信息
	 */
	List<Worker> findAllWorker ();
	
	/**
	 * 根据条件筛选用户
	 * @param w_type
	 * @param w_sex
	 * @param w_workTime
	 * @param hasPic 
	 * @return
	 */
	List<Worker> findWorkerByCondition (String w_type, String w_sex, String hasPic);

	/**
	 * 根据用户名查询用户
	 * @param w_name
	 */
	Worker findWorkerByName(String w_name);
}

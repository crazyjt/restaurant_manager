package com.jt.dao;

import java.util.List;

import com.jt.domain.Worker;

public interface WorkerDao {

	/**
	 * 根据用户id和密码查询用户
	 * @param w_id
	 * @param w_password
	 * @return
	 */
	Worker selectWorkerByInfo(String w_id, String w_password);

	/**
	 * 添加用户
	 * @param worker
	 * @return
	 */
	int insertWorker(Worker worker);

	/**
	 * 更新用户信息
	 * @param worker
	 * @return
	 */
	int updateWorker(Worker worker);

	/**
	 * 根据用户id删除用户
	 * @param w_id
	 * @return
	 */
	int deleteWorker(String w_id);

	/**
	 * 根据用户id查询用户信息
	 * @param w_id
	 * @return
	 */
	Worker selectWorkerById(String w_id);

	/**
	 * 查找所有用户
	 * @return
	 */
	List<Worker> selectAllWorker();

	/**
	 * 根据条件查找用户
	 * @param w_type
	 * @param w_sex
	 * @param w_workTime
	 * @param hasPic 
	 * @return
	 */
	List<Worker> selectWorkerByCondition(String w_type, String w_sex, String hasPic);

	Worker selectWorkerByName(String w_name);


}

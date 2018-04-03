package com.jt.service;

import java.util.List;

import com.jt.domain.Diningtable;

public interface IDiningtableService {
	
	/**
	 * 添加餐桌信息
	 * @param diningtable
	 * @return
	 */
	int addTable(Diningtable diningtable);
	
	/**
	 * 修改餐桌信息
	 * @param diningtable
	 * @return
	 */
	int modifyTable(Diningtable diningtable);

	/**
	 * 删除餐桌信息
	 * @param d_id
	 * @return
	 */
	int removeTable(String d_id);
	
	/**
	 * 根据id查找餐桌
	 * @param d_id
	 * @return
	 */
	Diningtable findTableById(String d_id);
	
	/**
	 * 查找所有餐桌信息
	 * @return
	 */
	List<Diningtable> findAllTable();
	
	/**
	 * 根据条件查找餐桌信息
	 * @param d_no
	 * @param d_status
	 * @return
	 */
	List<Diningtable> findTableByCondition(String d_no, String d_status);
}
	

package com.jt.dao;

import java.util.List;

import com.jt.domain.Diningtable;

public interface DiningtableDao {

	/**
	 * 插入餐桌
	 * @param diningtable
	 * @return
	 */
	int insertTable(Diningtable diningtable);

	/**
	 * 更新餐桌
	 * @param diningtable
	 * @return
	 */
	int updateTable(Diningtable diningtable);

	/**
	 * 删除餐桌
	 * @param d_id
	 * @return
	 */
	int deleteTable(String d_id);

	/**
	 * 根据id查询餐桌
	 * @param d_id
	 * @return
	 */
	Diningtable selectTableById(String d_id);

	/**
	 * 查找所有餐桌
	 * @return
	 */
	List<Diningtable> selectAllTable();

	/**
	 * 根据条件查找餐桌
	 * @param d_no
	 * @param d_status
	 * @return
	 */
	List<Diningtable> selectTableByCondition(String d_no, String d_status);

}

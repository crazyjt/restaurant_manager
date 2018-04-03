package com.jt.service;

import java.util.List;

import com.jt.domain.Chargeinfo;

public interface IChargeInfoService {

	/**
	 * 添加流水账
	 * @param chargeinfo
	 * @return
	 */
	int addCharge(Chargeinfo chargeinfo);
	
	/**
	 * 修改流水账
	 * @param chargeinfo
	 * @return
	 */
	int modifyCharge(Chargeinfo chargeinfo);
	
	/**
	 * 删除流水账
	 * @param c_id
	 * @return
	 */
	int removeCharge(String c_id);
	
	/**
	 * 根据id查询流水账
	 * @param c_id
	 * @return
	 */
	Chargeinfo findChagreById(String c_id);
	
	/**
	 * 查找所有流水账
	 * @return
	 */
	List<Chargeinfo> findAllCharge();
	
	/**
	 * 根据条件查找流水账
	 * @param o_id
	 * @param w_id
	 * @param d_id
	 * @param c_giveBillflg
	 * @return
	 */
	List<Chargeinfo> findChargeByCondition(String o_id, String w_id, String d_id, String c_giveBillflg);
}

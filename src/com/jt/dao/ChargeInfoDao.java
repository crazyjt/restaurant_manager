package com.jt.dao;

import java.util.List;

import com.jt.domain.Chargeinfo;

public interface ChargeInfoDao {

	int insertCharge(Chargeinfo chargeinfo);

	int updateCharge(Chargeinfo chargeinfo);

	int deleteCharge(String c_id);

	Chargeinfo selectChargeById(String c_id);

	List<Chargeinfo> selectAllCharge();

	List<Chargeinfo> selectChargeByCondition(String o_id, String w_id, String d_id, String c_giveBillflg);

}

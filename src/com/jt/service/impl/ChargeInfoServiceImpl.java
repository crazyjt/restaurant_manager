package com.jt.service.impl;

import java.util.List;
import com.jt.dao.ChargeInfoDao;
import com.jt.dao.impl.ChargeInfoDaoImpl;
import com.jt.domain.Chargeinfo;
import com.jt.service.IChargeInfoService;

public class ChargeInfoServiceImpl implements IChargeInfoService {

	private ChargeInfoDao dao = new ChargeInfoDaoImpl();
	
	@Override
	public int addCharge(Chargeinfo chargeinfo) {
		return dao.insertCharge(chargeinfo);
	}

	@Override
	public int modifyCharge(Chargeinfo chargeinfo) {
		return dao.updateCharge(chargeinfo);
	}

	@Override
	public int removeCharge(String c_id) {
		return dao.deleteCharge(c_id);
	}

	@Override
	public Chargeinfo findChagreById(String c_id) {
		return dao.selectChargeById(c_id);
	}

	@Override
	public List<Chargeinfo> findAllCharge() {
		return dao.selectAllCharge();
	}

	@Override
	public List<Chargeinfo> findChargeByCondition(String o_id, String w_id, String d_id, String c_giveBillflg) {
		return dao.selectChargeByCondition(o_id, w_id, d_id, c_giveBillflg);
	}

}

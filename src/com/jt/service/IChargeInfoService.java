package com.jt.service;

import java.util.List;

import com.jt.domain.Chargeinfo;

public interface IChargeInfoService {

	/**
	 * �����ˮ��
	 * @param chargeinfo
	 * @return
	 */
	int addCharge(Chargeinfo chargeinfo);
	
	/**
	 * �޸���ˮ��
	 * @param chargeinfo
	 * @return
	 */
	int modifyCharge(Chargeinfo chargeinfo);
	
	/**
	 * ɾ����ˮ��
	 * @param c_id
	 * @return
	 */
	int removeCharge(String c_id);
	
	/**
	 * ����id��ѯ��ˮ��
	 * @param c_id
	 * @return
	 */
	Chargeinfo findChagreById(String c_id);
	
	/**
	 * ����������ˮ��
	 * @return
	 */
	List<Chargeinfo> findAllCharge();
	
	/**
	 * ��������������ˮ��
	 * @param o_id
	 * @param w_id
	 * @param d_id
	 * @param c_giveBillflg
	 * @return
	 */
	List<Chargeinfo> findChargeByCondition(String o_id, String w_id, String d_id, String c_giveBillflg);
}

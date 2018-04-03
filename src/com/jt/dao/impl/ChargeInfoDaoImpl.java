package com.jt.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jt.dao.ChargeInfoDao;
import com.jt.domain.Chargeinfo;
import com.jt.utils.SessionUtils;

public class ChargeInfoDaoImpl implements ChargeInfoDao {

	int SUCCESS = 1;
	
	@Override
	public int insertCharge(Chargeinfo chargeinfo) {
		try {
			Session session = SessionUtils.getCurrentSession();
			session.save(chargeinfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateCharge(Chargeinfo chargeinfo) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Chargeinfo dbChargeinfo = (Chargeinfo) session.get(Chargeinfo.class, chargeinfo.getC_id());
			dbChargeinfo.setC_requestReceive(chargeinfo.getC_requestReceive());
			dbChargeinfo.setC_factReceive(chargeinfo.getC_factReceive());
			dbChargeinfo.setC_returnMoney(chargeinfo.getC_returnMoney());
			dbChargeinfo.setC_giveBillflg(chargeinfo.getC_giveBillflg());
			dbChargeinfo.setC_remark(chargeinfo.getC_remark());
			dbChargeinfo.setOrderInfo(chargeinfo.getOrderInfo());
			dbChargeinfo.setWorker(chargeinfo.getWorker());
			dbChargeinfo.setDiningtable(chargeinfo.getDiningtable());
			session.update(dbChargeinfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteCharge(String c_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Chargeinfo dbChargeinfo = (Chargeinfo) session.get(Chargeinfo.class, c_id);
			session.delete(dbChargeinfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Chargeinfo selectChargeById(String c_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			Chargeinfo dbchargeinfo = (Chargeinfo) session.get(Chargeinfo.class, c_id);
			if(dbchargeinfo != null) {
				return dbchargeinfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Chargeinfo> selectAllCharge() {
		try {
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Chargeinfo");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Chargeinfo> selectChargeByCondition(String o_id, String w_id, String d_id, String c_giveBillflg) {
		if(StringUtils.isBlank(o_id) && StringUtils.isBlank(w_id) && StringUtils.isBlank(d_id) && StringUtils.isBlank(c_giveBillflg)) {
			return selectAllCharge();
		} else {
			try {
				Session session = SessionUtils.getCurrentSession();
				String queryStr = "from Chargeinfo where 1=1";
				StringBuffer queryBuffer = new StringBuffer(queryStr);
				if(StringUtils.isNotBlank(o_id)) {
					queryBuffer.append(" and o_id='");
					queryBuffer.append(o_id + "'");
				}
				if(StringUtils.isNotBlank(w_id)) {
					queryBuffer.append(" and w_id='");
					queryBuffer.append(w_id + "'");
				}
				if(StringUtils.isNotBlank(d_id)) {
					queryBuffer.append(" and d_id='");
					queryBuffer.append(d_id + "'");
				}
				if(StringUtils.isNotBlank(c_giveBillflg)) {
					if("ÊÇ".equals(c_giveBillflg)) {
						queryBuffer.append(" and c_giveBillflg='ÊÇ'");
					} else if("·ñ".equals(c_giveBillflg)) {
						queryBuffer.append(" and c_giveBillflg='·ñ'");
					}
				}
				Query query = session.createQuery(queryBuffer.toString());
				return query.list();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}

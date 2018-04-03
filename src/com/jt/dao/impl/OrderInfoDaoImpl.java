package com.jt.dao.impl;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.jt.dao.OrderInfoDao;
import com.jt.domain.OrderInfo;
import com.jt.utils.SessionUtils;

public class OrderInfoDaoImpl implements OrderInfoDao {
	
	int SUCCESS = 1;

	@Override
	public int insertOrder(OrderInfo orderInfo) {
		try {
			Session session = SessionUtils.getCurrentSession();
			session.save(orderInfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateOrder(OrderInfo orderInfo) {
		try {
			Session session = SessionUtils.getCurrentSession();
			OrderInfo dbOrderInfo = (OrderInfo) session.get(OrderInfo.class, orderInfo.getO_id());
			dbOrderInfo.setO_price(orderInfo.getO_price());
			dbOrderInfo.setO_pay(orderInfo.getO_pay());
			dbOrderInfo.setO_serverTime(orderInfo.getO_serverTime());
			dbOrderInfo.setM_order(orderInfo.getM_order());
			dbOrderInfo.setWorker(orderInfo.getWorker());
			dbOrderInfo.setMenus(orderInfo.getMenus());
			dbOrderInfo.setDiningtable(orderInfo.getDiningtable());
			session.update(dbOrderInfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteOrder(String o_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			OrderInfo dbOrderInfo = (OrderInfo) session.get(OrderInfo.class, o_id);
			System.out.println("----dbOrderInfo----" + dbOrderInfo);
			session.delete(dbOrderInfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderInfo selectOrderById(String o_id) {
		try {
			Session session = SessionUtils.getCurrentSession();
			OrderInfo dbOrderInfo = (OrderInfo) session.get(OrderInfo.class, o_id);
			System.out.println("dbOrder: " + dbOrderInfo);
			if(dbOrderInfo != null) {
				return dbOrderInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderInfo> selectAllOrder() {
		try {
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from OrderInfo");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderInfo> selectOrderByCondition(String o_id, String d_id, String o_pay) {
		if(StringUtils.isBlank(o_id) && StringUtils.isBlank(d_id) && StringUtils.isBlank(o_pay)) {
			return selectAllOrder();
		} else {
			try {
				Session session = SessionUtils.getCurrentSession();
				String queryStr = "from OrderInfo where 1=1";
				StringBuffer queryBuffer = new StringBuffer(queryStr);
				if(StringUtils.isNotBlank(o_id)) {
					queryBuffer.append(" and o_id= '");
					queryBuffer.append(o_id + "'");
				}
				if(StringUtils.isNotBlank(d_id)) {
					queryBuffer.append(" and d_id= '");
					queryBuffer.append(d_id + "'");
				}
				if(StringUtils.isNotBlank(o_pay)) {
					if("ÊÇ".equals(o_pay)) {
						queryBuffer.append(" and o_pay='ÊÇ'");
					} else if ("·ñ".equals(o_pay)) {
						queryBuffer.append(" and o_pay='·ñ'");
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

	
	@Override
	public OrderInfo selectOrderByTable(String d_id, String o_pay) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from OrderInfo where d_id=:d_id and o_pay=:o_pay order by o_id DESC").setString("d_id", d_id).setString("o_pay", o_pay);
			query.setFirstResult(0);
			query.setMaxResults(1);
			System.out.println("query: " + query.uniqueResult());
			return (OrderInfo) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderInfo selectLastOrder() {
		try {
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from OrderInfo order by o_id DESC");
			query.setFirstResult(0);
			query.setMaxResults(1);
			System.out.println("query: " + query.uniqueResult());
			return (OrderInfo) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

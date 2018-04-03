package com.jt.service.impl;

import java.util.List;

import com.jt.dao.DiningtableDao;
import com.jt.dao.impl.DiningtableDaoImpl;
import com.jt.domain.Diningtable;
import com.jt.service.IDiningtableService;

public class DiningtableServiceImpl implements IDiningtableService {

	private DiningtableDao dao = new DiningtableDaoImpl();
	
	@Override
	public int addTable(Diningtable diningtable) {
		return dao.insertTable(diningtable);
	}

	@Override
	public int modifyTable(Diningtable diningtable) {
		return dao.updateTable(diningtable);
	}

	@Override
	public int removeTable(String d_id) {
		return dao.deleteTable(d_id);
	}

	@Override
	public Diningtable findTableById(String d_id) {
		return dao.selectTableById(d_id);
	}

	@Override
	public List<Diningtable> findAllTable() {
		return dao.selectAllTable();
	}

	@Override
	public List<Diningtable> findTableByCondition(String d_no, String d_status) {
		return dao.selectTableByCondition(d_no, d_status);
	}

}

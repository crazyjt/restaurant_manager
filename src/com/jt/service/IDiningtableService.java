package com.jt.service;

import java.util.List;

import com.jt.domain.Diningtable;

public interface IDiningtableService {
	
	/**
	 * ��Ӳ�����Ϣ
	 * @param diningtable
	 * @return
	 */
	int addTable(Diningtable diningtable);
	
	/**
	 * �޸Ĳ�����Ϣ
	 * @param diningtable
	 * @return
	 */
	int modifyTable(Diningtable diningtable);

	/**
	 * ɾ��������Ϣ
	 * @param d_id
	 * @return
	 */
	int removeTable(String d_id);
	
	/**
	 * ����id���Ҳ���
	 * @param d_id
	 * @return
	 */
	Diningtable findTableById(String d_id);
	
	/**
	 * �������в�����Ϣ
	 * @return
	 */
	List<Diningtable> findAllTable();
	
	/**
	 * �����������Ҳ�����Ϣ
	 * @param d_no
	 * @param d_status
	 * @return
	 */
	List<Diningtable> findTableByCondition(String d_no, String d_status);
}
	

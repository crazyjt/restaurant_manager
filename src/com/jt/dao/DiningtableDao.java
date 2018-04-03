package com.jt.dao;

import java.util.List;

import com.jt.domain.Diningtable;

public interface DiningtableDao {

	/**
	 * �������
	 * @param diningtable
	 * @return
	 */
	int insertTable(Diningtable diningtable);

	/**
	 * ���²���
	 * @param diningtable
	 * @return
	 */
	int updateTable(Diningtable diningtable);

	/**
	 * ɾ������
	 * @param d_id
	 * @return
	 */
	int deleteTable(String d_id);

	/**
	 * ����id��ѯ����
	 * @param d_id
	 * @return
	 */
	Diningtable selectTableById(String d_id);

	/**
	 * �������в���
	 * @return
	 */
	List<Diningtable> selectAllTable();

	/**
	 * �����������Ҳ���
	 * @param d_no
	 * @param d_status
	 * @return
	 */
	List<Diningtable> selectTableByCondition(String d_no, String d_status);

}

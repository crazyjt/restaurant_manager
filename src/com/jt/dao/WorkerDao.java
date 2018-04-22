package com.jt.dao;

import java.util.List;

import com.jt.domain.Worker;

public interface WorkerDao {

	/**
	 * �����û�id�������ѯ�û�
	 * @param w_id
	 * @param w_password
	 * @return
	 */
	Worker selectWorkerByInfo(String w_id, String w_password);

	/**
	 * ����û�
	 * @param worker
	 * @return
	 */
	int insertWorker(Worker worker);

	/**
	 * �����û���Ϣ
	 * @param worker
	 * @return
	 */
	int updateWorker(Worker worker);

	/**
	 * �����û�idɾ���û�
	 * @param w_id
	 * @return
	 */
	int deleteWorker(String w_id);

	/**
	 * �����û�id��ѯ�û���Ϣ
	 * @param w_id
	 * @return
	 */
	Worker selectWorkerById(String w_id);

	/**
	 * ���������û�
	 * @return
	 */
	List<Worker> selectAllWorker();

	/**
	 * �������������û�
	 * @param w_type
	 * @param w_sex
	 * @param w_workTime
	 * @param hasPic 
	 * @return
	 */
	List<Worker> selectWorkerByCondition(String w_type, String w_sex, String hasPic);

	Worker selectWorkerByName(String w_name);


}

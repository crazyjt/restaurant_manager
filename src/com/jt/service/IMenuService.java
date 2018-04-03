package com.jt.service;

import java.util.List;

import com.jt.domain.Menu;

public interface IMenuService {
	
	/**
	 * ��Ӳ˵�
	 * @param menu
	 * @return
	 */
	int addMenu(Menu menu);
	
	/**
	 * �޸Ĳ˵�
	 * @param menu
	 * @return
	 */
	int modifyMenu(Menu menu);
	
	/**
	 * ɾ���˵�
	 * @param m_id
	 * @return
	 */
	int removeMenu(String m_id);
	
	/**
	 * ����id���Ҳ˵�
	 * @param m_id
	 * @return
	 */
	Menu findMenuById(String m_id);
	
	/**
	 * �������в˵�
	 * @return
	 */
	List<Menu> findAllMenu();

	List<Menu> findMenuByCondition(String m_name, String m_type, String hasPic);
}

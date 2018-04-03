package com.jt.dao;

import java.util.List;

import com.jt.domain.Menu;

public interface MenuDao {
	
	/**
	 * ��Ӳ˵�
	 * @param menu
	 * @return
	 */
	int insertMenu(Menu menu);
	
	/**
	 * �޸Ĳ˵�
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);
	
	/**
	 * ɾ���˵�
	 * @param m_id
	 * @return
	 */
	int deleteMenu(String m_id);
	
	/**
	 * ����id��ѯ�˵�
	 * @param m_id
	 * @return
	 */
	Menu selectMenuById(String m_id);
	
	/**
	 * ��ѯ���в˵�
	 * @return
	 */
	List<Menu> selectAllMenu();

	List<Menu> selectMenuByCondition(String m_name, String m_type, String hasPic);
}

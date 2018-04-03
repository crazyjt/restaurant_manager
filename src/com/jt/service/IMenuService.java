package com.jt.service;

import java.util.List;

import com.jt.domain.Menu;

public interface IMenuService {
	
	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	int addMenu(Menu menu);
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	int modifyMenu(Menu menu);
	
	/**
	 * 删除菜单
	 * @param m_id
	 * @return
	 */
	int removeMenu(String m_id);
	
	/**
	 * 根据id查找菜单
	 * @param m_id
	 * @return
	 */
	Menu findMenuById(String m_id);
	
	/**
	 * 查找所有菜单
	 * @return
	 */
	List<Menu> findAllMenu();

	List<Menu> findMenuByCondition(String m_name, String m_type, String hasPic);
}

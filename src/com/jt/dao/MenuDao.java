package com.jt.dao;

import java.util.List;

import com.jt.domain.Menu;

public interface MenuDao {
	
	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	int insertMenu(Menu menu);
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);
	
	/**
	 * 删除菜单
	 * @param m_id
	 * @return
	 */
	int deleteMenu(String m_id);
	
	/**
	 * 根据id查询菜单
	 * @param m_id
	 * @return
	 */
	Menu selectMenuById(String m_id);
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Menu> selectAllMenu();

	List<Menu> selectMenuByCondition(String m_name, String m_type, String hasPic);
}

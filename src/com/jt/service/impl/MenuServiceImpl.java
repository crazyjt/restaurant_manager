package com.jt.service.impl;

import java.util.List;

import com.jt.dao.MenuDao;
import com.jt.dao.impl.MenuDaoImpl;
import com.jt.domain.Menu;
import com.jt.service.IMenuService;

public class MenuServiceImpl implements IMenuService {

	MenuDao dao = new MenuDaoImpl();
	
	@Override
	public int addMenu(Menu menu) {
		return dao.insertMenu(menu);
	}

	@Override
	public int modifyMenu(Menu menu) {
		return dao.updateMenu(menu);
	}

	@Override
	public int removeMenu(String m_id) {
		return dao.deleteMenu(m_id);
	}

	@Override
	public Menu findMenuById(String m_id) {
		return dao.selectMenuById(m_id);
	}

	@Override
	public List<Menu> findAllMenu() {
		return dao.selectAllMenu();
	}

	@Override
	public List<Menu> findMenuByCondition(String m_name, String m_type, String hasPic) {
		return dao.selectMenuByCondition(m_name, m_type, hasPic);
	}

}

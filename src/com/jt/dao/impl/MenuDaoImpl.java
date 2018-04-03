package com.jt.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.jt.dao.MenuDao;
import com.jt.domain.Menu;
import com.jt.utils.SessionUtils;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

public class MenuDaoImpl implements MenuDao {

	int SUCCESS = 1;
	
	@Override
	public int insertMenu(Menu menu) {
		try{
			Session session = SessionUtils.getCurrentSession();
			session.save(menu);
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMenu(Menu menu) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Menu dbMenu = (Menu) session.get(Menu.class, menu.getM_id());
			dbMenu.setM_name(menu.getM_name());
			dbMenu.setM_price(menu.getM_price());
			dbMenu.setM_inventory(menu.getM_inventory());
			dbMenu.setM_type(menu.getM_type());
			dbMenu.setM_path(menu.getM_path());
			dbMenu.setM_filename(menu.getM_filename());
			session.update(dbMenu);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int deleteMenu(String m_id) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Menu dbMenu = (Menu) session.get(Menu.class, m_id);
//			SQLQuery query = (SQLQuery) session.createSQLQuery("update m_o_table set m_id = '' where m_id = :m_id").setString("m_id", m_id);
			
			session.delete(dbMenu);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Menu selectMenuById(String m_id) {
		try{
			Session session = SessionUtils.getCurrentSession();
			Menu dbMenu = (Menu) session.get(Menu.class, m_id);
			if(dbMenu != null) {
				return dbMenu;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Menu> selectAllMenu() {
		try{
			Session session = SessionUtils.getCurrentSession();
			Query query = session.createQuery("from Menu");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Menu> selectMenuByCondition(String m_name, String m_type, String hasPic) {
		System.out.println("dao: " + m_name + m_type + hasPic);
		if(StringUtils.isBlank(m_name) && StringUtils.isBlank(m_type) && StringUtils.isBlank(hasPic)) {
			return selectAllMenu();
		} else {
			try{
				Session session = SessionUtils.getCurrentSession();
				String queryStr = "from Menu";
				StringBuffer queryBuffer = new StringBuffer(queryStr);
				queryBuffer.append(" where 1=1");
				if(StringUtils.isNotBlank(m_name)) {
					queryBuffer.append(" and m_name='");
					queryBuffer.append(m_name.toString() + "'");
				}
				if(StringUtils.isNotBlank(m_type)) {
					queryBuffer.append(" and m_type='");
					queryBuffer.append(m_type.toString() + "'");
				}
				if(StringUtils.isNotBlank(hasPic)) {
					if("true".equals(hasPic)) {
						queryBuffer.append(" and m_filename is not null ");
					} else {
						queryBuffer.append(" and m_filename is null ");
					}
				}
				Query query = session.createQuery(queryBuffer.toString());
				return query.list();
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}

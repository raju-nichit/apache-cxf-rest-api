package com.example.rest.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.rest.dto.UserDTO;

public class UserDaoImpl extends BaseDAO implements UserDao{

	@SuppressWarnings("unchecked")
	public List<UserDTO> getUserByAuthToken(String token) throws HibernateException {
		List<UserDTO> userDetailsList = null;
		try {
			Session session = getCurrentSession();
			userDetailsList = session.createCriteria(UserDTO.class).add(Restrictions.eq("authToken", token)).list();
		} catch (HibernateException exception) {
		}
		return userDetailsList;
	}
}

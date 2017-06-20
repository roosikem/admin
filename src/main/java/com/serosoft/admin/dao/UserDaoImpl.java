package com.serosoft.admin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serosoft.admin.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> userList() throws Exception{
		List<User> users=null;
		Session session=sessionFactory.openSession();
		users=session.createQuery(" From User ").list();
		return users;
	}

	@Override
	public void save(User user) throws Exception{
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		   session.merge(user);
		   trans.commit();
	}

	@Override
	public User getById(Long userId) throws Exception{
		return (User) sessionFactory.openSession().get(User.class, userId);
	}

	@Override
	public void deleteUser(Long userId) throws Exception{
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		 User user = (User) session.load(User.class, userId);
	        if (null != user) {
	            session.delete(user);
	            trans.commit();
	            session.close();
	            
	        }
		
	}

	@Override
	public User getByEmail(String email) throws Exception {
		Session session=sessionFactory.openSession();
		User user=(User)session.createQuery(" From User where email=:email ").setString("email", email).uniqueResult();
		return user;
	}
	
	

}

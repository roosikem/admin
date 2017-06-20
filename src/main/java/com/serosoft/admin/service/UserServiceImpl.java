package com.serosoft.admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serosoft.admin.dao.UserDao;
import com.serosoft.admin.generic.ServiceException;
import com.serosoft.admin.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public List<User> userList() throws ServiceException {
		try {
			return userDao.userList();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void save(User user) throws ServiceException {
		try {
			 userDao.save(user);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public User getById(Long userId) throws ServiceException {
		try {
			 return userDao.getById(userId);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void deleteUser(Long userId) throws ServiceException {
		try {
			  userDao.deleteUser(userId);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public User getByEmail(String email) throws ServiceException {
		try {
			 return userDao.getByEmail(email);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Map<String, User> getUserMap() throws ServiceException {
		List<User> users=this.userList();
		Map<String,User> userMap=new LinkedHashMap<String,User>();
		for(User u:users){
			userMap.put(u.getEmail(), u);
		}
		return userMap;
	}

}

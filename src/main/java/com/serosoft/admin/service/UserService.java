package com.serosoft.admin.service;

import java.util.List;
import java.util.Map;

import com.serosoft.admin.generic.ServiceException;
import com.serosoft.admin.model.User;

public interface UserService {
	public List<User> userList()throws ServiceException;
	public void save(User user)throws ServiceException;
	public User getById(Long userId)throws ServiceException;
	public User getByEmail(String email)throws ServiceException;
	public void deleteUser(Long userId)throws ServiceException;
	public Map<String, User> getUserMap()throws ServiceException;
}

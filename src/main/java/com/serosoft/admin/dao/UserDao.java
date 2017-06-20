package com.serosoft.admin.dao;

import java.util.List;

import com.serosoft.admin.model.User;

public interface UserDao {

	public List<User> userList()throws Exception;
	public void save(User user)throws Exception;
	public User getById(Long userId)throws Exception;
	public User getByEmail(String email)throws Exception;
	public void deleteUser(Long userId)throws Exception;
}

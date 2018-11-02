package com.uxpsystems.assignement.assignement.service;

import com.uxpsystems.assignement.assignement.dao.User;

public interface UserService {

	public Long createUser(User user);
	
	public Long updateUser(User user);
	
	public Long deleteUser(long userId);
	
	public User getUser(long userId);
}

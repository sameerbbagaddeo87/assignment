package com.uxpsystems.assignement.assignement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignement.assignement.dao.User;
import com.uxpsystems.assignement.assignement.dao.UserRepository;

@Service(value="userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Long createUser(User user) {
		User user1 = userRepository.save(user);
		return user1.getId();
	}

	@Override
	public Long updateUser(User user) {
		userRepository.save(user);
		return user.getId();
	}

	@Override
	public Long deleteUser(long userId) {
		userRepository.deleteById(userId);
		return userId;
	}

	@Override
	public User getUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.get();
	}

}

package com.uxpsystems.assignement.assignement.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{

	public <S extends User> S save(S entity);
	public Optional<User> findById(Long id);
	public void deleteById(Long id);
}

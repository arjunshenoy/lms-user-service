package com.germanium.lmsuserservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.germanium.lmsuserservice.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}

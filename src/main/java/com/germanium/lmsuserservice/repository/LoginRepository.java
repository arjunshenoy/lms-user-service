package com.germanium.lmsuserservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.germanium.lmsuserservice.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

	@Transactional
	@Modifying(clearAutomatically =  true)
	@Query("update Login u set u.password=?2 where u.userName=?1")
	public void updateUserPassword(String user, String password);

}

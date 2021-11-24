package com.germanium.lmsuserservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@PersistenceContext
	protected EntityManager manager;
	
	@Override
	public List<Integer> executeSqlQuery(String s) {
		Query fetchQuery = manager.createNativeQuery(s);
		return fetchQuery.getResultList();
	}

}

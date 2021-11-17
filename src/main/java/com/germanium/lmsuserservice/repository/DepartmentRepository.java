package com.germanium.lmsuserservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.germanium.lmsuserservice.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}

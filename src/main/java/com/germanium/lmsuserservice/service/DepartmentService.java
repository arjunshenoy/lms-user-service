package com.germanium.lmsuserservice.service;

import java.util.List;

import com.germanium.lmsuserservice.model.Department;



public interface DepartmentService {

	public Department createDepartment(Department department); 
	
	public List<Department> getDepartments();
	
	public Department updateDepartment(Integer departmentId, Department department) throws Exception;

	public boolean deleteDepartment(Integer departmentId) throws Exception;

	public Department findDepartmentById(Integer departmentId) throws Exception;

}

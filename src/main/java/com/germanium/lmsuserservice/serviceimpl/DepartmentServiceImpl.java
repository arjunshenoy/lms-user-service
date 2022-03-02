package com.germanium.lmsuserservice.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.Department;
import com.germanium.lmsuserservice.repository.DepartmentRepository;
import com.germanium.lmsuserservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	DepartmentRepository departmentRepo;
	
	@Override
	public Department createDepartment(Department department) {
		logger.info("Creating Department");
		return departmentRepo.save(department);	
	}

	@Override
	public List<Department> getDepartments() {
		logger.info("Getting the Department Details");
		return (List<Department>) departmentRepo.findAll();
	}
	
	@Override
	public Department findDepartmentById(Integer departmentId) throws ResourceNotFoundException {
		logger.info("Getting the Department Details for Department Id: "+departmentId);
		Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);
		if (!optionalDepartment.isPresent()) {
			throw new ResourceNotFoundException("Department With Department Id: "+ departmentId+ " Not Found");
		}
		return optionalDepartment.get();
	}

	@Override
	public Department updateDepartment(Integer departmentId, Department department) throws ResourceNotFoundException {
		if(!departmentRepo.existsById(departmentId)) {
			throw new ResourceNotFoundException("Department With Department Id: "+ departmentId+ " Not Found");
		}
		logger.info("Updating Department Details");
		return departmentRepo.save(department);
	}

	@Override
	public boolean deleteDepartment(Integer departmentId) throws ResourceNotFoundException {
		logger.info("Deleting Department Details");
		if (!departmentRepo.existsById(departmentId)) {
			throw new ResourceNotFoundException("Department With Department Id: "+ departmentId+ " Not Found");
		}
		departmentRepo.deleteById(departmentId);
		return true;
	}

	
}

package com.germanium.lmsuserservice.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.germanium.lmsuserservice.model.Department;
import com.germanium.lmsuserservice.service.DepartmentService;

public class DepartmentControllerTest {

	@Mock
	DepartmentService departmentService;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
	private DepartmentController departmentController;

	@Test(groups = "mocked")
	public void getDepartments_shouldReturnStatusCodeOK() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Department dept1 = new Department();

		dept1.setHeadId(1);
		dept1.setDepartmentName("CS");
		dept1.setDepartmentId(1);
		dept1.setCreatedBy("admin");
		dept1.setCreatedTs(timestamp);
		dept1.setUpdatedBy("admin");
		dept1.setUpdatedTs(timestamp);

		Department dept2 = new Department();

		dept2.setHeadId(2);
		dept2.setDepartmentName("EC");
		dept2.setDepartmentId(2);
		dept2.setCreatedBy("admin");
		dept2.setCreatedTs(timestamp);
		dept2.setUpdatedBy("admin");
		dept2.setUpdatedTs(timestamp);

		List<Department> deptList = List.of(dept1, dept2);

		when(departmentService.getDepartments()).thenReturn(deptList);
		var responseEntity = departmentController.getDepartment();

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

	}
	
	@Test(groups = "mocked")
	public void getDepartmentById_shouldReturnStatusCodeOK() throws Exception {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Department dept1 = new Department();
		dept1.setHeadId(1);
		dept1.setDepartmentName("CS");
		dept1.setDepartmentId(1);
		dept1.setCreatedBy("admin");
		dept1.setCreatedTs(timestamp);
		dept1.setUpdatedBy("admin");
		dept1.setUpdatedTs(timestamp);
		
		when(departmentService.findDepartmentById(isA(Integer.class))).thenReturn(dept1);
		var responseEntity = departmentController.getDepartment();
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test(groups ="mocked")
	public void updateDepartment_shouldReturnStatusCodeOK() throws Exception {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Department dept1 = new Department();
		dept1.setHeadId(1);
		dept1.setDepartmentName("CS");
		dept1.setDepartmentId(1);
		dept1.setCreatedBy("admin");
		dept1.setCreatedTs(timestamp);
		dept1.setUpdatedBy("admin");
		dept1.setUpdatedTs(timestamp);
		
		
		when(departmentService.updateDepartment(isA(Integer.class),isA(Department.class))).thenReturn(dept1);
		
		DepartmentController spy = Mockito.spy(departmentController);
		
		var responseEntity = spy.updateDepartment(1, dept1);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		
	}
	
	

}

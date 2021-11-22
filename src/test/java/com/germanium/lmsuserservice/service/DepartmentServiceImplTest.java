package com.germanium.lmsuserservice.service;

import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.Department;
import com.germanium.lmsuserservice.repository.DepartmentRepository;
import com.germanium.lmsuserservice.serviceImpl.DepartmentServiceImpl;

public class DepartmentServiceImplTest {

	@Mock
	DepartmentRepository departmentRepo;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "mocked")
	public void saveDepartment() {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Department dept1 = new Department();

		dept1.setHeadId(1);
		dept1.setDepartmentName("CS");
		dept1.setDepartmentId(1);
		dept1.setCreatedBy("admin");
		dept1.setCreatedTs(timestamp);
		dept1.setUpdatedBy("admin");
		dept1.setUpdatedTs(timestamp);

		when(departmentRepo.save(isA(Department.class))).thenReturn(dept1);
		Department response = departmentService.createDepartment(dept1);

		assertNotNull(response);
		assertEquals(response.getCreatedBy(), dept1.getCreatedBy());
		assertEquals(response.getCreatedTs(), dept1.getCreatedTs());
		assertEquals(response.getDepartmentId(), dept1.getDepartmentId());
		assertEquals(response.getDepartmentName(), dept1.getDepartmentName());
		assertEquals(response.getHeadId(), dept1.getHeadId());
		assertEquals(response.getUpdatedBy(), dept1.getUpdatedBy());
		assertEquals(response.getUpdatedTs(), dept1.getUpdatedTs());

	}

	@Test(groups = "mocked")
	public void getDeprtmentById() throws Exception {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Department dept1 = new Department();

		dept1.setHeadId(1);
		dept1.setDepartmentName("CS");
		dept1.setDepartmentId(1);
		dept1.setCreatedBy("admin");
		dept1.setCreatedTs(timestamp);
		dept1.setUpdatedBy("admin");
		dept1.setUpdatedTs(timestamp);

		Optional<Department> optionalUser = Optional.of(dept1);
		when(departmentRepo.findById(isA(Integer.class))).thenReturn(optionalUser);

		Department returnedUser = departmentService.findDepartmentById(1);

		assertNotNull(returnedUser);
		assertEquals(returnedUser.getDepartmentName(), "CS");
	}

	@Test(groups = "mocked", expectedExceptions = ResourceNotFoundException.class)
	public void getDepartmentById_ReturnsNotFoundException() throws ResourceNotFoundException {
		Optional<Department> optionalUser = Optional.empty();
		when(departmentRepo.findById(isA(Integer.class))).thenReturn(optionalUser);
		departmentService.findDepartmentById(1);
	}

	@Test(groups = "mocked")
	public void deleteDepartmentById() {

		when(departmentRepo.existsById(isA(Integer.class))).thenReturn(true);
		departmentService.deleteDepartment(1);
		ArgumentCaptor<Integer> userId = ArgumentCaptor.forClass(Integer.class);
		verify(departmentRepo).deleteById(userId.capture());
		assertEquals(userId.getValue(), Integer.valueOf(1));

	}

	@Test(groups = "mocked", expectedExceptions = ResourceNotFoundException.class)
	public void updateDepartment_ReturnsNotFoundException() {
		Department userProfile = new Department();
		when(departmentRepo.existsById(isA(Integer.class))).thenReturn(false);
		departmentService.updateDepartment(1, userProfile);
	}

	@Test(groups = "mocked")
	public void updateUserProfile_verifySave() {
		InOrder inOrder = Mockito.inOrder(departmentRepo);
		Department userProfile = new Department();
		when(departmentRepo.existsById(isA(Integer.class))).thenReturn(true);
		departmentService.updateDepartment(1, userProfile);

		inOrder.verify(departmentRepo).save(userProfile);
	}
}

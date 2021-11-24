package com.germanium.lmsuserservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.germanium.lmsuserservice.model.Department;
import com.germanium.lmsuserservice.service.DepartmentService;

@RestController
@RequestMapping(value = "/api/v1/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

	Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	DepartmentService departmentService;

	@PostMapping()
	public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
		logger.info("Request received for creating department");
		Department departmentDetails = departmentService.createDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION).body(departmentDetails);
	}

	@GetMapping()
	public ResponseEntity<List<Department>> getDepartment() {
		logger.info("Request received for getting department details");
		return ResponseEntity.ok().body(departmentService.getDepartments());
	}

	@GetMapping("{departmentId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("departmentId") Integer departmentId)
			throws Exception {
		logger.info("Request received for getting details of department ID : {}", departmentId);
		return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId));
	}

	@PutMapping("{departmentId}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("departmentId") final Integer departmentId,
			@Valid @RequestBody Department department) throws Exception {
		logger.info("Update request received for department ID : {}", departmentId);

		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION)
				.body(departmentService.updateDepartment(departmentId, department));

	}

	@DeleteMapping("{departmentId}")
	public ResponseEntity<Boolean> deleteDepartment(@PathVariable("departmentId") Integer departmentId) throws Exception {
		logger.info("Delete request received for department ID : {}", departmentId);
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION)
				.body(departmentService.deleteDepartment(departmentId));
	}
	
	@GetMapping("{departmentId}/workHours")
	public ResponseEntity<Float> getDepartmentWorkHours(@PathVariable("departmentId") Integer departmentId) throws Exception {
		logger.info("Request received for getting work hours of department ID : {}", departmentId);
		return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId).getWorkingHours());
	}
	
	@PutMapping("{departmentId}/workHours")
	public ResponseEntity<?> updateDepartmentWorkHours (@PathVariable("departmentId") final Integer departmentId,
			@Valid @RequestBody float workHours) throws Exception{
		logger.info("Update request received for department work hours ID : {}", departmentId);
		Department dep = departmentService.findDepartmentById(departmentId);
		dep.setWorkingHours(workHours);
		departmentService.updateDepartment(departmentId, dep);		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION).body("Department Details updated Successfully");
		
	}
	
	@GetMapping("{departmentId}/workEmployees")
	public ResponseEntity<Float> getDepartmentWorkEmployees(@PathVariable("departmentId") Integer departmentId) throws Exception {
		logger.info("Request received for getting working employees needed by department ID : {}", departmentId);
		return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId).getWorkingEmployees());
	}
	
	@PutMapping("{departmentId}/workEmployees")
	public ResponseEntity<?> updateDepartmentWorkEmployees(@PathVariable("departmentId") final Integer departmentId,
			@Valid @RequestBody float workEmployees) throws Exception{
		logger.info("Update request received for department working employee count ID : {}", departmentId);
		Department dep = departmentService.findDepartmentById(departmentId);
		dep.setWorkingEmployees(workEmployees);
		departmentService.updateDepartment(departmentId, dep);		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION).body("Department Details updated Successfully");	
	}
	
	@GetMapping("{departmentId}/leavequeue")
	public ResponseEntity<Integer> getDepartmentLeaveQueue(@PathVariable("departmentId") Integer departmentId) throws Exception {
		logger.info("Request received for getting working employees needed by department ID : {}", departmentId);
		return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId).getLeaveQueueing());
	}
	
	@PutMapping("{departmentId}/leavequeue")
	public ResponseEntity<?> updateDepartmentLeaveQueue(@PathVariable("departmentId") final Integer departmentId,
			@Valid @RequestBody int leaveQueue) throws Exception{
		logger.info("Update request received for department work hours ID : {}", departmentId);
		Department dep = departmentService.findDepartmentById(departmentId);
		dep.setLeaveQueueing(leaveQueue);
		departmentService.updateDepartment(departmentId, dep);		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION).body("Department Details updated Successfully");	
	}
}
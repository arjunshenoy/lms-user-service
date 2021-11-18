package com.germanium.lmsuserservice.model;

// @author: Chinmay Jose K M

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "Department")
@ApiModel(value = "Department", description = "Class that holds the Department details")
public class Department extends SystemProperties{
	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "departmentId")
	private Integer departmentId;

	@Column(name = "department_name")
	@ApiModelProperty(value = "departmentName")
	private String departmentName;
	
	@Column(name = "head_id")
	@ApiModelProperty(value = "headId")
	private Integer headId;
	
	@Column(name = "working_hours_per_day")
	@ApiModelProperty(value = "workingHours")
	private float workingHours;
	
	@Column(name = "working_employees_per_day")
	@ApiModelProperty(value = "workingEmployees")
	private float workingEmployees;
	
	@Column(name = "leave_request_queueing")
	@ApiModelProperty(value = "leaveQueueing")
	private int leaveQueueing;

	public float getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(float workingHours) {
		this.workingHours = workingHours;
	}

	public float getWorkingEmployees() {
		return workingEmployees;
	}

	public void setWorkingEmployees(float workingEmployees) {
		this.workingEmployees = workingEmployees;
	}

	public int getLeaveQueueing() {
		return leaveQueueing;
	}

	public void setLeaveQueueing(int leaveQueueing) {
		this.leaveQueueing = leaveQueueing;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}
}
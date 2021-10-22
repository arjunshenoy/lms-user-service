package com.germanium.lmsuserservice.model.dto;

import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;

public class ImportUserDTO {

	//@CsvBindByPosition(position = 0)
	//private String employeeId;

	@CsvBindByPosition(position = 1)
	private String firstName;

	@CsvBindByPosition(position = 2)
	private String middleName;

	@CsvBindByPosition(position = 3)
	private String lastName;
	
	@CsvBindByPosition(position = 4)
	private String phoneNumber;
	
	@CsvBindByPosition(position = 5)
	private String gender;

	@CsvBindByPosition(position = 6)
	private String dob;
	
	@CsvBindByPosition(position = 7)
	private String dateOfJoining;
	
	@CsvBindByPosition(position = 8)
	private String departmentId;
	
	@CsvBindByPosition(position = 9)
	private String role;
	
	@CsvBindByPosition(position = 10)
	private String address;
	
	@CsvBindByPosition(position = 11)
	private String isActive;
	
	@CsvBindByPosition(position = 12)
	private String isPermanent;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsPermanent() {
		return isPermanent;
	}

	public void setPermanent(String isPermanent) {
		this.isPermanent = isPermanent;
	}
	
	

}

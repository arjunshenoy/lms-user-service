package com.germanium.lmsuserservice.model;

// @author: Chinmay Jose K M

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@Entity
@Table(name = "User")
@ApiModel(value = "User", description = "Class that holds the User details")
public class User extends SystemProperties {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "employeeId")
	private int employeeId;

	@Column(name = "first_name")
	@ApiModelProperty(value = "firstName")
	private String firstName;

	@Column(name = "middle_name")
	@ApiModelProperty(value = "middleName")
	private String middleName;

	@Column(name = "last_name")
	@ApiModelProperty(value = "lastName")
	private String lastName;

	@Column(name = "phone_number")
	@ApiModelProperty(value = "phoneNumber")
	private String phoneNumber;
	
	@Column(name ="email")
	@ApiModelProperty(value = "email")
	private String email;

	@Column(name = "gender")
	@ApiModelProperty(value = "phoneNumber")
	private String gender;

	@Column(name = "dob")
	@ApiModelProperty(value = "dob")
	private Date dob;

	@Column(name = "date_of_joining")
	@ApiModelProperty(value = "dateOfJoining")
	private Date dateOfJoining;

	@Column(name = "is_permanent")
	@ApiModelProperty(value = "isPermanent")
	private boolean isPermanent;

	@OneToOne
	@JoinColumn(name = "department_id")
	@Transient
	private Department department;

	@ApiModelProperty(value = "departmentId")
	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "role")
	@ApiModelProperty(value = "role")
	private String role;

	@Column(name = "address")
	@ApiModelProperty(value = "address")
	private String address;

	@Column(name = "is_active")
	@ApiModelProperty(value = "isActive")
	private boolean isActive;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public boolean isPermanent() {
		return isPermanent;
	}

	public void setPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	

}
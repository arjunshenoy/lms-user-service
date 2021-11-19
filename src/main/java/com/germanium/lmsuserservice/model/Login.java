package com.germanium.lmsuserservice.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Ajin Pius Michel
 */

@Entity
@Table(name = "login")
@ApiModel(value = "Login", description = "Class that holds the login details of Users")
public class Login {


	@Column(name = "employee_id")
	@NotNull
	@ApiModelProperty(value = "employeeId")
	private int id;

	@Id
	@Column(name = "username")
	@NotNull
	@ApiModelProperty(value = "userName")
	private String userName;

	@Column(name = "password")
	@ApiModelProperty(value = "password")
	private String password;

	@Column(name = "active")
	@ApiModelProperty(value = "active")
	private Boolean active;

	@Column(name = "roles")
	@ApiModelProperty(value = "roles")
	private String roles;

	@Column(name = "created_ts")
	@CreationTimestamp
	@ApiModelProperty(value = "createdTs")
	private Timestamp createdTs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setaActive(Boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

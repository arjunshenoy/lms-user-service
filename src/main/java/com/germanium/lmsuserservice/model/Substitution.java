package com.germanium.lmsuserservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
*
* @author Arjun Krishna Shenoy
*/

@Entity
@Table(name = "substitution")
@ApiModel(value = "Substitution", description = "This class holds the substitute user details")
@Getter
@Setter
public class Substitution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "substitutionId")
	@Column(name = "substitute_id")
	private int substitutionId;

	@ApiModelProperty(value = "leaveId")
	@Column(name = "leave_id")
	private int leaveId;

	@ApiModelProperty(value = "dateOfApproval")
	@Column(name = "date_of_approval")
	private Date dateOfApproval;
	
}

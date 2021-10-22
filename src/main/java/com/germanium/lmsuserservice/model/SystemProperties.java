package com.germanium.lmsuserservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Arjun Krishna Shenoy
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SystemProperties {

	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	protected String createdBy;

	@CreatedDate
	@Column(name = "created_timestamp", nullable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdTs;

	@LastModifiedBy
	@Column(name = "updated_by", nullable = true, updatable = true)
	protected String updatedBy;

	@LastModifiedDate
	@Column(name = "updated_timestamp", nullable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedTs;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
	

}

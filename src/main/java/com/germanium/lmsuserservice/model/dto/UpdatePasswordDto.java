package com.germanium.lmsuserservice.model.dto;


public class UpdatePasswordDto {

	private String oldPassword;
	private String newPassword;
	private String username;

	public UpdatePasswordDto() {

	}

	public UpdatePasswordDto(Builder builder) {
		this.oldPassword = builder.oldPassword;
		this.newPassword = builder.newPassword;
		this.username = builder.username;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String oldPassword;
		private String newPassword;
		private String username;

		public Builder oldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
			return this;
		}

		public Builder newPassword(String newPassword) {
			this.newPassword = newPassword;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public UpdatePasswordDto build() {
			return new UpdatePasswordDto(this);
		}
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

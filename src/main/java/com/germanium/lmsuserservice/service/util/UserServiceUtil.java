package com.germanium.lmsuserservice.service.util;

public class UserServiceUtil {

	public static final String SEL_USER = "SELECT employee_id FROM user";

	//Right now we support only query on user class we need to add department as well - Streach Goal
	public static String generateDynamicSQL(String s) {

		return new StringBuilder(SEL_USER).append(" WHERE ").append(s).toString();

	}
}

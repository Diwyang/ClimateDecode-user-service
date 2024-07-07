package com.climate.decode.userservice.enums;

public enum RoleType {
	USER (Names.USER), ADMIN (Names.ADMIN);
	
	public static class Names {
		
		public static final String USER = "USER";
		public static final String ADMIN = "ADMIN";
		
	}

	private final String roleType;

	private RoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return this.roleType;
	}
	
}

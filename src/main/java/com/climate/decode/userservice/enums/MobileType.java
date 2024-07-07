package com.climate.decode.userservice.enums;

public enum MobileType {
	PERSONAL(Names.PERSONAL), WORK(Names.WORK), OTHER(Names.OTHER);
	
	public static class Names {
		public static final String WORK = "WORK";
		public static final String PERSONAL = "PERSONAL";
		public static final String OTHER = "OTHER";
	}

	private final String mobileType;

	private MobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	@Override
	public String toString() {
		return this.mobileType;
	}
}

package com.climate.decode.userservice.enums;

public enum AddressType {
	WORK(Names.WORK), HOME(Names.HOME), OTHER(Names.OTHER) ;
	
	public static class Names {
		public static final String WORK = "WORK";
		public static final String HOME = "HOME";
		public static final String OTHER = "OTHER";
	}

	private final String addressType;

	private AddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return this.addressType;
	}
}

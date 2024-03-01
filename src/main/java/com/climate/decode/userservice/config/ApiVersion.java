package com.climate.decode.userservice.config;

public enum ApiVersion {

	 V_1_0(Constants.V_1_0);
	
    ApiVersion(String version) {
        if (!version.equals(this.name()))
            throw new IllegalArgumentException();
    }
    public static class Constants {
        public static final String V_1_0 = "v.1.0";
    }
}

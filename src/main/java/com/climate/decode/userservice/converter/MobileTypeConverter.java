package com.climate.decode.userservice.converter;


import org.dozer.CustomConverter;

import com.climate.decode.userservice.enums.MobileType;

public class MobileTypeConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }
        if (sourceFieldValue instanceof String) {
            return MobileType.valueOf((String) sourceFieldValue);
        } else if (sourceFieldValue instanceof MobileType) {
            return ((MobileType) sourceFieldValue).name();
        }
        throw new IllegalArgumentException("Unsupported conversion type");
    }
}


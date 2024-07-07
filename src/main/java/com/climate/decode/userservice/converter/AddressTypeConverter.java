package com.climate.decode.userservice.converter;


import org.dozer.CustomConverter;

import com.climate.decode.userservice.enums.AddressType;

public class AddressTypeConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }
        if (sourceFieldValue instanceof String) {
            return AddressType.valueOf((String) sourceFieldValue);
        } else if (sourceFieldValue instanceof AddressType) {
            return ((AddressType) sourceFieldValue).name();
        }
        throw new IllegalArgumentException("Unsupported conversion type");
    }
}


package com.climate.decode.userservice.converter;

import org.dozer.CustomConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConverter implements CustomConverter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        if (source instanceof String) {
            return ZonedDateTime.parse((String) source, FORMATTER);
        } else if (source instanceof ZonedDateTime) {
            return FORMATTER.format((ZonedDateTime) source);
        }

        throw new IllegalArgumentException("Unsupported source type: " + sourceClass.getName());
    }
}

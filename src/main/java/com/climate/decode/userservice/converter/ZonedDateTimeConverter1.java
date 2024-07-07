package com.climate.decode.userservice.converter;

import org.dozer.DozerConverter;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ZonedDateTimeConverter1 extends DozerConverter<ZonedDateTime, ZonedDateTime> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public ZonedDateTimeConverter1() {
        super(ZonedDateTime.class, ZonedDateTime.class);
        log.info("Initialize ZonedDateTimeConverter......");
    }

    @Override
    public ZonedDateTime convertTo(ZonedDateTime source, ZonedDateTime destination) {
        if (source == null) {
            return null;
        }
        log.info("ZonedDateTimeConverter :: convertTo");
        return source;
    }

    @Override
    public ZonedDateTime convertFrom(ZonedDateTime source, ZonedDateTime destination) {
        if (source == null) {
            return null;
        }
        log.info("ZonedDateTimeConverter :: convertFrom");
        return source;
    }
    
}

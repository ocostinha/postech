package com.fiap.pos.tech.challenge.mappers.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class MappingUtils {
    public static final String GENERATE_UUID_EXPRESSION =
            "java(com.fiap.pos.tech.challenge.mappers.utils.MappingUtils.generateUuid())";
    public static final String LOCAL_DATE_TIME_NOW =
            "java(com.fiap.pos.tech.challenge.mappers.utils.MappingUtils.localDateTimeNow())";


    public static UUID generateUuid() {
        return UUID.randomUUID();
    }

    public static LocalDateTime localDateTimeNow() {
        return LocalDateTime.now();
    }
}

package org.ssk.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * title        : 시간 유틸 클래스
 * author       : sim
 * date         : 2023-11-27
 * description  :
 */
public class TimeUtil {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static String getCurrentTime(){
        return LocalDateTime.now().format(TIME_FORMATTER);
    }
}

package com.xk.common.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DateCoverUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    public  Date StringCoverToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 將字串轉換程 ZonedDateTime
     * @param time
     * @return ZonedDateTime
     */
    public  ZonedDateTime parseZdt(String time) {
        if (time == null || time.isBlank()) {
            throw new IllegalArgumentException("bindTime/unbindTime 不能為空");
        }
        try {
            return ZonedDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            return ZonedDateTime.now();
        }
    }
}

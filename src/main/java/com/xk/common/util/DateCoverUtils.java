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


    public  Date StringCoverToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);
        return date;
    }

    public  ZonedDateTime parseZdt(String s, ZoneId defaultZone) {
        if (s == null) throw new IllegalArgumentException("input is null");
        String in = s.trim();
        if (in.isEmpty()) throw new IllegalArgumentException("input is blank");

        // 數字：Epoch 秒或毫秒
        if (in.matches("^\\d{10}$")) {                 // 10 位：秒
            return Instant.ofEpochSecond(Long.parseLong(in)).atZone(defaultZone);
        }
        if (in.matches("^\\d{13}$")) {                 // 13 位：毫秒
            return Instant.ofEpochMilli(Long.parseLong(in)).atZone(defaultZone);
        }

        // 先試有時區的格式
        try { // 例：2024-12-06T10:15:30+08:00[Asia/Taipei]
            return ZonedDateTime.parse(in, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException ignore) {}

        try { // 例：2024-12-06T10:15:30+08:00（只有 offset）
            OffsetDateTime odt = OffsetDateTime.parse(in, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return odt.toInstant().atZone(defaultZone);
        } catch (DateTimeParseException ignore) {}

        // 沒帶時區 → 視為 local，套 defaultZone
        List<DateTimeFormatter> localDateTimeFormats = new ArrayList<>();
        localDateTimeFormats.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);                // 2024-12-06T10:15:30
        localDateTimeFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));   // 2024-12-06 10:15:30
        localDateTimeFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));   // 2024/12/06 10:15:30
        localDateTimeFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        localDateTimeFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));

        for (DateTimeFormatter f : localDateTimeFormats) {
            try {
                LocalDateTime ldt = LocalDateTime.parse(in, f);
                return ldt.atZone(defaultZone);
            } catch (DateTimeParseException ignore) {}
        }

        // 只有日期 → 當天 00:00
        List<DateTimeFormatter> dateOnlyFormats = List.of(
                DateTimeFormatter.ISO_LOCAL_DATE,                       // 2024-12-06
                DateTimeFormatter.ofPattern("yyyy/MM/dd")               // 2024/12/06
        );
        for (DateTimeFormatter f : dateOnlyFormats) {
            try {
                LocalDate d = LocalDate.parse(in, f);
                return d.atStartOfDay(defaultZone);
            } catch (DateTimeParseException ignore) {}
        }

        throw new IllegalArgumentException("Unsupported datetime format: \"" + s + "\"");
    }
}

package utils;

import java.time.LocalDate;
import java.sql.Date;

public class DateUtil {
    public static Date asDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
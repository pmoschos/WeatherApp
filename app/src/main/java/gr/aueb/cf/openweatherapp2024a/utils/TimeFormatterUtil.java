package gr.aueb.cf.openweatherapp2024a.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeFormatterUtil {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    private TimeFormatterUtil() {}

    public static String getFormattedTime(long unixTimestamp) {
        return Instant.ofEpochSecond(unixTimestamp)
                .atZone(ZoneId.systemDefault())
                .format(TIME_FORMATTER);
    }
}

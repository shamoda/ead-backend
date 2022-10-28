package com.sliit.ead.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
public class DateTimeUtil {
    public static String getDateAsString(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return dateTime.format(format);
        }
        return null;
    }
}

package logic.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean isValidPeriod(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null &&
                !startDate.isBefore(LocalDate.now()) &&
                !endDate.isBefore(startDate);
    }
}

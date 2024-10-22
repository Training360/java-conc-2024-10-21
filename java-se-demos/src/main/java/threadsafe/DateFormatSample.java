package threadsafe;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@BadPractice("SimpleDateFormat not thread safe")
@Slf4j
public class DateFormatSample {

    /*
     1. megoldás: lokális változóba -
     1.b. megoldás: ThreadLocal
     2. megoldás: LocalDateTime, DateTimeFormatter immutable and thread-safe
     */

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    public Date save(String s) {

       log.debug("Objects: {} {}", s, dateFormat);
       Date date = dateFormat.parse(s);
       return date;
    }
}

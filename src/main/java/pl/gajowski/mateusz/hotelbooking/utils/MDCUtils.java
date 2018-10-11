package pl.gajowski.mateusz.hotelbooking.utils;

import org.slf4j.MDC;

public class MDCUtils {
    public static final String SUBJECT = "SUBJECT";
    public static final String SOURCE = "SOURCE";

    public static void put(String key, String obj) {
        if (obj != null) {
            MDC.put(key, obj);
        }
    }

    public static void put(String key, String val, Object obj) {
        String values = MDC.get(key);
        if (values == null) {
            values = "";
        }

        if (obj != null) {
            values = values.concat(val + "=" + obj + "|");
            MDC.put(key, values);
        }
    }
}

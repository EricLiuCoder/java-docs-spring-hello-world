package com.pcc.carrental.util;

import java.sql.Date;

public class DateTimeUtil {

    public static Date newDate() {
        return new Date(System.currentTimeMillis());
    }
}

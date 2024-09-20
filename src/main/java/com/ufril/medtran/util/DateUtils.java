package com.ufril.medtran.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by moin on 11/6/15.
 */
public class DateUtils {

    public static Date getTodayStart() {
        final TimeZone timeZone = TimeZone.getDefault();
        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(timeZone);// forID( "America/Montreal" );
        DateTime now = DateTime.now(dateTimeZone);
        DateTime todayStart = now.withTimeAtStartOfDay();
        return todayStart.toDate();
    }

    public static Date getTomorrowStart() {
        final TimeZone timeZone = TimeZone.getDefault();
        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(timeZone);// forID( "America/Montreal" );
        DateTime now = DateTime.now(dateTimeZone);
        DateTime tomorrowStart = now.plusDays( 1 ).withTimeAtStartOfDay();
        return tomorrowStart.toDate();
    }

    public static String getServerTimeZoneDisplayName(Locale locale) {
        final TimeZone timeZone = TimeZone.getDefault();
        final boolean daylight = timeZone.inDaylightTime(new Date());
        return timeZone.getDisplayName(daylight, TimeZone.LONG, locale);
    }

    /**
     * It take a date as parameter and return true if the date expired in respect
     * with the current date and time, otherwise return false
     * @param expiryDate
     * @return
     */
    public static boolean isExpired(Date expiryDate) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        final Calendar expire = Calendar.getInstance();
        expire.setTimeInMillis(expiryDate.getTime());
        return cal.after(expire);
    }

    public static boolean isGreaterThen(Date first, Date second) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(first.getTime());
        final Calendar expire = Calendar.getInstance();
        expire.setTimeInMillis(second.getTime());
        return cal.after(expire);
    }

    public static Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}

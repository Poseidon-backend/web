package com.proger4.webproger4.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateCalculator {

    private static final Logger log = LoggerFactory.getLogger(DateCalculator.class);

    public static String addDates(Date a, Date b) {
        Calendar first = Calendar.getInstance();
        Calendar second = Calendar.getInstance();
            first.setTime(a);
            second.setTime(b);

        int yearDiff = first.get(Calendar.YEAR) + second.get(Calendar.YEAR);
        int monthDiff = (first.get(Calendar.MONTH) + 1) + (second.get(Calendar.MONTH) + 1);
        int dayDiff = first.get(Calendar.DAY_OF_MONTH) + second.get(Calendar.DAY_OF_MONTH);
        while (dayDiff > first.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            dayDiff -= first.getActualMaximum(Calendar.DAY_OF_MONTH);
            monthDiff++;
            first.add(Calendar.MONTH, 1);
        }

        while (monthDiff > 12) {
            yearDiff++;
            monthDiff -= 12;
        }
        return dayDiff + " Day " + monthDiff + " Month " + yearDiff + " Year ";
    }

    public static String subtractDates(Date a, Date b) {
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        if (a.compareTo(b) < 0) {
            earlier.setTime(a);
            later.setTime(b);
        } else {
            earlier.setTime(b);
            later.setTime(a);
        }
        int yearDiff = later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR);
        int monthDiff = later.get(Calendar.MONTH) - earlier.get(Calendar.MONTH);
        int dayDiff = later.get(Calendar.DAY_OF_MONTH) - earlier.get(Calendar.DAY_OF_MONTH);

        if (dayDiff < 0) {
            dayDiff = earlier.getActualMaximum(Calendar.DAY_OF_MONTH) + dayDiff;
            earlier.add(Calendar.MONTH, -1);
            monthDiff--;
        }

        if (monthDiff <= 0) {
            monthDiff += 12;
            yearDiff--;
        }
        if (a.compareTo(b) < 0) {
            return "- " + Math.abs(dayDiff) + " Day " + Math.abs(monthDiff) + " Month " + Math.abs(yearDiff) + " Year "; }
        else {
            return Math.abs(dayDiff) + " Day " + Math.abs(monthDiff) + " Month " + Math.abs(yearDiff) + " Year ";
        }
            }

    public LocalDate nextWorkingDay(LocalDate date) {
        validateDate(date);
        LocalDate nextDay = date.plusDays(1);
        while (nextDay.getDayOfWeek().getValue() >= 6) {
            nextDay = nextDay.plusDays(1);
        }
        return nextDay;
    }

    private void validateDate(LocalDate date) {
        if (date == null) {
            log.error("Date cannot be null");
            throw new IllegalArgumentException("Date cannot be null");
        }

        if (date.getYear() < 1) {
            log.error("Date must be in the valid range (year >= 1)");
            throw new IllegalArgumentException("Date must be in the valid range (year >= 1)");
        }
    }

}
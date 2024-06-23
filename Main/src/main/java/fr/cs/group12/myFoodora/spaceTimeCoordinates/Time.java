package fr.cs.group12.myFoodora.spaceTimeCoordinates;

import java.time.LocalDateTime;
/**
 * The Time class represents a point in time with specific attributes including day, month, year, hour, minute, and second.
 * It provides methods for getting the current time, determining the start and end of previous months, and comparing time instances.
 */
public class Time {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;

 
    /**
     * Constructs a new Time object with the specified day, month, year, hour, minute, and second.
     *
     * @param day    The day component of the time.
     * @param month  The month component of the time.
     * @param year   The year component of the time.
     * @param hour   The hour component of the time.
     * @param minute The minute component of the time.
     * @param second The second component of the time.
     */
    public Time(int day, int month, int year, int hour, int minute, int second) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    /**
     * Constructs a new Time object with the specified day, month, year, and set the rest to 0.
     *
     * @param day    The day component of the time.
     * @param month  The month component of the time.
     * @param year   The year component of the time.
     */
    public Time(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    /**
     * Returns the day component of the time.
     *
     * @return The day component of the time.
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day component of the time.
     *
     * @param day The day component of the time.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Returns the month component of the time.
     *
     * @return The month component of the time.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month component of the time.
     *
     * @param month The month component of the time.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Returns the year component of the time.
     *
     * @return The year component of the time.
     */
    public int getYear() {
        return year;
    }
    /**
     * Sets the year component of the time.
     *
     * @param year The year component of the time.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the hour component of the time.
     *
     * @return The hour component of the time.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the hour component of the time.
     *
     * @param hour The hour component of the time.
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Returns the minute component of the time.
     *
     * @return The minute component of the time.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the minute component of the time.
     *
     * @param minute The minute component of the time.
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Returns the second component of the time.
     *
     * @return The second component of the time.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Sets the second component of the time.
     *
     * @param second The second component of the time.
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * Returns a new Time object representing the current system time.
     *
     * @return A Time object representing the current system time.
     */
    public static Time getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return new Time(day, month, year, hour, minute, second);
    }

    /**
     * Returns the time representing the start of the current month.
     *
     * @return A Time object representing the start of the current month.
     */
    public Time getDebutOfMonth() {
        return new Time(1, month, year, 0, 0, 0);
    }

    /**
     * Returns the time representing the start of the previous month.
     *
     * @return A Time object representing the start of the previous month.
     */
    public Time getStartOfPreviousMonth() {
        int previousMonth = this.month - 1;
        int previousYear = this.year;
        if (previousMonth == 0) {
            previousMonth = 12;
            previousYear--;
        }
        return new Time(1, previousMonth, previousYear, 0, 0, 0);
    }

    /**
     * Returns the time representing the end of the previous month.
     *
     * @return A Time object representing the end of the previous month.
     */
    public Time getEndOfPreviousMonth() {
        int previousMonth = this.month - 1;
        int previousYear = this.year;
        if (previousMonth == 0) {
            previousMonth = 12;
            previousYear--;
        }

        int lastDayOfPreviousMonth;
        switch (previousMonth) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                lastDayOfPreviousMonth = 31;
                break;
            case 4: case 6: case 9: case 11:
                lastDayOfPreviousMonth = 30;
                break;
            case 2:
                if ((previousYear % 4 == 0 && previousYear % 100 != 0) || (previousYear % 400 == 0)) {
                    lastDayOfPreviousMonth = 29; // Leap year
                } else {
                    lastDayOfPreviousMonth = 28;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid month: " + previousMonth);
        }

        return new Time(lastDayOfPreviousMonth,previousMonth,previousYear, 23, 59, 59);
    }
    // Comparison of dates ignoring null or undeclared fields

    /**
     * Checks if this time is before another time.
     *
     * @param otherTime The time to compare with.
     * @return true if this time is before the other time, false otherwise.
     */
    public boolean isBefore(Time otherTime) {
        if (otherTime == null) {
            return false; // If the other time is null, consider this time is not before null time
        }

        if (year != otherTime.year) {
            return year < otherTime.year;
        }

        if (month != otherTime.month) {
            return month < otherTime.month;
        }

        if (day != otherTime.day) {
            return day < otherTime.day;
        }

        if (hour != otherTime.hour) {
            return hour < otherTime.hour;
        }

        if (minute != otherTime.minute) {
            return minute < otherTime.minute;
        }

        return second < otherTime.second;
    }

    /**
     * Checks if this time is after another time.
     *
     * @param otherTime The time to compare with.
     * @return true if this time is after the other time, false otherwise.
     */
    public boolean isAfter(Time otherTime) {
        return otherTime != null && !isEqual(otherTime) && !isBefore(otherTime);
    }

    /**
     * Checks if this time is equal to another time.
     *
     * @param otherTime The time to compare with.
     * @return true if this time is equal to the other time, false otherwise.
     */
    public boolean isEqual(Time otherTime) {
        if (otherTime == null) {
            return false; // If the other time is null, consider it as not equal
        }
        return year == otherTime.year &&
                month == otherTime.month &&
                day == otherTime.day &&
                hour == otherTime.hour &&
                minute == otherTime.minute &&
                second == otherTime.second;
    }

    /**
     * Returns a string representation of this time.
     *
     * @return A string representation of this time in the format "dd/MM/yyyy HH:mm:ss".
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d %02d:%02d:%02d", day, month, year, hour, minute, second);
    }


}


package utils;

import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Fecha {
    public Fecha() {
    }

    private static LocalDateTime getCurrentLocalDateTime() {
        LocalDateTime cd = LocalDateTime.now();
        return cd;
    }

    public static Date getCurrentDate() {
        LocalDateTime cd = Fecha.getCurrentLocalDateTime();
        return new Date(cd.getYear() - 1900, cd.getMonthValue() - 1, cd.getDayOfMonth());
    }

    public static Timestamp getCurrentTimeStamp() {
        LocalDateTime cd = Fecha.getCurrentLocalDateTime();
        return new Timestamp(cd.getYear() - 1900,
                cd.getMonthValue() - 1,
                cd.getDayOfMonth(), cd.getHour(),
                cd.getMinute(),
                cd.getSecond(),
                cd.getNano());
    }

    public Date getDate(int year, int month, int day) {
        return new Date(year - 1900, month - 1, day);
    }

    public static void main(String[] args) {
        System.out.println("Fecha actual Date: " + Fecha.getCurrentDate());
        System.out.println("Fecha actual TimeStamp: " + Fecha.getCurrentTimeStamp());
    }
}

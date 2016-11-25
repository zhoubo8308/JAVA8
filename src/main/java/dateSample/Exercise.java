package dateSample;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Created by bzou on 2016/11/17.
 */
public class Exercise {
    public static void main(String ...args){
        Exercise.computerProgrammerDay();
        Exercise.addyears();
        Exercise.aliveDays();
    }

    public static void computerProgrammerDay(){
        System.out.println(LocalDate.of(2016,1,1).plus(255, ChronoUnit.DAYS));
    }

    public static void addyears(){
        System.out.println("2000-2-29 add 1 year " + LocalDate.of(2000,2,29).plusYears(1));
        System.out.println("2000-2-29 add 4 year " + LocalDate.of(2000,2,29).plusYears(4));
        System.out.println("2000-2-29 add 1 year 4 times " + LocalDate.of(2000,2,29).plusYears(1).plusYears(1).plusYears(1).plusYears(1));
    }

    /**
     * Write a program that prints how many days you have been alive
     */
    public static void aliveDays() {
        LocalDate birthday = LocalDate.of(1982, 4, 16);
        LocalDate now = LocalDate.now();
        Period d = Period.between(birthday, now);
        System.out.println("I have been alive for " + d.getYears() + "years " + d.getMonths() + "months " + d.getDays()+"days");

        LocalDateTime birthday2 = LocalDateTime.of(1982,4,16,0,0);
        LocalDateTime now2 = LocalDateTime.now();
        Duration duration = Duration.between(birthday2,now2);
        System.out.println("I have been alive for " + duration.toDays());
    }

    /**
     * List all Friday the 13th in the twentieth century.
     */
    public static  void listAllFridaysIn13thCentry(){

    }



}

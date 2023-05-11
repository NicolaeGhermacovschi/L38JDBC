package org.example;

import org.example.JDBC.JDBC;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!\n");
        JDBC jdbc = new JDBC();

        Scanner sc = new Scanner(System.in);

        int ch;
        do {
            System.out.println("===============================");
            System.out.println("|1| show data                   |");
            System.out.println("|2| add new employee            |");
            System.out.println("|3| update existing employee    |");
            System.out.println("|4| delete from table           |");
            System.out.println("|5| max and min employee by age |");
            System.out.println("|0| EXIT                        |");
            System.out.println("===============================");
            System.out.print("=>");

            ch = sc.nextInt();
            jdbc.menuSQL(ch);
//            try {
//
//                jdbc.menuSQL(ch);
//            } catch (RuntimeException e) {
//                System.out.println(e.getMessage());
//            }
        } while (ch != 0);

        sc.close();

    }
}

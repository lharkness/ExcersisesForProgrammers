package com.leeharkness;

import java.util.Scanner;

public class Greeter {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = inputReader.next();
        String output = "Hello, " + name + " nice to meet you!";
        System.out.println(output);
    }
}

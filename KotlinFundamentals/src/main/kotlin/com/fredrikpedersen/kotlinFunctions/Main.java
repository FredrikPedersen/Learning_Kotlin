package com.fredrikpedersen.kotlinFunctions;

public class Main {

    public static void main(String[] args) {
        callOverloadedFunction();
    }

    private static void callOverloadedFunction() {
        KotlinFunctions.logDefaultParam("Hello There");
        System.out.println();
        KotlinFunctions.logDefaultParam("Hello There", 10);
    }
}

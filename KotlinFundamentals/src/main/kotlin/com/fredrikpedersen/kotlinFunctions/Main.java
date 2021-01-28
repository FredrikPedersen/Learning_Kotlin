package com.fredrikpedersen.kotlinFunctions;

public class Main {

    public static void main(String[] args) {
        callOverloadedFunction();
    }

    private static void callOverloadedFunction() {
        KotlinFunctions.log("Hello There");
        System.out.println();
        KotlinFunctions.log("Hello There", 10);
    }
}

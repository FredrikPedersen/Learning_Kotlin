package com.fredrikpedersen.datatypesAndNullReferences;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 16/08/2021 at 12:46
 */

public class NullReference {

    public static void main(String[] args) {
        final String str = null;
        str.toUpperCase(); //NullPointerException
    }
}

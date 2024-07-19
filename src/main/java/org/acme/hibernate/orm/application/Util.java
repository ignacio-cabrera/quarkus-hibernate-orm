package org.acme.hibernate.orm.application;

public class Util {
    private Util() {
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public static boolean startsWithVowel(String str) {
        return isVowel(str.charAt(0));
    }
}

package fr.formiko.utils;

public final class Utils {

    private Utils(){}
    public static String getClassBaseName(Object object) {
        String [] words = object.getClass().getName().split("\\.");
        return words[words.length - 1];
    }
}

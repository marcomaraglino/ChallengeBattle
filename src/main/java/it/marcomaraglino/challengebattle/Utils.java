package it.marcomaraglino.challengebattle;

public class Utils {
    public static String capitalizeFirstLetter(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}

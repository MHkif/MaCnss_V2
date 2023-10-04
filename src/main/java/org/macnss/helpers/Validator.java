package org.macnss.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static String regexString = "^(?![0-9\\s]+$)[A-Za-z][A-Za-z\\s]*$\n";
    private static String regexInteger = "^[0-9]+$";

    public static boolean validString(String input){

        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return true;

        } else {
            return false;
        }
    }


    public static boolean validName(String input){

        String pattern = "^[A-Za-z][A-Za-z\\s]*$";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(input);

        // Check if the input matches the pattern
        return !input.isEmpty() && !input.matches(".*\\d.*") && matcher.matches();

    }

    public static boolean validEmail(String input){

        String pattern = "^(.+)@(.+)$";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(input);

        // Check if the input matches the pattern
        return matcher.matches();



    }

    public static boolean validInteger(String input){

        Pattern regex = Pattern.compile(regexInteger);

        // Create a Matcher object
        Matcher matcher = regex.matcher(input);

        if(matcher.matches()){
            return true;
        }else {
            return false;
        }
    }


}
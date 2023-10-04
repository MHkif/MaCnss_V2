package org.macnss.helpers;

import org.macnss.controllers.Controller;

public class PrintStatement extends Controller {

    public static void  opening(String text){
        System.out.println();
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("\t\t\t\t\t\t "+ text +" \t\t\t\t\t\t\t ");
        System.out.println("|----------------------------------------------------------------|\n");
    }

    public static void options(){
        System.out.println("\nYou want to :");
        System.out.println("1 - Log in as Admin .");
        System.out.println("2 - Log in as Agent .");
        System.out.println("3 - Shows Folders History .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void adminOptions(){
        System.out.println("\nAs an Admin you can : ");
        System.out.println("1 - Create new agent .");
        System.out.println("2 - Update an agent .");
        System.out.println("3 - Get an agent .");
        System.out.println("4 - Get all agents .");
        System.out.println("5 - delete an agent .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void agentOptions(){
        System.out.println("\nAs an Agent you can : ");
        System.out.println("1 - Create new folder .");
        System.out.println("2 - Get a folder .");
        System.out.println("3 - Get all folder .");
        System.out.println("4 - delete a folder .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }



    public static void backToMenu(){
        System.out.println("\n-> Tap any key to back to menu .\n");
        scanner.nextLine();
    }

    public  static void validateIdStatement(String id, String field){
        if(!Validator.validString(id)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't start by a numbers for this field - start by letters .");
                System.out.print("-> "+ field +" : ");
                id  = scanner.nextLine();
                if(Validator.validString(id)){
                    confirmName = false;
                }
            }
        }

    }

    public  static void validateNameStatement(String name, String field){
        if(!Validator.validName(name)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't use numbers for this field - use letters .");
                System.out.print("-> "+ field +" : ");
                name  = scanner.nextLine();
                if(Validator.validName(name)){
                    confirmName = false;
                }
            }
        }
    }

    public  static void validateNumberStatement(String num, String field){
        if(!Validator.validInteger(num)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nYou can't use letters for this field - use numbers .");
                System.out.print("-> "+ field +" : ");
                num  = scanner.nextLine();
                if(Validator.validInteger(num)){
                    confirmName = false;
                }
            }
        }
    }

    public  static void validateEmailStatement(String email){
        if(!Validator.validEmail(email)){
            boolean confirmEmail = true;
            while (confirmEmail){
                System.out.println("You have to provide a valid email ex: example@test.com .");
                System.out.print("-> Email : ");
                email  = scanner.nextLine();
                if(Validator.validEmail(email)){
                    confirmEmail = false;
                }
            }
        }
    }

    public  static void validatePasswordStatement(String password){
        if(!Validator.validString(password)){
            boolean confirmPassword = true;
            while (confirmPassword){
                System.out.println("\nInvalid Entry , Format not accepted, use numbers & letters only .");
                System.out.print("-> Password : ");
                password  = scanner.nextLine();
                if(Validator.validString(password)){
                    confirmPassword = false;
                }
            }
        }
    }
}

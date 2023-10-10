package org.macnss.Utils;

import org.macnss.Controllers.Controller;
import org.macnss.Entities.Employer;
import org.macnss.Entities.Folder;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PrintStatement extends Controller {

    public static void  opening(String text){
        System.out.println("\n\n");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("\t\t\t\t\t\t "+ text +" \t\t\t\t\t\t\t ");
        System.out.println("|----------------------------------------------------------------|\n");
    }

    public static void options(){
        System.out.println("\nYou want to :");
        System.out.println("1 - Log in as Admin .");
        System.out.println("2 - Log in as Agent .");
        System.out.println("3 - Log in as Company .");
        System.out.println("4 - Log in as Employer .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void adminOptions(){
        System.out.println("1 - Create new agent .");
        System.out.println("2 - Update an agent .");
        System.out.println("3 - Get an agent .");
        System.out.println("4 - Get all agents .");
        System.out.println("5 - delete an agent .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void agentOptions(){
        System.out.println("1 - Create new folder .");
        System.out.println("2 - Get a folder .");
        System.out.println("3 - Get all folder .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void companyOptions(){
        System.out.println("1 - Create new employer .");
        System.out.println("2 - Update an employer .");
        System.out.println("3 - Get an employer .");
        System.out.println("4 - Get all employers .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void employerOptions(){
        System.out.println("1 - View my profile .");
        System.out.println("2 - get folder .");
        System.out.println("3 - Get all folder .");
        System.out.println("4 - Check Retirement salary .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void updateEmployer(){
        System.out.println("What you want to update : ");
        System.out.println("1 - First Name .");
        System.out.println("2 - Last Name .");
        System.out.println("3 - Email .");
        System.out.println("4 - Password .");
        System.out.println("5 - Birth Day .");
        System.out.println("6 - Salary .");
        System.out.println("0 - Quitter .");
        System.out.print("->  ");
    }

    public static void displayEmployer(Employer employer, int wordeDays){
        System.out.println("* Employer : ");
        System.out.println("  Matriculate : " + employer.getMatriculate());
        System.out.println("  First Name : " + employer.getFirstName());
        System.out.println("  Last Name : " + employer.getLastName());
        System.out.println("  Email : " + employer.getEmail());
        System.out.println("  Password : " + employer.getPassword());
        System.out.println("  Birth day : " + employer.getBirthDay().toString());
        System.out.println("  Salary : " + employer.getSalary());
        System.out.println("  Status : " + employer.getStatus());
        System.out.println("  Worked Days : " + wordeDays);
        System.out.println();
    }

    public static void displayFolder(Folder folder){
        System.out.println("* Folder : ");
        System.out.println("  ID : " + folder.getId());
        System.out.println("  Name : " + folder.getName());
        System.out.println("  Deposit at : " + folder.getDepositDate());
        System.out.println("  Total refund : " + folder.getTotal_refund());
        System.out.println("  Status : " + folder.getStatus());
        System.out.println();
    }


    public static void backToMenu(){
        System.out.println("\n-> Tap any key to back to menu .\n");
        String s = scanner.nextLine();
    }

    public  static Date parsingDate(String dateInput) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateInput);
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

    public  static void validateDateStatement(String date, String field){
        if(!Validator.validDate(date)){
            boolean confirmName = true;
            while (confirmName){
                System.out.println("\nDate field requires a form like : YYYY-MM-DD .");
                System.out.print("-> "+ field +" : ");
                date  = scanner.nextLine();
                if(Validator.validDate(date)){
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

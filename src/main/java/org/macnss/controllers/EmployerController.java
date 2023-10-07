package org.macnss.controllers;

import org.macnss.Main;
import org.macnss.Services.EmployerService;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.UniqueCodeGenerator;
import org.macnss.Utils.Validator;
import org.macnss.entity.*;

import java.sql.Date;
import java.text.ParseException;

public class EmployerController extends Controller{

    private Employer employer ;
    private final EmployerService employerService = new EmployerService();

    public EmployerController(){
        if(!Main.SESSION.has("Employer")){
            Navigator.INSTANCE().index();
        }else {
            this.employer = (Employer) Main.SESSION.get("Employer");
        }
    }

    public void index(){
        try {
            boolean isRunning = true;

            while (isRunning){
                PrintStatement.opening("Employer Panel");
                PrintStatement.employerOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> {
                            isRunning = false;
                            Main.SESSION.remove("Employer");
                        }
                        case 1 -> this.getFolder();
                        case 2 -> this.getAllFolder();
                        case 3 -> this.checkRetirementSalary();
                    }
                }
                else{
                    System.out.println("\nInvalid Entry , Choose one of the following options: ");
                }
            }

        }catch (Exception e){
            System.out.println("Crashed : "+ e.getCause());
        }

    }

    public void create() throws ParseException {
        Employer employer = new Employer();
        PrintStatement.opening("Add new employer");
        System.out.print("-> First name : ");
        String firstName = scanner.nextLine();
        PrintStatement.validateNameStatement(firstName, "First name ");

        System.out.print("-> Last name : ");
        String lastName = scanner.nextLine();
        PrintStatement.validateNameStatement(lastName, "Last name ");

        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);

        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        System.out.print("-> Birth Day : ");
        String birthDay = scanner.nextLine();
        PrintStatement.validateDateStatement(birthDay, "Birth Day");

        System.out.print("-> Salary : ");
        String salary = scanner.nextLine();
        PrintStatement.validateNumberStatement(salary, "Salary");
        System.out.print("\n");

        employer.setMatriculate(UniqueCodeGenerator.code());
        employer.setFirstName(firstName);
        employer.setLastName(lastName);
        employer.setEmail(email);
        employer.setPassword(password);
        Date ConvertedDate =  new Date(PrintStatement.parsingDate(birthDay).getTime());
        employer.setBirthDay(ConvertedDate);
        employer.setSalary(salary);
        if(employerService.save(employer) != null){
            System.out.println("Employer has been created successfully");
        }else{
            System.out.println("Creation of Employer has been Failed");
        }
    }

     public void update(Employer employer){

     }

     public void find(){

     }

     public void getAll(){

     }

     public void deactivate(Employer employer){

     }

    public void getFolder(){
        System.out.println("Get Folder");
    }

     public void getAllFolder(){

     }

     public void checkRetirementSalary(){
         if (!employerService.ageVerified(employer) || employerService.retirementSalary(employer) == 0){
            System.out.println("\nYou are not allowed to have a retirement salary Mr "+
                    employer.getFirstName() + " " + employer.getLastName());
        }else{
            System.out.println("\nRetirement Salary : "+ employerService.retirementSalary(employer));
        }
         PrintStatement.backToMenu();
     }

}

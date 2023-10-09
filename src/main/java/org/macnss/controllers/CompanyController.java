package org.macnss.controllers;

import org.macnss.Main;
import org.macnss.Services.EmployerService;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.UniqueCodeGenerator;
import org.macnss.Utils.Validator;
import org.macnss.entity.Company;
import org.macnss.entity.Employer;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class CompanyController extends Controller{

        private Company company ;
        private final EmployerService employerService = new EmployerService();

        public CompanyController(){
                if(!Main.SESSION.has("Company")){
                        Navigator.INSTANCE().index();
                }else {
                        this.company = (Company) Main.SESSION.get("Company");
                }

        }

        public void index(){
                try {
                        boolean isRunning = true;
                        while (isRunning){
                                PrintStatement.opening("Company Panel");
                                PrintStatement.companyOptions();
                                String option = scanner.nextLine();
                                if(Validator.validInteger(option) &&  Integer.parseInt(option) < 5){
                                        switch (Integer.parseInt(option)) {
                                                case 0 -> {
                                                        isRunning = false;
                                                        Main.SESSION.remove("Company");
                                                }

                                                case 1 -> this.createEmployer();
                                                case 2 -> this.updateEmployer();
                                                case 3 -> this.findEmployer();
                                                case 4 -> this.getAllEmployers();


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

        public void createEmployer() throws ParseException {
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
                        if(!employerService.associateEmployerWithCompany(employer, company, 26)){
                                System.out.println(" # ALERT #  : Employer record has not been saved in employers history try to fix it . ");
                        }
                }else{
                        System.out.println("Creation of Employer has been Failed");
                }
        }

        public void updateEmployer(){
                Employer employer;

                        boolean isRunning = true;
                        while (isRunning) {
                                PrintStatement.opening("Update an employer ");
                                System.out.print("-> Matriculate : ");
                                String matriculate = scanner.nextLine();
                                PrintStatement.validateNameStatement(matriculate, "Matriculate ");
                                employer = employerService.findBy(matriculate);
                                if(Objects.nonNull(employer)) {
                                        PrintStatement.displayEmployer(employer);
                                        PrintStatement.updateEmployer();
                                        String option = scanner.nextLine();
                                        if (Validator.validInteger(option) && Integer.parseInt(option) < 7) {
                                                switch (Integer.parseInt(option)) {
                                                        case 0 -> {
                                                                isRunning = false;
                                                        }
                                                        case 1 -> {
                                                                System.out.print("-> First name : ");
                                                                String firstName = scanner.nextLine();
                                                                PrintStatement.validateNameStatement(firstName, "First name ");
                                                                employer.setFirstName(firstName);
                                                        }
                                                        case 2 -> {
                                                                System.out.print("-> Last name : ");
                                                                String lastName = scanner.nextLine();
                                                                PrintStatement.validateNameStatement(lastName, "Last name ");
                                                                employer.setLastName(lastName);
                                                        }
                                                        case 3 -> {
                                                                System.out.print("-> Email : ");
                                                                String email = scanner.nextLine();
                                                                PrintStatement.validateEmailStatement(email);
                                                                employer.setEmail(email);
                                                        }
                                                        case 4 -> {
                                                                System.out.print("-> Password : ");
                                                                String password = scanner.nextLine();
                                                                PrintStatement.validatePasswordStatement(password);
                                                                employer.setPassword(password);
                                                        }
                                                        case 5 -> {
                                                                System.out.print("-> Birth Day : ");
                                                                String birthDay = scanner.nextLine();
                                                                PrintStatement.validateDateStatement(birthDay, "Birth Day");
                                                                try {
                                                                        Date ConvertedDate = new Date(PrintStatement.parsingDate(birthDay).getTime());
                                                                        employer.setBirthDay(ConvertedDate);
                                                                } catch (Exception e) {
                                                                        throw new RuntimeException();
                                                                }
                                                        }
                                                        case 6 -> {
                                                                System.out.print("-> Salary : ");
                                                                String salary = scanner.nextLine();
                                                                PrintStatement.validateNumberStatement(salary, "Salary");
                                                                employer.setSalary(salary);
                                                        }

                                                }
                                                if (employerService.update(employer) != null) {
                                                        System.out.println("\n\nEmployer has been updated successfully\n");
                                                        PrintStatement.displayEmployer(employer);
                                                } else {
                                                        System.out.println("Update of Employer has been Failed");
                                                }
                                                isRunning = false;
                                                PrintStatement.backToMenu();
                                        } else {
                                                System.out.println("\nInvalid Entry , Choose one of the following options: ");
                                        }


                                }else {
                                        System.out.println("\nEmployer not found .");
                                        PrintStatement.backToMenu();
                                }
                        }


        }

        public void findEmployer(){
                Employer employer;
                PrintStatement.opening("Find an employer ");
                System.out.print("-> Matriculate : ");
                String matriculate = scanner.nextLine();
                PrintStatement.validateNameStatement(matriculate, "Matriculate ");
                employer = employerService.findBy(matriculate);
                if(Objects.nonNull(employer)){
                        System.out.println("\n");
                        PrintStatement.displayEmployer(employer);
                }else {
                        System.out.println("\nEmployer not found .");

                }
                PrintStatement.backToMenu();
        }

        public void getAllEmployers(){
                List<Employer> employers;
                PrintStatement.opening("Get all employers ");
                employers = employerService.getAll();
                if(Objects.nonNull(employers)){
                      System.out.println("\n");
                      employers.forEach(PrintStatement::displayEmployer);
                      PrintStatement.backToMenu();
                }else {
                        System.out.println("\nNo employers has been found .");
                        PrintStatement.backToMenu();
                }
        }

        public void deactivateEmployer(){

        }



}

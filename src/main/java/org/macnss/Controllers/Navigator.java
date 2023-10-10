package org.macnss.Controllers;


import org.macnss.Main;
import org.macnss.Services.*;
import org.macnss.Entities.Agent;
import org.macnss.Utils.PrintStatement;

import org.macnss.Utils.UniqueCodeGenerator;
import org.macnss.Utils.Validator;


import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;


public class Navigator extends Controller {

    private static Navigator instance;
    private AdminController adminController;
    private AgentController agentController;
    private CompanyController companyController;
    private EmployerController employerController;

    private final AdminService adminService = new AdminService();
    private  final AgentService agentService = new AgentService();
    private final CompanyService companyService = new CompanyService();
    private  final EmployerService employerService = new EmployerService();
    private Navigator(){
        if(Main.SESSION.has("Admin")){
            adminController = new AdminController();
            adminController.index();
        }
        else if(Main.SESSION.has("Agent")){
            agentController = new AgentController();
            agentController.index();
        }
        else if(Main.SESSION.has("Company")){
            companyController = new CompanyController();
            companyController.index();
        }
        else if(Main.SESSION.has("Employer")){
            employerController = new EmployerController();
            employerController.index();
        }else {
            System.out.print("\nWelcome Again to MaCnss Application .");
        }
    }

    public static synchronized Navigator INSTANCE(){
        if(instance == null){
            instance = new Navigator();
        }
        return instance;
    }

    public void index(){


            try {
                boolean isRunning = true;
                while (isRunning){
                    PrintStatement.opening("MaCnss Application");
                    PrintStatement.options();
                    String option = scanner.nextLine();

                    if(Validator.validInteger(option) && Integer.parseInt(option) < 5){
                        switch (Integer.parseInt(option)) {
                            case 0 -> {
                                isRunning = false;
                                Main.SESSION.unset();
                            }
                            case 1 -> this.loginAsAdmin();
                            case 2 -> this.loginAsAgent();
                            case 3 -> this.loginAsCompany();
                            case 4 -> this.loginAsEmployer();
                        }
                    }
                    else{
                        System.out.println("\nInvalid Entry , Choose one of the following options: ");
                    }
                }

            }catch (Exception e){
                System.out.println("Crashed : "+e);
            }

    }

    public void loginAsAdmin(){
        System.out.println("\n");
        System.out.println("Login as admin , Enter your credentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        try {
            if(adminService.login(email, password) != null){
                Main.SESSION.set("Admin", adminService.login(email, password));
                adminController = new AdminController();
                adminController.index();
            }else {
                System.out.println("Admin not found .");
                PrintStatement.backToMenu();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginAsAgent(){
        System.out.println("\n");
        System.out.println("Login as Agent, Enter your credentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        if(agentService.login(email, password) != null){
            LocalTime time = LocalTime.now();
            Main.SESSION.set("sendingCode", time);
            Main.SESSION.set("Agent", agentService.login(email, password));
            agentController = new AgentController();
            Agent agent = (Agent) Main.SESSION.get("Agent");
            final String code = UniqueCodeGenerator.code();
            String text = "Code Verification sent by MaCnss : "+ code;
            EmailService.sendEmail(text, "code verification ", agent.getEmail());
            System.out.println("\n* We send you a code to your email , Enter 0 to exit .");
            System.out.println("-> code verification :");
            String codeVer = scanner.nextLine();
            if(codeVer.equals("0")){
                PrintStatement.backToMenu();
            }else{
                int tries = 5;
                while (tries > 0){
                    long minutes  = ChronoUnit.MINUTES.between((Temporal) Main.SESSION.get("sendingCode"), LocalTime.now()) % 60;
                    if(minutes >= 5){
                        System.out.println("This code has been expired try again .");
                        break;
                    }
                    else if(codeVer.equals(code)){
                        agentController.index();
                    }
                    else{
                        System.out.println("\nCode verification wrong try again ."+ (--tries) +" tries left");
                        System.out.println("-> code verification :");
                        codeVer = scanner.nextLine();
                        if(codeVer.equals("0")){
                            break;
                        }
                    }
                }
                System.out.println("No tries left, Try to login again .");
                PrintStatement.backToMenu();
            }
        }else {
            System.out.println("Agent not found .");
            PrintStatement.backToMenu();
        }

    }

    public void loginAsCompany(){
        System.out.println("\n");
        System.out.println("Login as Company, Enter your credentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        if(companyService.login(email, password) != null){
            Main.SESSION.set("Company", companyService.login(email, password));
                companyController = new CompanyController();
                companyController.index();
        }else {
            System.out.println("Company not found .");
            PrintStatement.backToMenu();
        }

    }

    public void loginAsEmployer(){
        System.out.println("\n");
        System.out.println("Login as Employer, Enter your credentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        if(employerService.login(email, password) != null){
            Main.SESSION.set("Employer", employerService.login(email, password));
            employerController = new EmployerController();
            employerController.index();
        }else {
            System.out.println("\nEmployer not found .");
            PrintStatement.backToMenu();
        }

    }



}

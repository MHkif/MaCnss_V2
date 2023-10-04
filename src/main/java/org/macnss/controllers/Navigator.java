package org.macnss.controllers;


import org.macnss.Main;
import org.macnss.Services.AdminService;
import org.macnss.Services.AgentService;
import org.macnss.Services.EmailService;
import org.macnss.entity.Agent;
import org.macnss.helpers.PrintStatement;

import org.macnss.helpers.UniqueCodeGenerator;
import org.macnss.helpers.Validator;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Navigator extends Controller {

    private static Navigator instance;
    private AdminController adminController;
    private AgentController agentController;
    private Navigator(){

    }

    public static synchronized Navigator INSTANCE(){
        if(instance == null){
            instance = new Navigator();
        }
        return instance;
    }

    private final AdminService adminService = new AdminService();
    private  final AgentService agentService = new AgentService();



    public void index(){

        PrintStatement.opening("MaCnss Application");
        try {
            boolean isRunning = true;
            while (isRunning){
                PrintStatement.options();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> isRunning = false;
                        case 1 -> this.loginAsAdmin();
                        case 2 -> this.loginAsAgent();
                        case 3 -> this.folderHistory();
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
        adminController = new AdminController();
        System.out.println("Login as admin , Enter your creadentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        try {
            if(adminService.login(email, password) != null){
                Main.SESSION.set("Admin", adminService.login(email, password));
                System.out.println(Main.SESSION.get("Admin").toString());
                adminController.index();
            }else {
                System.out.println("Admin not found .");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginAsAgent(){
        agentController = new AgentController();
        System.out.println("Login as Agent, Enter your creadentials :");
        System.out.print("-> Email : ");
        String email = scanner.nextLine();
        PrintStatement.validateEmailStatement(email);
        System.out.print("-> Password : ");
        String password = scanner.nextLine();
        PrintStatement.validatePasswordStatement(password);

        if(agentService.login(email, password) != null){
            LocalTime time = LocalTime.now();
            Main.SESSION.set("sendingCode", time);
            System.out.println("The Code has been sent at "+ Main.SESSION.get("sendingCode") );
            Main.SESSION.set("Agent", agentService.login(email, password));
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
        }

    }


    private void folderHistory(){

        System.out.print("Enter you matriculate reference : ");
        String matriculate = scanner.nextLine();
        PrintStatement.validateIdStatement(matriculate , "matriculate");


    }
}

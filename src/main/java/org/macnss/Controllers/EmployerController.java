package org.macnss.Controllers;

import org.macnss.Main;
import org.macnss.Services.EmployerService;
import org.macnss.Services.FolderService;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.Validator;
import org.macnss.Entities.*;

import java.util.Objects;

public class EmployerController extends Controller{

    private Employer employer ;
    private final EmployerService employerService = new EmployerService();
    private final FolderService folderService = new FolderService();

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
                        case 1 -> this.profile();
                        case 2 -> this.getFolder();
                        case 3 -> this.getAllFolder();
                        case 4 -> this.checkRetirementSalary();
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

    public void profile(){
        PrintStatement.opening("My Profile");
        PrintStatement.displayEmployer(employer, employerService.workedDays(employer));
        PrintStatement.backToMenu();
    }

    public void getFolder(){
        Folder folder;
        PrintStatement.opening("Find A Folder");
        System.out.print("-> ID : ");
        String id = scanner.nextLine();
        PrintStatement.validateNameStatement(id, "Folder Id ");
        folder = folderService.findBy(id);
        if(Objects.nonNull(folder)){
            PrintStatement.displayFolder(folder);
        }else {
            System.out.println("\nFolder not found .");
        }
        PrintStatement.backToMenu();
    }

     public void getAllFolder(){
         PrintStatement.opening("Get All My Folders");
         if ((long) employerService.getAllMyFolders(folderService.getAll(), employer).size() > 0){
             employerService.getAllMyFolders(folderService.getAll(), employer).forEach(PrintStatement::displayFolder);
         }else {
             System.out.println("No folders found for this employer .");
         }
         PrintStatement.backToMenu();
     }

     public void checkRetirementSalary(){
         if (!employerService.ageVerified(employer) ){
            System.out.println("\nYou are not allowed to have a retirement salary Mr "+
                    employer.getFirstName() + " " + employer.getLastName() + " Because you are not older enough (Must be >= 55 years) .");
        }else if(employerService.workedDays(employer) < 1320){
             System.out.println("\nYou are not allowed to have a retirement salary Mr "+
                     employer.getFirstName() + " " + employer.getLastName() +
                     " Because your working days are not >= 1320 (Your working days : "+employerService.workedDays(employer)+" ) .");
         }
         else{
            System.out.println("\nRetirement Salary : "+ employerService.retirementSalary(employer));
        }
         PrintStatement.backToMenu();
     }

}

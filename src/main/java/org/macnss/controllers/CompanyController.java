package org.macnss.controllers;

import org.macnss.Main;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.UniqueCodeGenerator;
import org.macnss.Utils.Validator;
import org.macnss.entity.ADocument;
import org.macnss.entity.Employer;

public class CompanyController extends Controller{

        private final EmployerController employerController = new EmployerController();

        public void index(){
                try {
                        boolean isRunning = true;
                        while (isRunning){
                                PrintStatement.opening("Company Panel");
                                PrintStatement.companyOptions();
                                String option = scanner.nextLine();
                                if(Validator.validInteger(option)){
                                        switch (Integer.parseInt(option)) {
                                                case 0 -> {
                                                        isRunning = false;
                                                        Main.SESSION.remove("Company");
                                                }
                                                case 1 -> employerController.create();
                                                case 2 -> employerController.update(new Employer());
                                                case 3 -> employerController.find();
                                                case 4 -> employerController.getAll();
                                                case 5 -> employerController.deactivate(new Employer());
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

        public void create(){

        }

}

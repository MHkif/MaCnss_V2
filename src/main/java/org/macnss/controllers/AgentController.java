package org.macnss.controllers;

import org.macnss.Main;
import org.macnss.entity.*;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.Validator;

public class AgentController extends Controller{

    private Agent agent ;
    private final FolderController folderController = new FolderController();

    public AgentController() {
        if(!Main.SESSION.has("Agent")){
            Navigator.INSTANCE().index();
        }else {
            this.agent = (Agent) Main.SESSION.get("Agent");
        }
    }

    public void index(){
        try {
            boolean isRunning = true;

            while (isRunning){
                PrintStatement.opening("Agent Panel");
                PrintStatement.agentOptions();
                String option = scanner.nextLine();
                if(Validator.validInteger(option)){
                    switch (Integer.parseInt(option)) {
                        case 0 -> {
                            isRunning = false;
                            Main.SESSION.remove("Agent");
                        }
                        case 1 -> folderController.createFolder(agent);
                        case 2 -> folderController.getFolder();
                        case 3 -> folderController.getAllFolder();
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
}

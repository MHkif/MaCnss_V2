package org.macnss.Controllers;

import org.macnss.Enum.DocumentStatus;
import org.macnss.Enum.DocumentType;
import org.macnss.Services.DocumentService;
import org.macnss.Services.FolderService;
import org.macnss.Services.EmployerService;
import org.macnss.Entities.*;
import org.macnss.Entities.Folder;
import org.macnss.Utils.PrintStatement;
import org.macnss.Utils.UniqueCodeGenerator;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Date;

public class FolderController extends Controller {
    private final FolderService folderService = new FolderService();
     EmployerService employerService = new EmployerService();
    private final DocumentService documentService = new DocumentService();




    public void createFolder(Agent agent){
        List<ADocument> documents = new ArrayList<>();
        PrintStatement.opening("Create new folder");
        System.out.print("-> Matriculate of Owner : ");
        String matriculate = scanner.nextLine();
        PrintStatement.validateIdStatement(matriculate,"Matriculate of Owner");
        Folder folder = new Folder();
        Employer employer = employerService.findBy(matriculate);

        if(Objects.isNull(employer)){
            boolean isNull = true;
            while (isNull){
                System.out.println("Patient not found , please provide a valid matriculate .");
                System.out.print("-> Matriculate of patient : ");
                matriculate = scanner.nextLine();
                PrintStatement.validateIdStatement(matriculate,"Matriculate of Owner");
                if(Objects.nonNull(employerService.findBy(matriculate))){
                    isNull = false;
                }
            }
        }else {
            folder.setAgent(agent);
            folder.setId(UniqueCodeGenerator.code());
            folder.setDepositDate(new Date(System.currentTimeMillis()));
            folder.setEmployer(employer);
            System.out.print("-> Name : ");
            String folder_name = scanner.nextLine();
            PrintStatement.validateNameStatement(folder_name, "Name");
            folder.setName(folder_name);

            addDocuments(folder).forEach(documents::add);
            addMedicines(folder).forEach(documents::add);
            addRadios(folder).forEach(documents::add);
            addScanners(folder).forEach(documents::add);

            double total = documents.stream().mapToDouble(aDocument -> aDocument.getPrice() * aDocument.getRefund_rate() / 100).sum();

            folder.setTotal_refund(Float.parseFloat(String.valueOf(total)));

            System.out.print("\n");
            if(folderService.save(folder) != null){
                for (ADocument document: documents) {
                    if(documentService.create(document) != null){
                        System.out.println("\nDocument "+ document.getTitle()+" has been created successfully");
                        System.out.println("Refund amount of this document is :"+  document.getPrice() * document.getRefund_rate() / 100);
                    }else{
                        System.out.println("Creation of Document "+ document.getTitle()+" has been Failed");
                    }
                }
                System.out.println("folder has been created successfully");
                System.out.println("Refund amount of this folder is :"+ folder.getTotal_refund());
            }else{
                System.out.println("Creation of folder has been Failed");
            }
        }
    }
    public void getAllFolder(){
        PrintStatement.opening("All Folders");
        List<Folder> folders = folderService.getAll();
        folders.forEach(PrintStatement::displayFolder);
       PrintStatement.backToMenu();
    }

    public void getFolder(){
        PrintStatement.opening("Get Folder");

            System.out.print("-> Folder ID : ");
            String folder_id = scanner.nextLine();
            PrintStatement.validateIdStatement(folder_id,"Folder ID");
            Folder folder =  folderService.findBy(folder_id);
            if(Objects.isNull(folder)){
                boolean isNull = true;
                while (isNull){
                    System.out.println("Folder not found , please provide a valid Folder ID .");
                    System.out.print("-> Folder ID : ");
                    folder_id = scanner.nextLine();
                    PrintStatement.validateIdStatement(folder_id,"Folder ID");
                    if(Objects.nonNull(employerService.findBy(folder_id))){
                        folder = folderService.findBy(folder_id);
                        System.out.println(folder.toString());
                        System.out.print("\nClick any key to exit .\n-> ");
                        scanner.nextLine();
                        isNull = false;
                    }
                }

            }
            else{
                PrintStatement.displayFolder(folder);
                PrintStatement.backToMenu();
            }

    }

    public  List<ADocument> addDocuments(Folder folder){
        List<ADocument> documents = new ArrayList<>();
        System.out.print("\nTo add Documents click any key , Enter on 0 ta exit .\n-> ");
        String  doc_option  = scanner.nextLine();
        if(!doc_option.equals("0"))
        {
            PrintStatement.opening("Add new Document");
            Document document = new Document();
            int num = 1;
            boolean addingNew = true;
            while (addingNew){

                System.out.println("Document "+num +" :");
                document.setId(UniqueCodeGenerator.code());
                System.out.print("-> Title : ");
                String doc_title = scanner.nextLine();
                PrintStatement.validateNameStatement(doc_title, "Title");
                document.setTitle(doc_title);

                System.out.print("-> Price  : ");
                String doc_cost = scanner.nextLine();
                PrintStatement.validateNumberStatement(doc_cost, "Price");
                document.setPrice(Float.parseFloat(doc_cost));

                System.out.print("-> Refund Rate  : ");
                String doc_refund = scanner.nextLine();
                PrintStatement.validateNumberStatement(doc_refund, "Refund Rate");
                document.setRefund_rate(Float.parseFloat(doc_refund));

                document.setCreatedAt(new Date(System.currentTimeMillis()));
                document.setFolder(folder);

                documents.add(document);

                System.out.print("\nTo add  new document click any key , Enter on 0 ta exit .\n-> ");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return documents;
    }
    public List<ADocument> addMedicines(Folder folder){
        List<ADocument> medicines = new ArrayList<>();
        System.out.print("\nTo add Medicines click any key , Enter on 0 ta exit .\n-> ");
        String  med_option  = scanner.nextLine();
       if(!med_option.equals("0")) {
           PrintStatement.opening("Add new Medicine");
            Medicine medicine = new Medicine();
            int num = 1;
            boolean addingNew = true;
            while (addingNew){
                java.sql.Date createdAt = new java.sql.Date(System.currentTimeMillis());
                System.out.println("Medicine "+num +" : ");
                String med_id = UniqueCodeGenerator.code();
                medicine.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                medicine.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                medicine.setPrice(Float.parseFloat(med_price));

                System.out.println("-> Refund Availability  : ");
                System.out.print("1 -> Yes \n2 -> No \n-> ");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.println("-> Refund Availability  : ");
                        System.out.print("1 -> Yes \n2 -> No \n-> ");
                        med_refund_av = scanner.nextLine();
                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        medicine.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        // # TODO : dans ce cas le patient peut avoir juste le remboursement d’une visite médicale
                        medicine.setStatus(DocumentStatus.NOT_REFUND);
                    }
                }
                medicine.setType(DocumentType.MEDICINE);
                medicine.setCreatedAt(createdAt);
                medicine.setFolder(folder);

                medicines.add(medicine);

                System.out.println("\nTo add  new medicines click any key , Enter on 0 ta exit .\n-> ");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
       return medicines;
    }

    public List<ADocument> addRadios(Folder folder){
        List<ADocument> radios = new ArrayList<>();
        System.out.print("\nTo add a Radio click any key , Enter on 0 ta exit . \n-> ");
        String  radio_option  = scanner.nextLine();
        if(!radio_option.equals("0")) {
            PrintStatement.opening("Add new Radio");
            Radio radio = new Radio();
            int num = 1;
            boolean addingNew = true;
            while (addingNew){
                System.out.println("Radio "+num +" :\n");
                String med_id = UniqueCodeGenerator.code();
                radio.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                radio.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                radio.setPrice(Float.parseFloat(med_price));

                System.out.println("-> Refund Availability  : ");
                System.out.print("1 -> Yes \n2 -> No \n-> ");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.println("-> Refund Availability  : ");
                        System.out.print("1 -> Yes \n2 -> No \n-> ");
                        med_refund_av = scanner.nextLine();

                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        radio.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        radio.setStatus(DocumentStatus.NOT_REFUND);
                    }
                }

                radio.setType(DocumentType.RADIO);
                radio.setCreatedAt(new Date(System.currentTimeMillis()));
                radio.setFolder(folder);

                radios.add(radio);

                System.out.print("\nTo add a new Radios click any key , Enter on 0 ta exit . \n-> ");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return radios;
    }

    public List<ADocument> addScanners(Folder folder){
        List<ADocument> scanners = new ArrayList<>();
        System.out.print("\nTo add a Scanner click any key , Enter on 0 ta exit . \n-> ");
        String  radio_option  = scanner.nextLine();
        if(!radio_option.equals("0")) {
            PrintStatement.opening("Add new Scanner");
            Scanner scanner1 = new Scanner();
            int num = 1;

            boolean addingNew = true;
            while (addingNew){
                System.out.println("Scanner "+num +" :\n");
                String med_id = UniqueCodeGenerator.code();
                scanner1.setId(med_id);

                System.out.print("-> Name : ");
                String med_name = scanner.nextLine();
                PrintStatement.validateNameStatement(med_name , "Name");
                scanner1.setTitle(med_name);

                System.out.print("-> Price  : ");
                String med_price = scanner.nextLine();
                PrintStatement.validateNumberStatement(med_price, "Price");
                scanner1.setPrice(Float.parseFloat(med_price));

                System.out.println("-> Refund Availability  : ");
                System.out.print("1 -> Yes \n2 -> No \n-> ");
                String med_refund_av = scanner.nextLine();

                if(!(med_refund_av.equals("1") || med_refund_av.equals("2"))){
                    System.out.println("\nInvalid choice, choose one of the following : ");

                    boolean notValid = true;
                    while (notValid){
                        System.out.println("-> Refund Availability  : ");
                        System.out.print("1 -> Yes \n2 -> No \n-> ");
                        med_refund_av = scanner.nextLine();

                        if(med_refund_av.equals("1") || med_refund_av.equals("2")){
                            notValid = false;
                        }else{
                            System.out.println("\nInvalid choice, choose one of the following : ");
                        }
                    }
                }else {
                    if (med_refund_av.equals("1")){
                        System.out.print("-> Refund Rate  : ");
                        String med_refund = scanner.nextLine();
                        PrintStatement.validateNumberStatement(med_refund, "Refund Rate");
                        scanner1.setRefund_rate(Float.parseFloat(med_refund));
                    }else{
                        scanner1.setStatus(DocumentStatus.NOT_REFUND);
                    }
                }

                scanner1.setType(DocumentType.SCANNER);

                scanner1.setCreatedAt(new Date(System.currentTimeMillis()));
                scanner1.setFolder(folder);
                scanners.add(scanner1);

                System.out.print("\nTo add a new Scanner click any key , Enter on 0 ta exit .");
                String  click  = scanner.nextLine();
                if(click.equals("0")){
                    addingNew = false;
                }else {
                    num++;
                }
            }
        }
        return scanners;
    }


}

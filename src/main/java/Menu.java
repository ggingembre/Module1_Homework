import Classes.*;
import Database.*;
import Exceptions.OutOfMenuRangeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by guillaume on 6/15/17.
 */
public class Menu {

    private boolean exit = false;
    private BufferedReader br = null;

    private DAOCompaniesImpl compDAO = new DAOCompaniesImpl();
    private DAOCustomersImpl custDAO = new DAOCustomersImpl();
    private DAODevelopersImpl devDAO = new DAODevelopersImpl();
    private DAOProjectsImpl proDAO = new DAOProjectsImpl();
    private DAOSkillsImpl skDAO = new DAOSkillsImpl();

    public Menu(DAOCompaniesImpl compDAO, DAOCustomersImpl custDAO, DAODevelopersImpl devDAO,
                DAOProjectsImpl proDAO, DAOSkillsImpl skDAO) {
        this.compDAO = compDAO;
        this.custDAO = custDAO;
        this.devDAO = devDAO;
        this.proDAO = proDAO;
        this.skDAO = skDAO;
    }

    private void printHeader(){
        System.out.println("*******************************");
        System.out.println("|      Welcome to our         |");
        System.out.println("|   Database Application      |");
        System.out.println("*******************************");
    }

    private void printUserMainMenu() {
        System.out.println("\nPlease make a selection");
        System.out.println("*****CREATE OBJECTS IN THE DATABASE*****");
        System.out.println("[1] Create an IT Company");
        System.out.println("[2] Create a Client");
        System.out.println("[3] Create a Developer");
        System.out.println("[4] Create a Project");
        System.out.println("[5] Create a Skill");
        System.out.println("[6] Add a Developer to an IT Company");
        System.out.println("[7] Add a Project to a Developer");
        System.out.println("[8] Add a Project to a Customer");
        System.out.println("[9] Add a Skill to a Developer");
        System.out.println("\n*****UPDATE OBJECTS IN THE DATABASE*****");
        System.out.println("[10] Update an IT Company");
        System.out.println("[11] Update a Client");
        System.out.println("[12] Update a Developer");
        System.out.println("[13] Update a Project");
        System.out.println("[14] Update a Skill");
        System.out.println("\n*****READ OBJECTS IN THE DATABASE*****");
        System.out.println("[15] Read info about an IT Company");
        System.out.println("[16] Read info about a Client");
        System.out.println("[17] Read info about a Developer");
        System.out.println("[18] Read info about a Project");
        System.out.println("[19] Read info about a Skill");
        System.out.println("\n*****DELETE OBJECTS IN THE DATABASE*****");
        System.out.println("[20] Delete an IT Company");
        System.out.println("[21] Delete a Client");
        System.out.println("[22] Delete a Developer");
        System.out.println("[23] Delete a Project");
        System.out.println("[24] Delete a Skill");
        System.out.println("[25] Delete a Developer from an IT Company");
        System.out.println("[26] Delete a Project from a Developer");
        System.out.println("[27] Delete a Project from a Customer");
        System.out.println("[28] Delete a Skill from a Developer");
        System.out.println("[29] Exit");
    }

    public void runMenu(){

        printHeader();
        while(!exit){
            printUserMainMenu();
            performActionUserMainMenu(getMenuInput(1,29));
        }

    }

    private int getMenuInput(int min, int max){
        int choice = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (choice < min || choice > max) {
            try {
                System.out.print("\nEnter your selection");
                choice = Integer.parseInt(br.readLine());
                if (choice < min || choice > max) throw new OutOfMenuRangeException();

            } catch (NumberFormatException | OutOfMenuRangeException | IOException e) {
                System.out.println("Invalid selection, please try again");
            }
        }
        return choice;
    }


    private void performActionUserMainMenu(int choice){
        switch(choice){
            case 1:
                createCompany();
                break;
            case 2:
                createCustomer();
                break;
            case 3:
                createDeveloper();
                break;
            case 4:
                createProject();
                break;
            case 5:
                createSkill();
                break;
            case 6:
                addDevToCo();
                break;
            case 7:
                addProjectToDev();
                break;
            case 8:
                addProjectToCustomer();
                break;
            case 9:
                addSkilltoDeveloper();
                break;
            case 10:
                updateCompany();
                break;
            case 11:
                updateCustomer();
                break;
            case 12:
                updateDeveloper();
                break;
            case 13:
                updateProject();
                break;
            case 14:
                updateSkill();
                break;
            case 15:
                readCompany();
                break;
            case 16:
                readCustomer();
                break;
            case 17:
                readDeveloper();
                break;
            case 18:
                readProject();
                break;
            case 19:
                readSkill();
                break;
            case 20:
                deleteCompany();
                break;
            case 21:
                deleteCustomer();
                break;
            case 22:
                deleteDeveloper();
                break;
            case 23:
                deleteProject();
                break;
            case 24:
                deleteSkill();
                break;
            case 25:
                DeleteDevFromCo();
                break;
            case 26:
                DeleteProFromDev();
                break;
            case 27:
                DeleteProFromCust();
                break;
            case 28:
                DeleteSkillFromDev();
                break;
            case 29:
                exit = true;
                System.out.println("Thank you for using our application");
        }
    }

    private void createCompany() {

        System.out.println("You will now be prompted to enter the information about the new company");

       // Company(String companyName, String companyAddress, String companyDescription)

        String compName = readNameFromConsole("company name");
        String compAddress = readNameFromConsole("company address");
        String compDesc = readNameFromConsole("company description");

        Company newCo = new Company(compName, compAddress, compDesc);
        compDAO.create(newCo);

        System.out.println("You have created the following company:");
        System.out.println("CompanyID: "+ newCo.getId());
        System.out.println("Company Name: " + newCo.getCompanyName());
        System.out.println("Company address: " + newCo.getCompanyAddress());
        System.out.println("Company description: " + newCo.getCompanyDescription());

    }

    private void createCustomer() {

        System.out.println("You will now be prompted to enter the information about the new customer");

        // public Customer(String customerName, String customerAddress, String customerPhone, String customerDescription)

        String custName = readNameFromConsole("customer name");
        String custAddress = readNameFromConsole("customer address");
        String custPhone = readNameFromConsole("customer phone");
        String custDesc = readNameFromConsole("customer description");

        Customer newCust = new Customer(custName, custAddress, custPhone, custDesc);
        custDAO.create(newCust);

        System.out.println("You have created the following customer:");
        System.out.println("Customer ID: "+ newCust.getCustomerId());
        System.out.println("Customer Name: " + newCust.getCustomerName());
        System.out.println("Customer address: " + newCust.getCustomerAddress());
        System.out.println("Customer phone: " + newCust.getCustomerPhone());
        System.out.println("Customer description: " + newCust.getCustomerDescription());

    }

    private void createDeveloper() {

            System.out.println("You will now be prompted to enter the information about the new developer");

            //public Developer(String firstName, String lastName, String gender,
            //    String address, String email, String phone, String jobTitle, double salary)

            String devFirstName = readNameFromConsole("developer's first name");
            String devLastName = readNameFromConsole("developer's last name");
            String devGender = readNameFromConsole("developer's gender");
            String devAddress = readNameFromConsole("developer's address");
            String devEmail = readNameFromConsole("developer's email");
            String devPhone = readNameFromConsole("developer's phone");
            String jobTitle = readNameFromConsole("developer's job title");
            Double salary = Double.parseDouble(readNameFromConsole("salary"));

            Developer newDev = new Developer(devFirstName, devLastName, devGender, devAddress, devEmail, devPhone, jobTitle, salary);
            devDAO.create(newDev);

            System.out.println("You have created the following developer:");
            System.out.println(newDev.toString());
    }

    private void createProject() {

        System.out.println("You will now be prompted to enter the information about the new project");

        // public Project(String projectName, String projectDescription)

        String projectName = readNameFromConsole("project name");
        String projectDescription = readNameFromConsole("project description");

        Project newProject = new Project(projectName, projectDescription);
        proDAO.create(newProject);

        System.out.println("You have created the following project:");
        System.out.println(newProject.toString());

    }

    private void createSkill(){

        System.out.println("You will now be prompted to enter the information about the new skill:");

        // public Project(String projectName, String projectDescription)

        String skillName = readNameFromConsole("skill name");
        String skillDescription = readNameFromConsole("skill description");

        Skill newSkill = new Skill(skillName, skillDescription);
        skDAO.create(newSkill);

        System.out.println("You have created the following skill:");
        System.out.println(newSkill.toString());

    }

    private void addDevToCo(){

    }

    private void addProjectToDev(){

    }

    private void addProjectToCustomer(){

    }

    private void addSkilltoDeveloper(){

    }

    private void updateCompany(){

        

    }

    private void updateCustomer(){

    }

    private void updateDeveloper(){

    }

    private void updateProject(){

    }

    private void updateSkill(){

    }

    private void readCompany() {

        String compName = readNameFromConsole("company name");
        System.out.println(compDAO.read(compName));

    }

    private void readCustomer(){

        int custId = Integer.parseInt(readNameFromConsole("customer ID"));
        System.out.println(custDAO.read(custId));

    }

    private void readDeveloper(){

        int devId = Integer.parseInt(readNameFromConsole("developer ID"));
        System.out.println(devDAO.read(devId));

    }

    private void readProject(){

        int projectId = Integer.parseInt(readNameFromConsole("project ID"));
        System.out.println(proDAO.read(projectId));

    }

    private void readSkill(){

        int skillId = Integer.parseInt(readNameFromConsole("skill ID"));
        System.out.println(skDAO.read(skillId));

    }

    private void deleteCompany(){

        int compId = Integer.parseInt(readNameFromConsole("company ID"));
        if (compDAO.delete(compId)){System.out.println("request completed");} else{
            System.out.println("The request could not be completed successfully");
        }

    }

    private void deleteCustomer(){

        int custId = Integer.parseInt(readNameFromConsole("customer ID"));
        if (custDAO.delete(custId)){System.out.println("request completed");} else{
            System.out.println("The request could not be completed successfully");
        }

    }

    private void deleteDeveloper(){
        int devId = Integer.parseInt(readNameFromConsole("developer ID"));
        if (devDAO.delete(devId)){System.out.println("request completed");} else{
            System.out.println("The request could not be completed successfully");
        }
    }

    private void deleteProject(){
        int projectId = Integer.parseInt(readNameFromConsole("project ID"));
        if (proDAO.delete(projectId)){System.out.println("request completed");} else{
            System.out.println("The request could not be completed successfully");
        }
    }

    private void deleteSkill(){

        int skillId = Integer.parseInt(readNameFromConsole("skill ID"));
        if (skDAO.delete(skillId)){System.out.println("request completed");} else{
            System.out.println("The request could not be completed successfully");
        }
    }

    private void DeleteDevFromCo(){


    }

    private void DeleteProFromDev(){

    }

    private void DeleteProFromCust(){

    }

    private void DeleteSkillFromDev(){

    }

    public static String readNameFromConsole(String wordDef) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = "";

        System.out.println("Please enter the " + wordDef);

        while (true) {
            try {
                text = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Incorrect input. Please enter the correct " + wordDef);
                continue;
            }
        }
        return text;
    }


}

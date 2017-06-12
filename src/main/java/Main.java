import Classes.*;
import Database.*;

import java.sql.SQLException;

/**
 * Created by guillaume on 6/6/17.
 */
public class Main {

    public static void main(String[] args) {

       System.out.println("Check if database exists, if not create it");

       if (!Utils.checkDBExists("homework11")) {
           try {
               Utils.createDB();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

        System.out.println("create company objects");

        Company ibm = new Company("IBM", "Palo Alto, USA", "International Business Machines, servers and clouds services");
        Company facebook = new Company("Facebook", "Silicon Valley, USA", "social media");
        Company webdev = new Company ("WebDesign", "Kiev, Ukraine", "website development");
        Company vk = new Company ("VK", "Moscow, Russia", "Russian social media");

        System.out.println("save objects to DB");
        DAOCompaniesImpl compDAO = new DAOCompaniesImpl();
        compDAO.create(ibm);
        compDAO.create(facebook);
        compDAO.create(webdev);
        compDAO.create(vk);

        System.out.println("update and read facebook");
        compDAO.update(facebook.getId(), vk);
        facebook = compDAO.read(facebook.getId());
        System.out.println(facebook.getCompanyName());

        System.out.println("delete duplicate vk");
        compDAO.delete(facebook.getId());

        System.out.println("create Customers");
        Customer claude = new Customer("Claude", "Paris, France", "+33145000000", "French consultant");
        Customer jf = new Customer ("Jean François", "Kiev, Ukraine", "+3804400000", "French embassy in Kiev");
        Customer framb = new Customer("Ambassade de France à Kiev", "Kiev, Ukraine", "+380440000", "French embassy in Kiev");
        Customer osmoun = new Customer("Osmoun", "Tashkent, Uzbekistan", "+3000000", "Uzbek tour operator");

        System.out.println("save them to DB");
        DAOCustomersImpl custDAO = new DAOCustomersImpl();
        custDAO.create(claude);
        custDAO.create(jf);
        custDAO.create(osmoun);
        custDAO.create(framb);

        System.out.println("Update JF in db and in memory");
        custDAO.update(jf.getCustomerId(),framb);
        jf = custDAO.read(jf.getCustomerId());
        System.out.println(jf.getCustomerName());

        System.out.println("delete embassy");
        custDAO.delete(framb.getCustomerId());


        System.out.println("create Developers");
        Developer charles = new Developer("Charles", "G", "M", "Kiev, Ukraine",
                "charles@charles.com", "+380444444444", "Web developer", 1500);
        Developer elena = new Developer ("Elena", "G", "F", "Kiev, Ukraine",
                "eg@eg.com","+3804400000","Web developer", 1500);
        Developer guillaume = new Developer("Guillaume", "G", "M", "Kiev, Ukraine",
                "gg@gg.com", "+38044000000", "JAVA developer", 2000);
        Developer igor = new Developer("Igor", "G", "M","Kiev, Ukraine",
                "igor@igor.com", "+380441111111","Senior Java Team Leader", 4000);


        System.out.println("saving them to DB");
        DAODevelopersImpl devDAO = new DAODevelopersImpl();
        devDAO.create(guillaume);
        devDAO.create(elena);
        devDAO.create(charles);
        devDAO.create(igor);

        System.out.println("Update charles in db and in memory");
        devDAO.update(charles.getDeveloperId(),guillaume);
        charles = devDAO.read(charles.getDeveloperId());
        System.out.println(charles.getFirstName());

        System.out.println("delete charles");
        devDAO.delete(charles.getDeveloperId());

        System.out.println("create skills");
        Skill java = new Skill("Java", "Java development");
        Skill web = new Skill("web","web development");
        Skill python = new Skill ("Python", "Python development");
        Skill jav = new Skill ("jav","error test");

        System.out.println("save skills to DB");
        DAOSkillsImpl skDAO = new DAOSkillsImpl();
        skDAO.create(java);
        skDAO.create(web);
        skDAO.create(python);
        skDAO.create(jav);

        System.out.println("update jav");
        skDAO.update(jav.getSkillId(),java);
        jav = skDAO.read(jav.getSkillId());
        System.out.println(jav.getSkillDescription());
        System.out.println("delete jav");
        skDAO.delete(jav.getSkillId());

        System.out.println("create projects");
        Project paysystem = new Project ("Pay System", "development of salary payment system");
        Project sitedev = new Project ("Site Dev", "development of 10 sites for one client");
        Project tool = new Project ("Analysis tool", "development of system to analyse data");
        Project sitesdev = new Project("sites dev", "dev dev dev");

        System.out.println("save to DB");
        DAOProjectsImpl prDAO = new DAOProjectsImpl();
        prDAO.create(paysystem);
        prDAO.create(sitedev);
        prDAO.create(tool);
        prDAO.create(sitesdev);

        System.out.println("update sitesdev");
        prDAO.update(sitesdev.getProjectId(), sitedev);
        sitesdev = prDAO.read(sitesdev.getProjectId());
        System.out.println(sitesdev.getProjectDescription());

        System.out.println("delete sitesdev");
        prDAO.delete(sitesdev.getProjectId());

        System.out.println("adding skills to Guillaume");
        // add java and python to guillaume
        skDAO.addSkillToDeveloper(guillaume.getDeveloperId(),java.getSkillId());
        skDAO.addSkillToDeveloper(guillaume.getDeveloperId(),python.getSkillId());

        System.out.println("adding skills to Igor");
        // add java, python, and webdev to Igor
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),java.getSkillId());
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),python.getSkillId());
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),web.getSkillId());

        System.out.println("adding skills to elena");
        // add webdev to Elena
        skDAO.addSkillToDeveloper(elena.getDeveloperId(),web.getSkillId());

        System.out.println("adding projects to developers");
        // add projects 1 and 3 to igor and guillaume
        System.out.println("adding projects to guillaume");
        prDAO.addProjectToDeveloper(paysystem.getProjectId(),guillaume.getDeveloperId());
        prDAO.addProjectToDeveloper(tool.getProjectId(),guillaume.getDeveloperId());
        System.out.println("adding projects to igor");
        prDAO.addProjectToDeveloper(paysystem.getProjectId(),igor.getDeveloperId());
        prDAO.addProjectToDeveloper(tool.getProjectId(),igor.getDeveloperId());

        // add project 2 to elena
        System.out.println("adding projects to elena");
        prDAO.addProjectToDeveloper(sitedev.getProjectId(),elena.getDeveloperId());

        System.out.println("adding projects to clients");
        // add projects to clients
        custDAO.addProjectToCustomer(paysystem.getProjectId(),vk.getId());
        custDAO.addProjectToCustomer(sitedev.getProjectId(),webdev.getId());
        custDAO.addProjectToCustomer(tool.getProjectId(),ibm.getId());

        // add developers to companies
        System.out.println("adding developers to companies");
        compDAO.addCompanyToDeveloper(ibm.getId(),igor.getDeveloperId());
        compDAO.addCompanyToDeveloper(vk.getId(),guillaume.getDeveloperId());
        compDAO.addCompanyToDeveloper(webdev.getId(), elena.getDeveloperId());

        System.out.println("testing skills");
        Skill english = new Skill("English", "fluent in English");
        skDAO.create(english);
        System.out.println(english.getSkillId() + ", " + english.getSkillName());


       /*DAOCompaniesImpl compDAO = new DAOCompaniesImpl();
       DAODevelopersImpl devDAO = new DAODevelopersImpl();
       DAOCustomersImpl custDAO = new DAOCustomersImpl();
       DAOProjectsImpl prDAO = new DAOProjectsImpl();
       DAOSkillsImpl skDAO = new DAOSkillsImpl();

       Company test = new Company("test", "test","test");
       compDAO.create(test);

         compDAO.delete(9);
         compDAO.delete(10);
         compDAO.delete(11);
         compDAO.delete(12);


         devDAO.delete(9);
         devDAO.delete(10);
         devDAO.delete(11);
         devDAO.delete(12);

         custDAO.delete(9);
         custDAO.delete(10);
         custDAO.delete(11);
         custDAO.delete(12);

         prDAO.delete(9);
         prDAO.delete(10);
         prDAO.delete(11);
         prDAO.delete(12);

         skDAO.delete(9);
         skDAO.delete(10);
         skDAO.delete(11);
         skDAO.delete(12);*/


    }
}

import Classes.*;
import Database.*;

import java.sql.SQLException;

/**
 * Created by guillaume on 6/6/17.
 */
public class Main {

    /*public static void main(String[] args) {

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

        System.out.println("delete jean francois");
        custDAO.delete(jf.getCustomerId());


        System.out.println("create Developers");
        Developer charles = new Developer("Charles", "G", "M", "Kiev, Ukraine",
                "charles@charles.com", "+380444444444", "Web developer", 1500);
        Developer elena = new Developer ("Elena", "G", "F", "Kiev, Ukraine",
                "eg@eg.com","+3804400000","Web developer", 1500);
        Developer guillaume = new Developer("Guillaume", "G", "M", "Kiev, Ukraine",
                "gg@gg.com", "+38044000000", "JAVA developer", 2000);
        Developer igor = new Developer("Igor", "G", "M","Kiev, Ukraine",
                "igor@igor.com", "+380441111111","Senior Java Team Leader", 4000);
        Developer guillaume2 = new Developer("Guillaume2", "G", "M", "Kiev, Ukraine",
                "gg@gg.com", "+38044000002", "JAVA developer", 1000);
     Developer igor2 = new Developer("Igor2", "G2", "M","Kiev, Ukraine",
             "igor@igor.com", "+380441111112","Java Developer", 2000);


        System.out.println("saving them to DB");
        DAODevelopersImpl devDAO = new DAODevelopersImpl();
        devDAO.create(guillaume);
        devDAO.create(elena);
        devDAO.create(charles);
        devDAO.create(igor);
        devDAO.create(guillaume2);
        devDAO.create(igor2);

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
        skDAO.addSkillToDeveloper(guillaume2.getDeveloperId(),java.getSkillId());
        skDAO.addSkillToDeveloper(guillaume2.getDeveloperId(),python.getSkillId());

        System.out.println("adding skills to Igor");
        // add java, python, and webdev to Igor
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),java.getSkillId());
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),python.getSkillId());
        skDAO.addSkillToDeveloper(igor.getDeveloperId(),web.getSkillId());

        skDAO.addSkillToDeveloper(igor2.getDeveloperId(),java.getSkillId());
        skDAO.addSkillToDeveloper(igor2.getDeveloperId(),python.getSkillId());
        skDAO.addSkillToDeveloper(igor2.getDeveloperId(),web.getSkillId());

        System.out.println("adding skills to elena");
        // add webdev to Elena
        skDAO.addSkillToDeveloper(elena.getDeveloperId(),web.getSkillId());

        System.out.println("adding projects to developers");
        // add projects 1 and 3 to igor and guillaume
        System.out.println("adding projects to guillaume");
        prDAO.addProjectToDeveloper(tool.getProjectId(),guillaume.getDeveloperId());
        prDAO.addProjectToDeveloper(tool.getProjectId(),guillaume2.getDeveloperId());
        System.out.println("adding projects to igor");
        prDAO.addProjectToDeveloper(paysystem.getProjectId(),igor.getDeveloperId());
        prDAO.addProjectToDeveloper(paysystem.getProjectId(),igor2.getDeveloperId());

        // add project 2 to elena
        System.out.println("adding projects to elena");
        prDAO.addProjectToDeveloper(sitedev.getProjectId(),elena.getDeveloperId());

        System.out.println("adding projects to clients");
        // add projects to clients
        custDAO.addProjectToCustomer(paysystem.getProjectId(),framb.getCustomerId());
        custDAO.addProjectToCustomer(sitedev.getProjectId(),osmoun.getCustomerId());
        custDAO.addProjectToCustomer(tool.getProjectId(),claude.getCustomerId());

        // add developers to companies
        System.out.println("adding developers to companies");
        compDAO.addCompanyToDeveloper(ibm.getId(),igor.getDeveloperId());
        compDAO.addCompanyToDeveloper(ibm.getId(),igor2.getDeveloperId());
        compDAO.addCompanyToDeveloper(vk.getId(),guillaume.getDeveloperId());
        compDAO.addCompanyToDeveloper(vk.getId(),guillaume2.getDeveloperId());
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

         compDAO.delete(13);
         compDAO.delete(14);
         compDAO.delete(15);
         compDAO.delete(16);
         compDAO.delete(17);
         compDAO.delete(18);

         devDAO.delete(13);
         devDAO.delete(14);
         devDAO.delete(15);
         devDAO.delete(16);

         custDAO.delete(13);
         custDAO.delete(14);
         custDAO.delete(15);
         //custDAO.delete(12);

         prDAO.delete(13);
         prDAO.delete(14);
         prDAO.delete(15);
         prDAO.delete(16);

         skDAO.delete(13);
         skDAO.delete(14);
         skDAO.delete(15);
         skDAO.delete(16);*/



    }
}

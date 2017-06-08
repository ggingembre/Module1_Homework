import Classes.*;
import Database.*;

/**
 * Created by guillaume on 6/6/17.
 */
public class Main {

    public static void main(String[] args) {

        // create company objects

        Company ibm = new Company(1, "IBM", "Palo Alto, USA", "International Business Machines, servers and clouds services");
        Company facebook = new Company(2, "Facebook", "Silicon Valley, USA", "social media");
        Company webdev = new Company (3, "WebDesign", "Kiev, Ukraine", "website development");
        Company vk = new Company (2, "VK", "Moscow, Russia", "Russian social media");

        // save objects to DB
        //DAOCompaniesImpl compDAO = new DAOCompaniesImpl();
        //compDAO.create(ibm);
        //compDAO.create(facebook);
        //compDAO.create(webdev);
        //compDAO.create(vk);

        // read IBM
        //compDAO.update(2, vk);
        //facebook = compDAO.read(2);

        //System.out.println(facebook.getCompanyName());

        // delete duplicate vk
        //compDAO.delete(4);

        // create Customers

        Customer claude = new Customer(1, "Claude", "Paris, France", "+33145000000", "French consultant");
        Customer jf = new Customer (2, "Jean François", "Kiev, Ukraine", "+3804400000", "French embassy in Kiev");
        Customer framb = new Customer(4,"Ambassade de France à Kiev", "Kiev, Ukraine", "+380440000", "French embassy in Kiev");
        Customer osmoun = new Customer(3, "Osmoun", "Tashkent, Uzbekistan", "+3000000", "Uzbek tour operator");

        // save them to DB

        DAOCustomersImpl custDAO = new DAOCustomersImpl();
        //custDAO.create(claude);
        //custDAO.create(jf);
        //custDAO.create(osmoun);
        //custDAO.create(framb);

        // Update JF in db and in memory
        //custDAO.update(2,framb);
        //jf = custDAO.read(2);
        //System.out.println(jf.getCustomerName());

        // delete embassy
        //custDAO.delete(4);


        // create Developers

        Developer guillaume = new Developer(3, "Guillaume", "G", "M", "Kiev, Ukraine",
                "gg@gg.com", "+38044000000", "JAVA developer", 2000);
        Developer elena = new Developer (2, "Elena", "G", "F", "Kiev, Ukraine",
                "eg@eg.com","+3804400000","Web developer", 1500);
        Developer charles = new Developer(1, "Charles", "G", "M", "Kiev, Ukraine",
                "charles@charles.com", "+380444444444", "Web developer", 1500);
        Developer igor = new Developer(4,"Igor", "G", "M","Kiev, Ukraine",
                "igor@igor.com", "+380441111111","Senior Java Team Leader", 4000);


        // save them to DB

        DAODevelopersImpl devDAO = new DAODevelopersImpl();
        //devDAO.create(guillaume);
        //devDAO.create(elena);
        //devDAO.create(charles);
        //devDAO.create(igor);

        // Update charles in db and in memory
        //devDAO.update(1,guillaume);
        //charles = devDAO.read(1);
        //System.out.println(charles.getFirstName());

        // delete guillaume
        //devDAO.delete(3);

        // create skills

        Skill java = new Skill(1,"Java", "Java development");
        Skill web = new Skill(2,"web","web development");
        Skill python = new Skill (3, "Python", "Python development");
        Skill jav = new Skill (4, "jav","error test");

        // save skills to DB
        DAOSkillsImpl skDAO = new DAOSkillsImpl();
        //skDAO.create(java);
        //skDAO.create(web);
        //skDAO.create(python);
        //skDAO.create(jav);

        // update jav

        //skDAO.update(4,java);
        //jav = skDAO.read(4);
        //System.out.println(jav.getSkillDescription());

        // delete jav
        //skDAO.delete(4);

        // create projects
        Project paysystem = new Project (1, "Pay System", "development of salary payment system");
        Project sitedev = new Project (2, "Site Dev", "development of 10 sites for one client");
        Project tool = new Project (3, "Analysis tool", "development of system to analyse data");
        Project sitesdev = new Project(4, "sites dev", "dev dev dev");

        // save to DB

        DAOProjectsImpl prDAO = new DAOProjectsImpl();
        //prDAO.create(paysystem);
        //prDAO.create(sitedev);
        //prDAO.create(tool);
        //prDAO.create(sitesdev);

        // update
        //prDAO.update(4, sitedev);
        //sitesdev = prDAO.read(4);
        //System.out.println(sitesdev.getProjectDescription());

        // delete
        //prDAO.delete(4);

    }
}

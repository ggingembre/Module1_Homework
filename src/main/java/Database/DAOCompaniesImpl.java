package Database;

import Classes.Company;
import Classes.Developer;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOCompaniesImpl implements DAOCompanies {



    public void create(Company company)
    {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();

        em.close();
        factory.close();

    }

    public boolean update(int compId, Company company)
    {
         boolean updated;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Company updateDev = em.find(Company.class, compId);

        updateDev.setCompanyName(company.getCompanyName());
        updateDev.setCompanyDescription(company.getCompanyDescription());
        updateDev.setCompanyAddress(company.getCompanyDescription());

        em.getTransaction().commit();

        em.close();
        factory.close();

        updated = true;

        return updated;

    }

    public Company read(int companyId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get customer to update
        Company result = em.find(Company.class, companyId);

        em.close();
        factory.close();

        return result;

    }

    public List<Company> readAll(){

        List<Company> all;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Company> typedQuery = em.createNamedQuery("Company.findAll", Company.class);

        all = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();
        factory.close();

        return all;
    }

    public boolean delete(int companyId){

        boolean deleted = false;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get project to update
        Company result = em.find(Company.class, companyId);

        if (result != null)
        {
            em.remove(result);
            em.getTransaction().commit();
            deleted = true;
        }

        em.close();
        factory.close();

        return deleted;

    }

    public void addCompanyToDeveloper(int compId, int devId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get objects from id
        Company company = em.find(Company.class, compId);

        Developer developer = em.find(Developer.class, devId);

        if ((company != null)&(developer != null)){
            developer.getCompanies().add(company);
            company.getDevelopers().add(developer);
            em.getTransaction().commit();
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");

        em.close();
        factory.close();

    }

 }
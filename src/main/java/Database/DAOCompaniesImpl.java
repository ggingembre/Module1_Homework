package Database;

import Classes.Company;
import Classes.Developer;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOCompaniesImpl implements DAOCompanies {



    public void create(Company company)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();

    }

    public boolean update(int compId, Company company)
    {
         boolean updated;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Company updateDev = em.find(Company.class, compId);

        updateDev.setCompanyName(company.getCompanyName());
        updateDev.setCompanyDescription(company.getCompanyDescription());
        updateDev.setCompanyAddress(company.getCompanyDescription());

        em.getTransaction().commit();

        updated = true;

        return updated;

    }

    public Company read(int companyId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get customer to update
        Company result = em.find(Company.class, companyId);

        return result;

    }

    public boolean delete(int companyId){

        boolean deleted = false;

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

        return deleted;

    }

    public void addCompanyToDeveloper(int compId, int devId){

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
    }

 }
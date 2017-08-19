package Database;

import Classes.Company;
import Classes.Developer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOCompaniesImpl implements DAOCompanies {

   private Session currentSession;
   private Transaction currentTransaction;

   private static SessionFactory getSessionFactory(){
       Configuration configuration = new Configuration().configure();
       StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
               .applySettings(configuration.getProperties());
       SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
       return sessionFactory;
   }

   public Session openCurrentSession (){
        currentSession = getSessionFactory().openSession();

       return currentSession;
   }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void create(Company company)
    {

        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        //EntityManager em = factory.createEntityManager();
        //Session session = (Session) em;
//
        //session.getTransaction().begin();

        //Session currentSession = sessionFactory.getCurrentSession();
        //currentSession.saveOrUpdate(company);

        //session.saveOrUpdate(company);
//
        //session.getTransaction().commit();
//
        //session.close();

        getCurrentSession().save(company);

    }

    public boolean update(int compId, Company company)
    {
         boolean updated;

        company.setId(compId);

        getCurrentSession().saveOrUpdate(company);

        updated = true;

        return updated;

    }

    public Company read(int companyId){

        Company company = getCurrentSession().get(Company.class, companyId);

        return company;

    }

    public boolean delete(int companyId){

        boolean deleted = false;

        Company result = getCurrentSession().get(Company.class, companyId);

            if (result != null)
            {
                getCurrentSession().delete(result);
                deleted = true;
            }

        return deleted;

    }

    public void addCompanyToDeveloper(int compId, int devId){

        Session currentSession = getCurrentSession();

        // get objects from id
        Company company = getCurrentSession().get(Company.class, compId);

        Developer developer = getCurrentSession().get(Developer.class, devId);

        if ((company != null)&(developer != null)){
            developer.getCompanies().add(company);
            company.getDevelopers().add(developer);
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }

 }
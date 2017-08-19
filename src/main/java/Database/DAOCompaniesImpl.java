package Database;

import Classes.Company;
import Classes.Developer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

 @Repository
 @Transactional
public class DAOCompaniesImpl implements DAOCompanies {

   @Autowired
   private SessionFactory sessionFactory;

     public void create(Company company)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(company);
    }

    public boolean update(int compId, Company company)
    {
         boolean updated;

        company.setId(compId);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(company);

        updated = true;

        return updated;

    }

    public Company read(int companyId){

        Session currentSession = sessionFactory.getCurrentSession();
        Company company = (Company) currentSession.createCriteria(Company.class)
                .add(Restrictions.idEq(companyId))
                .uniqueResult();
        return company;

    }

    public boolean delete(int companyId){

        boolean deleted = false;

            Session currentSession = sessionFactory.getCurrentSession();

            Company result = (Company) currentSession.createCriteria(Company.class)
                    .add(Restrictions.idEq(companyId))
                    .uniqueResult();

            if (result != null)
            {
                currentSession.delete(result);
                deleted = true;
            }

        return deleted;

    }

    public void addCompanyToDeveloper(int compId, int devId){

        Session currentSession = sessionFactory.getCurrentSession();

        // get objects from id
        Company company = (Company) currentSession.createCriteria(Company.class)
                .add(Restrictions.idEq(compId))
                .uniqueResult();

        Developer developer = (Developer) currentSession.createCriteria(Developer.class)
                .add(Restrictions.idEq(devId))
                .uniqueResult();

        if ((company != null)&(developer != null)){
            developer.getCompanies().add(company);
            company.getDevelopers().add(developer);
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }

}
package Database;

import Classes.Customer;

import Classes.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@Transactional

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOCustomersImpl implements DAOCustomers {

    //@Autowired
    SessionFactory sessionFactory;

    public void create(Customer customer) {

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);

    }


    public boolean update (int customerId, Customer customer){

        boolean updated;

        customer.setCustomerId(customerId);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);

        updated = true;

        return updated;

    }

    public Customer read(int customerId) {

        Session currentSession = sessionFactory.getCurrentSession();
        Customer result = (Customer) currentSession.createCriteria(Customer.class)
                .add(Restrictions.eq("company_id", customerId))
                .uniqueResult();
        return result;

    }

    public boolean delete(int customerId){

        boolean deleted = false;

        Session currentSession = sessionFactory.getCurrentSession();

        Customer result = (Customer) currentSession.createCriteria(Customer.class)
                .add(Restrictions.idEq(customerId))
                .uniqueResult();

        if (result != null)
        {
            currentSession.delete(result);
            deleted = true;
        }

        return deleted;

    }

    public void addProjectToCustomer(int projectId, int custId){


        Session currentSession = sessionFactory.getCurrentSession();

        // get objects from id
        Customer customer = (Customer) currentSession.createCriteria(Customer.class)
                .add(Restrictions.idEq(custId))
                .uniqueResult();

        Project project = (Project) currentSession.createCriteria(Project.class)
                .add(Restrictions.idEq(projectId))
                .uniqueResult();

        if ((customer != null)&(project != null)){
            customer.getProjects().add(project);
            project.getCustomers().add(customer);
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }


}

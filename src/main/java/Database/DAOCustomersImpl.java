package Database;

import Classes.Customer;

import Classes.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOCustomersImpl implements DAOCustomers {

    public void create(Customer customer) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }


    public boolean update (int customerId, Customer customer){

        boolean updated;

        customer.setCustomerId(customerId);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get customer to update
        Customer update = em.find(Customer.class, customerId);

        update.setCustomerName(customer.getCustomerName());
        update.setCustomerAddress(customer.getCustomerAddress());
        update.setCustomerPhone(customer.getCustomerPhone());
        update.setCustomerDescription(customer.getCustomerDescription());

        em.getTransaction().commit();


        updated = true;

        return updated;

    }

    public Customer read(int customerId) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get customer to update
        Customer result = em.find(Customer.class, customerId);

        return result;

    }

    public boolean delete(int customerId){

        boolean deleted = false;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get project to update
        Customer result = em.find(Customer.class, customerId);

        if (result != null)
        {
            em.remove(result);
            em.getTransaction().commit();
            deleted = true;
        }

        return deleted;

    }

    public void addProjectToCustomer(int projectId, int custId){


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get objects from id
        Project project = em.find(Project.class, projectId);

        Customer customer = em.find(Customer.class, custId);


        if ((customer != null)&(project != null)){
            customer.getProjects().add(project);
            project.getCustomers().add(customer);
            em.getTransaction().commit();
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }


}

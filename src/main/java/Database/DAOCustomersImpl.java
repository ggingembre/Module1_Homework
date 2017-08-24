package Database;

import Classes.Customer;

import Classes.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;


/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOCustomersImpl implements DAOCustomers {

    public void create(Customer customer) {


        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }


    public boolean update (int customerId, Customer customer){

        boolean updated;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

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

        em.close();
        factory.close();

        updated = true;

        return updated;

    }

    public Customer read(int customerId) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get customer to update
        Customer result = em.find(Customer.class, customerId);

        em.close();
        factory.close();

        return result;

    }

    public List<Customer> readAll(){

        List<Customer> all;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Customer> typedQuery = em.createNamedQuery("Customer.findAll", Customer.class);

        all = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();
        factory.close();

        return all;
    }


    public boolean delete(int customerId){

        boolean deleted = false;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

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

        em.close();
        factory.close();

        return deleted;

    }

    public void addProjectToCustomer(int projectId, int custId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

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

        em.close();
        factory.close();

    }

}

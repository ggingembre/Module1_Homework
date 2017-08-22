package Database;

import Classes.Developer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;

/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAODevelopersImpl implements DAODevelopers {


    public void create(Developer developer){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(developer);
        em.getTransaction().commit();

        em.close();
        factory.close();
    }


    public boolean update (int developerId, Developer developer){

        boolean updated;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Developer updateDev = em.find(Developer.class, developerId);

        updateDev.setFirstName(developer.getFirstName());
        updateDev.setSalary(developer.getSalary());
        updateDev.setJobTitle(developer.getJobTitle());
        updateDev.setEmail(developer.getEmail());
        updateDev.setAddress(developer.getAddress());
        updateDev.setGender(developer.getGender());
        updateDev.setLastName(developer.getLastName());
        updateDev.setPhone(developer.getPhone());

        em.getTransaction().commit();

        em.close();
        factory.close();

        updated = true;

        return updated;

    }


    public Developer read(int developerId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Developer result = em.find(Developer.class, developerId);

        em.close();
        factory.close();

        return result;
    }


    public boolean delete(int developerId){

        boolean deleted = false;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Developer result = em.find(Developer.class, developerId);

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
}
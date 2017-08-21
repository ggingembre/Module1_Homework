package Database;

import Classes.Developer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAODevelopersImpl implements DAODevelopers {


    public void create(Developer developer){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(developer);
        em.getTransaction().commit();
    }


    public boolean update (int developerId, Developer developer){

        boolean updated;

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

        updated = true;

        return updated;

    }


    public Developer read(int developerId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Developer result = em.find(Developer.class, developerId);

        return result;
    }


    public boolean delete(int developerId){

        boolean deleted = false;

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

        return deleted;

    }
}
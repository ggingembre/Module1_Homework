package Database;

import Classes.Developer;
import Classes.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@Transactional
//
/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOSkillsImpl implements DAOSkills {

    //@Autowired
    SessionFactory sessionFactory;

    public void create(Skill skill){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();

        em.close();
        factory.close();

    }


    public boolean update (int skillId, Skill skill){

        boolean updated;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Skill update = em.find(Skill.class, skillId);

        update.setSkillDescription(skill.getSkillDescription());
        update.setSkillName(skill.getSkillName());

        em.getTransaction().commit();

        updated = true;

        em.close();
        factory.close();

        return updated;

    }


    public Skill read(int skillId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Skill result = em.find(Skill.class, skillId);

        em.close();
        factory.close();

        return result;

    }


    public List<Skill> readAll(){

        List<Skill> all;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Skill> typedQuery = em.createNamedQuery("Skill.findAll", Skill.class);

        all = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();
        factory.close();

        return all;
    }

    public boolean delete(int skillId){

        boolean deleted = false;

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get skill to update
        Skill result = em.find(Skill.class, skillId);

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


    public void addSkillToDeveloper(int devId, int skillId){

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get objects from id
        Skill skill = em.find(Skill.class, skillId);

        Developer developer = em.find(Developer.class, devId);

        if ((skill != null)&(developer != null)){
            skill.getDevelopers().add(developer);
            developer.getSkills().add(skill);
            em.getTransaction().commit();
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");

        em.close();
        factory.close();

    }

}

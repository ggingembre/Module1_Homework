package Database;

import Classes.Developer;
import Classes.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();

    }


    public boolean update (int skillId, Skill skill){

        boolean updated;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Skill update = em.find(Skill.class, skillId);

        update.setSkillDescription(skill.getSkillDescription());
        update.setSkillName(skill.getSkillName());

        em.getTransaction().commit();

        updated = true;

        return updated;

    }


    public Skill read(int skillId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Skill result = em.find(Skill.class, skillId);

        return result;

    }


    public boolean delete(int skillId){

        boolean deleted = false;

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

        return deleted;

    }


    public void addSkillToDeveloper(int devId, int skillId){

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
    }

}

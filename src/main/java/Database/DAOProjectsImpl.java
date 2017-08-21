package Database;

import Classes.Developer;
import Classes.Project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by guillaume on 6/8/17.
 */

// how to do this with sessionfactory?
// Session currentSession = sessionFactory.getCurrentSession();
// the code seems much cleaner with only session factory, but for some reason I could not get it to work

public class DAOProjectsImpl implements DAOProjects {


    public void create(Project project){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();

    }

    public boolean update (int projectId, Project project){


        boolean updated;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Project update = em.find(Project.class, projectId);

        update.setProjectDescription(project.getProjectDescription());
        update.setProjectName(project.getProjectName());

        em.getTransaction().commit();

        updated = true;

        return updated;

    }


    public Project read(int projectId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get developer to update
        Project result = em.find(Project.class, projectId);

        return result;

    }


    public boolean delete(int projectId){

        boolean deleted = false;

        // Session currentSession = sessionFactory.getCurrentSession();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get project to update
        Project result = em.find(Project.class, projectId);

        if (result != null)
        {
            em.remove(result);
            em.getTransaction().commit();
            deleted = true;
        }

        return deleted;

    }


    public void addProjectToDeveloper(int projectId, int devId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // get objects from id
        Project project = em.find(Project.class, projectId);

        Developer developer = em.find(Developer.class, devId);

        if ((project != null)&(developer != null)){
            project.getDevelopers().add(developer);
            developer.getProjects().add(project);
            em.getTransaction().commit();
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }

}

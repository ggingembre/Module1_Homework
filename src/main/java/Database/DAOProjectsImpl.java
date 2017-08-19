package Database;

import Classes.Developer;
import Classes.Project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional



/**
 * Created by guillaume on 6/8/17.
 */

public class DAOProjectsImpl implements DAOProjects {

    @Autowired
    SessionFactory sessionFactory;

    public void create(Project project){

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(project);

    }

    public boolean update (int projectId, Project project){


        boolean updated;

        project.setProjectId(projectId);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(project);

        updated = true;

        return updated;

    }


    public Project read(int projectId){

        Session currentSession = sessionFactory.getCurrentSession();
        Project result = (Project) currentSession.createCriteria(Project.class)
                .add(Restrictions.eq("company_id", projectId))
                .uniqueResult();
        return result;

    }


    public boolean delete(int projectId){

        boolean deleted = false;

        Session currentSession = sessionFactory.getCurrentSession();

        Project result = (Project) currentSession.createCriteria(Project.class)
                .add(Restrictions.idEq(projectId))
                .uniqueResult();

        if (result != null)
        {
            currentSession.delete(result);
            deleted = true;
        }

        return deleted;

    }


    public void addProjectToDeveloper(int projectId, int devId){


        Session currentSession = sessionFactory.getCurrentSession();

        // get objects from id
        Project project = (Project) currentSession.createCriteria(Project.class)
                .add(Restrictions.idEq(projectId))
                .uniqueResult();

        Developer developer = (Developer) currentSession.createCriteria(Developer.class)
                .add(Restrictions.idEq(devId))
                .uniqueResult();

        if ((project != null)&(developer != null)){
            project.getDevelopers().add(developer);
            developer.getProjects().add(project);
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }

}

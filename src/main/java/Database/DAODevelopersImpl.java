package Database;

import Classes.Developer;

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
public class DAODevelopersImpl implements DAODevelopers {

    //@Autowired
    SessionFactory sessionFactory;

    public void create(Developer developer){

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(developer);

    }


    public boolean update (int developerId, Developer developer){

        boolean updated;

        developer.setDeveloperId(developerId);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(developer);

        updated = true;

        return updated;

    }


    public Developer read(int developerId){

        Session currentSession = sessionFactory.getCurrentSession();
        Developer result = (Developer) currentSession.createCriteria(Developer.class)
                .add(Restrictions.eq("company_id", developerId))
                .uniqueResult();
        return result;
    }


    public boolean delete(int developerId){

        boolean deleted = false;

        Session currentSession = sessionFactory.getCurrentSession();

        Developer result = (Developer) currentSession.createCriteria(Developer.class)
                .add(Restrictions.idEq(developerId))
                .uniqueResult();

        if (result != null)
        {
            currentSession.delete(result);
            deleted = true;
        }

        return deleted;

    }
}

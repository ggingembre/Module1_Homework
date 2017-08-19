package Database;

import Classes.Developer;
import Classes.Skill;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
public class DAOSkillsImpl implements DAOSkills {

    //@Autowired
    SessionFactory sessionFactory;

    public void create(Skill skill){

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(skill);

    }


    public boolean update (int skillId, Skill skill){

        boolean updated;

        skill.setSkillId(skillId);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(skill);

        updated = true;

        return updated;

    }


    public Skill read(int skillId){

        Session currentSession = sessionFactory.getCurrentSession();
        Skill result = (Skill) currentSession.createCriteria(Skill.class)
                .add(Restrictions.eq("company_id", skillId))
                .uniqueResult();

        return result;

    }


    public boolean delete(int skillId){

        boolean deleted = false;

        Session currentSession = sessionFactory.getCurrentSession();

        Skill result = (Skill) currentSession.createCriteria(Skill.class)
                .add(Restrictions.idEq(skillId))
                .uniqueResult();

        if (result != null)
        {
            currentSession.delete(result);
            deleted = true;
        }

        return deleted;

    }


    public void addSkillToDeveloper(int devId, int skillId){

        Session currentSession = sessionFactory.getCurrentSession();

        // get objects from id
        Skill skill = (Skill) currentSession.createCriteria(Skill.class)
                .add(Restrictions.idEq(skillId))
                .uniqueResult();

        Developer developer = (Developer) currentSession.createCriteria(Developer.class)
                .add(Restrictions.idEq(devId))
                .uniqueResult();

        if ((skill != null)&(developer != null)){
            skill.getDevelopers().add(developer);
            developer.getSkills().add(skill);
            System.out.println("Operation successfully completed!");
        } else System.out.println("operation aborted, one parameter is null");
    }

}

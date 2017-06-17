package Database;

import Classes.Developer;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAODevelopers {

    void create(Developer developer);
    boolean update (int developerId, Developer developer);
    Developer read(int developerId);
    boolean delete(int developerId);

}

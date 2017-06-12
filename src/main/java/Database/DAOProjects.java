package Database;

import Classes.Project;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOProjects {

    void create(Project project);
    void update (int projectId, Project project);
    Project read(int projectId);
    void delete(int projectId);
    void addProjectToDeveloper(int projectId, int devId);

}

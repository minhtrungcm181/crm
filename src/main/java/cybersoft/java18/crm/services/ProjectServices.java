package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.ProjectModel;
import cybersoft.java18.crm.repository.ProjectRepository;

import java.util.List;


public class ProjectServices {
    private static ProjectServices INSTANCE = null;
    private ProjectRepository projectRepository = new ProjectRepository();
    public static ProjectServices getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ProjectServices();
        }

        return INSTANCE;
    }
    public List<ProjectModel> getAllProJect(){
        return projectRepository.getAllProject();
    }
    public Integer deleteProjectById(String id){
        return projectRepository.deleteProject(id);
    }
    public Integer updateProject(ProjectModel projectModel) {
        return projectRepository.updateProject(projectModel);
    }
    public Integer saveProJect(ProjectModel projectModel)
    {
        return projectRepository.saveProject(projectModel);
    }

}

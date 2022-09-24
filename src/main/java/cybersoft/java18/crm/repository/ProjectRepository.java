package cybersoft.java18.crm.repository;

import cybersoft.java18.crm.model.ProjectModel;
import cybersoft.java18.crm.model.RoleModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository extends AbstractRepository<ProjectModel>{
    public List<ProjectModel> getAllProject(){
        return excuteQuery((connection)->{
            List<ProjectModel> projectModels = new ArrayList<>();

            String query = "select * from jobs";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               ProjectModel projectModel = new ProjectModel();
                projectModel.setId(resultSet.getInt("id"));
                projectModel.setName(resultSet.getString("name"));
                projectModel.setStartDate(getDateTimeFromResultSet("start_date",resultSet));
                projectModel.setEndDate(getDateTimeFromResultSet("end_date", resultSet));

                projectModels.add(projectModel);
            }

            return projectModels;
        });
    }
    public Integer deleteProject(String id){
        return excuteSaveAndUpdate((connection)->{
            String query = "delete from jobs where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);

            return preparedStatement.executeUpdate();
        });
    }
    public Integer saveProject (ProjectModel projectModel) {
        return excuteSaveAndUpdate((connection) -> {
            String query = "insert into jobs(name, id, start_date, end_date) value(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, projectModel.getName());
            preparedStatement.setInt(2, projectModel.getId());
            preparedStatement.setTimestamp(3, Timestamp.from(
                    projectModel.getStartDate().toInstant(ZoneOffset.of("+07:00")))
            );
            preparedStatement.setTimestamp(3, Timestamp.from(
                    projectModel.getEndDate().toInstant(ZoneOffset.of("+07:00")))
            );
            return preparedStatement.executeUpdate();
        });
    }

    public Integer updateProject(ProjectModel projectModel){
        return excuteSaveAndUpdate((connection) -> {
            String query = "update jobs set name=? ,start_date=?, end_date=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,projectModel.getName());

            preparedStatement.setTimestamp(2,Timestamp.from(
                    projectModel.getStartDate().toInstant(ZoneOffset.of("+07:00"))));

            preparedStatement.setTimestamp(3,Timestamp.from(
                    projectModel.getEndDate().toInstant(ZoneOffset.of("+07:00"))));
            preparedStatement.setInt(4,projectModel.getId());

            return  preparedStatement.executeUpdate();
        });
    }

    private LocalDateTime getDateTimeFromResultSet(String columnName, ResultSet resultSet) {
        Timestamp time;

        try {
            time = resultSet.getTimestamp(columnName);
        } catch (SQLException e) {
            return null;
        }

        if (time == null)
            return null;

        return time.toLocalDateTime();
    }
}

package cybersoft.java18.crm.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProjectModel {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

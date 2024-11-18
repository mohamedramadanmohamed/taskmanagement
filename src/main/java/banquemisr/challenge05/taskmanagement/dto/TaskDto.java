package banquemisr.challenge05.taskmanagement.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private int priority;
    private LocalDateTime dueDate;
}

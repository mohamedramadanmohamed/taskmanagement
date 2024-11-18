package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.model.Task;
import banquemisr.challenge05.taskmanagement.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContaining(String title);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByDueDateBefore(LocalDateTime dueDate);

    List<Task> findByTitleContainingAndStatus(String title, String status);

}

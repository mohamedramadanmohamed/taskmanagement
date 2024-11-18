// TaskService.java
package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById(Long id);
    TaskDto updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
    List<TaskDto> getAllTasks(int page, int size);
    List<TaskDto> searchTasks(String title, String status, LocalDateTime dueDate);
    List<TaskDto> findByTitleContaining(String title);
    List<TaskDto> findByStatus(TaskStatus status);

}

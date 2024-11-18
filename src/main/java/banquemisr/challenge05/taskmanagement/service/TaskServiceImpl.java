package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.model.Task;
import banquemisr.challenge05.taskmanagement.model.TaskStatus;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus().toUpperCase()));
        task.setPriority(taskDto.getPriority());
        task.setDueDate(taskDto.getDueDate());

        task = taskRepository.save(task);
        return convertToDto(task);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
        return convertToDto(task);
    }

    @Override
    @Transactional
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus().toUpperCase()));
        task.setPriority(taskDto.getPriority());
        task.setDueDate(taskDto.getDueDate());

        task = taskRepository.save(task);
        return convertToDto(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
        taskRepository.delete(task);
    }

    @Override
    public List<TaskDto> getAllTasks(int page, int size) {
        return taskRepository.findAll().stream()
                .skip(page * size)
                .limit(size)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> searchTasks(String title, String status, LocalDateTime dueDate) {
        List<Task> tasks;

        if (title != null && status != null) {
            tasks = taskRepository.findByTitleContainingAndStatus(title, TaskStatus.valueOf(status.toUpperCase()).toString());
        } else if (dueDate != null) {
            tasks = taskRepository.findByDueDateBefore(dueDate);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByTitleContaining(String title) {
        // Fetch tasks by title containing the given keyword
        List<Task> tasks = taskRepository.findByTitleContaining(title);

        // Convert to DTOs and return
        return tasks.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public List<TaskDto> findByStatus(TaskStatus status) {
        // Fetch tasks by status
        List<Task> tasks = taskRepository.findByStatus(status);

        // Convert to DTOs and return
        return tasks.stream()
                .map(this::convertToDto)
                .toList();
    }

    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus().toString());
        taskDto.setPriority(task.getPriority());
        taskDto.setDueDate(task.getDueDate());
        return taskDto;
    }
}

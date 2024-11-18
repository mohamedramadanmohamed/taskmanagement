package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.model.TaskStatus;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only admins can create tasks
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Both users and admins can view a task
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Only admins can update tasks
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(taskService.getAllTasks(page, size));
    }

    // Delete a task
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only admins can delete tasks
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Update a task
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only admins can update tasks
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }

    // Find tasks by title (search functionality)
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Accessible to both users and admins
    public ResponseEntity<List<TaskDto>> searchTasksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(taskService.findByTitleContaining(title));
    }

    // Find tasks by status
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Accessible to both users and admins
    public ResponseEntity<List<TaskDto>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.findByStatus(status));
    }

    // Search tasks with optional filters: title, status, and dueDate
    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Accessible to both users and admins
    public ResponseEntity<List<TaskDto>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
        return ResponseEntity.ok(taskService.searchTasks(title, status, dueDate));
    }


}

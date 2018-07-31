package com.services;


import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.Task;
import com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    TaskRepository taskRepository;


    //findAll task method
    public List<Task> findAllTasks() throws Exception {

        List<Task> tasks;

        try {

            tasks = (List<Task>) taskRepository.findAll();
        } catch (Exception e) {

            throw e;
        }

        return tasks;

    }

    //create task
    public Task createTask(Task task) throws CustomDatabaseException {
        taskRepository.save(task);
        Task t = taskRepository.findByDescription(task.getDescription());

        return t;
    }

    //updated method
    public Task taskcompleted(Task task) throws CustomDatabaseException {

        try {
            int i = taskRepository.updateTask(task.getName(), task.getDescription(), task.isCompleted(), task.getId());

            if (i < 1) {

                throw new CustomDatabaseException("Unable to update the task");
            }

            return taskRepository.findOne(task.getId());
        } catch (Exception e) {

            throw new CustomDatabaseException(e.getMessage());
        }

    }

    public boolean deleteTasksById(long id) throws CustomDatabaseException {

        Task task;
        try {
            taskRepository.delete(id);

            return true;

        } catch (Exception e) {
            throw e;
        }

    }

}

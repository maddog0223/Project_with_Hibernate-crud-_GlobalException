package com.services;


import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.Task;
import com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    TaskRepository taskRepository;


    //findAll task method
    @Cacheable(value = "tasks")
    public List<Task> findAllTasks() throws Exception {

        List<Task> tasks;

        try {

            tasks = (List<Task>) taskRepository.findAll();
        } catch (Exception e) {

            throw e;
        }

        return tasks;

    }

    // get by task id
    @Cacheable(value = "tasks", key = "#id")
    public Task findTaskById(long id) throws Exception {

        Task task;

        try {

            task = taskRepository.findOne(id);
        } catch (Exception e) {

            throw e;
        }

        return task;

    }

    //create task
//    @CacheEvict(allEntries = true) //Evict is another way clears all data
    @CachePut(value = "tasks", key = "#task.id")
    public Task createTask(Task task) throws CustomDatabaseException {
        taskRepository.save(task);
        Task t = taskRepository.findByDescription(task.getDescription());

        return t;
    }

    //update method
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

    //delete task
    @CacheEvict(value = "tasks", key = "#id")
    public boolean deleteTasksById(long id) throws CustomDatabaseException {

        Task task;
        try {
            taskRepository.delete(id);

            return true;

        } catch (Exception e) {
            throw e;
        }

    }

//    @Cacheable(value = "tasks", key = "{#userId, #todo}")
//    public List<Task> findAllByUserId(long userId, boolean todo) throws CustomDatabaseException {
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//
//            System.out.println("Sleep Error");
//        }
//
//        
//        if(todo){
//
//            return taskRepository.findByUserId(userId);
//
//            return taskRepository.find
//
//
//        }
    }

}


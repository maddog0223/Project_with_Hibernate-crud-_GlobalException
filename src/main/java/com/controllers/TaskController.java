package com.controllers;


import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.Task;
import com.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    //get task
    @GetMapping
    public CustomResponseObject getTask() throws Exception{

        List<Task> tasks = taskService.findAllTasks();
        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(tasks);
        obj.setStatusCode(200);

        return obj;

    }

    //create task
    @PostMapping
    public CustomResponseObject createTask(@Valid @RequestBody Task task) throws Exception{

       Task t = taskService.createTask(task);

        CustomResponseObject obj = new CustomResponseObject();

        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }



    //put task
    @PutMapping
    public CustomResponseObject completedTask(@Valid @RequestBody Task taskupdate) throws CustomDatabaseException {

        Task task = taskService.taskcompleted(taskupdate);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }

    //delete task
    @DeleteMapping("/{id}")
    public CustomResponseObject<String> deleteTask(@PathVariable("id") long id) throws CustomDatabaseException {

       boolean task = taskService.deleteTasksById(id);
        CustomResponseObject obj = new CustomResponseObject();
       if (task){

           obj.setData(  "id task " + id + " has be deleted");
           obj.setStatusCode(200);

           return obj;
       }

        throw new  CustomDatabaseException("Unable to delete task");

    }


    // With the user CRUD with the user class

    @GetMapping("/{firstname}")
    public CustomResponseObject<Task> getByUsersName
            (@PathVariable("firstname") String firstname) throws CustomDatabaseException{


    }
}

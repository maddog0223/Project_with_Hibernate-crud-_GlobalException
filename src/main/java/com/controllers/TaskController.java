package com.controllers;


import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.Task;
import com.model.User;
import com.services.TaskService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    //get task
    @GetMapping
    public CustomResponseObject getTask() throws Exception {

        List<Task> tasks = taskService.findAllTasks();
        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(tasks);
        obj.setStatusCode(200);

        return obj;

    }

    //Get by id
    @GetMapping("/{id}")
    public CustomResponseObject<Task> findUserById(@PathVariable("id") long id) throws CustomDatabaseException {

        Task gettask = taskService.findTaskById(id);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(gettask);
        obj.setStatusCode(200);

        return obj;
    }


    //create task
    @PostMapping
    public CustomResponseObject createTask(@Valid @RequestBody Task task) throws Exception {

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
        if (task) {

            obj.setData("id task " + id + " has be deleted");
            obj.setStatusCode(200);

            return obj;
        }

        throw new CustomDatabaseException("Unable to delete task");

    }


    // Get id with the user class

    @GetMapping("/user/{id}")
    public CustomResponseObject<Task> getByUsersId
            (@PathVariable("id") long id) throws CustomDatabaseException {

        Task task = new Task();
        User user = new User();

        long taskid = task.getId();
        long userid = user.getId();

        id = userid;
        CustomResponseObject obj = new CustomResponseObject();

        if (id == taskid){

           taskService.findTaskById(id);

            obj.setData(task);
            obj.setStatusCode(200);

            return obj;


        }

        throw new CustomDatabaseException("Unable to identify user/task id");

    }

}

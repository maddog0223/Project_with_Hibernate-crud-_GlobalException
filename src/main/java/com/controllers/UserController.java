package com.controllers;


import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.User;
import com.repository.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;


    // Getting and executing all the total ids
    @GetMapping
    public CustomResponseObject getUsers() throws Exception {

        List<User> users = userService.findAllUsers();
        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(users);
        obj.setStatusCode(200);

        return obj;

    }

    // Getting and showing the output of the specific type if id you write in the ur)
    @GetMapping("/{id}")
    public CustomResponseObject<User> findUserById(@PathVariable("id") long id) throws Exception {

        User user = userService.findUserById(id);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(user);
        obj.setStatusCode(200);

        return obj;
    }

    //Post
    @PostMapping
    public CustomResponseObject<User> createUser(@Valid @RequestBody User user) throws Exception {

        User u = userService.createUser(user);

        CustomResponseObject obj = new CustomResponseObject();

        obj.setData(user);
        obj.setStatusCode(200);

        return obj;
    }

//    @PutMapping("/update/{id}")
//    public CustomResponseObject<User> updateUser1(@PathVariable("id")long id,
//                                                 @Valid @RequestBody User userupdate) throws Exception{
//        User user = userService.updateUserById(id);
//
//        CustomResponseObject obj = new CustomResponseObject();
//        obj.setData(user);
//        obj.setStatusCode(200);
//
//        user.setFirst_name(userupdate.getFirst_name());
//        user.setLast_name(userupdate.getLast_name());
//        user.setEmail(userupdate.getEmail());
//
//
//        return obj;
//    }

    @PutMapping
    public CustomResponseObject<User> updateUser(@Valid @RequestBody User user) throws CustomDatabaseException {

        User u = userService.updateUser(user);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(u);
        obj.setStatusCode(200);

        return obj;
    }

    @DeleteMapping("/{id}")
    public CustomResponseObject<String> deleteUser(@PathVariable("id") long id) throws CustomDatabaseException {


        boolean success = userService.deleteUser(id);
        CustomResponseObject<String> obj = new CustomResponseObject();

        if (success) {

            obj.setData("User Successfully Deleted");
            obj.setStatusCode(200);

            return obj;

        }
        throw new  CustomDatabaseException("Unable to delete user");
    }


}

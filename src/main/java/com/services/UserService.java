package com.services;


import com.exceptions.CustomDatabaseException;
import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() throws Exception {

        List<User> users;

        try {
         users = (List<User>) userRepository.findAll();
        } catch (Exception e ){

            throw e;
        }

        return users;
    }

    public User findUserById(long id) throws Exception{

        User u;

        try{

            u = userRepository.findOne(id);
        }catch (Exception e){

            throw e;
        }

        return u;
    }

    public User createUser(User user) throws Exception{

            userRepository.save(user);
            User u = userRepository.findByEmail(user.getEmail());
            return u;

    }

//    public User updateUserById(long id) throws Exception {
//
//        User u = userRepository.findOne(id);
//
//            u.setFirst_name(u.getFirst_name());
//            u.setLast_name(u.getLast_name());
//            u.setEmail(u.getEmail());
//
//        userRepository.save(u);
//
//        return  u;
//    }

    public User updateUser(User user) throws CustomDatabaseException {

        try {
            int i = userRepository.updateUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getId());

            if (i < 1) {

                throw new CustomDatabaseException("Unable to update user");
            }

            return userRepository.findOne(user.getId());
        }catch (Exception e){

            throw new CustomDatabaseException(e.getMessage());
        }
    }

    public boolean deleteUser(long id) throws CustomDatabaseException{

        try {
            userRepository.delete(id);
            return true;
        }catch (Exception e){
            throw e;
        }
    }
}

package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update tasks_app.users set first_name =?1, last_name = ?2, email=?3 where id = ?4", nativeQuery = true)
    int updateUser(String firstName, String lastName, String email, long id);  // int stands for the number of ROWS/FIELDS affected in a query
}

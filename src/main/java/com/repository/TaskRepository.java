package com.repository;

import com.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


    Task findByDescription(String description);

    @Modifying
    @Transactional
    @Query(value = "update tasks_app.tasks set name =?1, description = ?2, completed = ?3, id = ?4 where id = ?4", nativeQuery = true)
    int updateTask(String name, String description, boolean completedcheck, long id);

    //delete query
    @Query(value = "delete from tasks_app.tasks name =?1, description = ?2, id = ?3 where completed = ?4", nativeQuery = true)
    int deleteTask(String name, String description,long id, boolean completedcheck);
}


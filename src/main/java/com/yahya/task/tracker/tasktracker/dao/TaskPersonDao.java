package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPersonDao extends JpaRepository<TaskPerson, Integer> {

    void deleteAllByUserProfileId(Integer id);
    void  deleteAllByTaskId(Integer id);

    List<TaskPerson> findAllByUserProfileId(Integer userProfileId);

}

package com.example.PostawIWygraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PostawIWygraj.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}

package com.example.PostawIWygraj.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Task;
import com.example.PostawIWygraj.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Task> getTaskByIdUser(Long id) {

	return em.createQuery("from Task where idUser = :user_id and idStatus !=:status_id").setParameter("user_id", id)
		.setParameter("status_id", 3).getResultList();
    }

    @Override
    public void save(Task task) {
	task.setCreatedDate(new Date());
	taskRepository.save(task);

    }

    @Override
    public Task completedTask(Long id) {
	Task task = null;
	if (id != null) {
	    task = getTaskById(id);
	    if (task != null) {
		task.setIdStatus(2);
		taskRepository.save(task);
	    }
	}
	return task;

    }

    @Override
    public Task removeTask(Long id) {
	Task task = null;
	if (id != null) {
	    task = getTaskById(id);
	    if (task != null) {
		task.setIdStatus(3);
		taskRepository.save(task);
	    }
	}
	return task;

    }

    @Override
    public Task getTaskById(Long id) {
	return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task toDoTask(Long id) {
	Task task = null;
	if (id != null) {
	    task = getTaskById(id);
	    if (task != null) {
		task.setIdStatus(1);
		taskRepository.save(task);
	    }
	}
	return task;
    }

    @Override
    public Task changeNotificationEmial(Long id) {
	Task task = null;
	if (id != null) {
	    task = getTaskById(id);
	    if (task != null) {
		if (task.isEmail() == false) {
		    task.setEmail(true);
		} else {
		    task.setEmail(false);
		}
		taskRepository.save(task);
	    }
	}
	return task;
    }

}

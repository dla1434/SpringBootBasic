package com.basic.sb.dao;

import org.springframework.data.repository.CrudRepository;

import com.basic.sb.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{

}

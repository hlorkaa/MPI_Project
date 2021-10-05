package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}

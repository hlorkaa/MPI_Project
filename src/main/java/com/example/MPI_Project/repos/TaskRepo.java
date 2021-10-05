package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByWorkman(String workman);
}

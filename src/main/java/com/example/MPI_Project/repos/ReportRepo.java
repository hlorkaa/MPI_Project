package com.example.MPI_Project.repos;

import org.springframework.data.repository.CrudRepository;
import com.example.MPI_Project.domain.Report;

import java.util.List;

public interface ReportRepo extends CrudRepository<Report, Integer> {
    List<Report> findByTitle(String title);
}

package com.example.MPI_Project.repos;

import com.example.MPI_Project.domain.Finances;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinancesRepo extends CrudRepository<Finances, Integer> {
    List<Finances> findByType(String type);
}
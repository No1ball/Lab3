package com.example.springtest.repos;

import com.example.springtest.entity.ContractsSqlDao;
import org.springframework.data.repository.CrudRepository;

public interface ContractsRepo extends CrudRepository<ContractsSqlDao, Integer> {

}
package com.example.springtest.repos;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractsRepo extends CrudRepository<ContractsSqlDao, Integer> {
    List<ContractsSqlDao> findByCompNameContainingIgnoreCaseOrderByCompName(String name);

    List <ContractsSqlDao> findAll();
}
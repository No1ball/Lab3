package com.example.springtest.repos;

import com.example.springtest.entity.DevicesSqlDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DevicesRepo extends CrudRepository<DevicesSqlDao, Integer> {
    List <DevicesSqlDao> findByNameIgnoreCaseOrderByName(String name);
    List <DevicesSqlDao> findAll();

}
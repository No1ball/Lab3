package com.example.springtest.repos;

import com.example.springtest.entity.DevicesSqlDao;
import org.springframework.data.repository.CrudRepository;

public interface DevicesRepo extends CrudRepository<DevicesSqlDao, Integer> {

}
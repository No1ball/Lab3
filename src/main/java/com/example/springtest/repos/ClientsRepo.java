package com.example.springtest.repos;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.CompanySqlDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ClientsRepo extends CrudRepository<ClientsSqlDao, Integer>  {
    List<ClientsSqlDao> findAll();
    List<ClientsSqlDao> findByNameContainsIgnoreCaseOrderByName(String name);
}

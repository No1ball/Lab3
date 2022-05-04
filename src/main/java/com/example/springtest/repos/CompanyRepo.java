package com.example.springtest.repos;

import com.example.springtest.entity.CompanySqlDAO;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CompanyRepo extends CrudRepository<CompanySqlDAO, Integer> {

}
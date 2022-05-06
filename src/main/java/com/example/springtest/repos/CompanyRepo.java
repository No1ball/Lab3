package com.example.springtest.repos;

import com.example.springtest.entity.CompanySqlDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CompanyRepo extends CrudRepository<CompanySqlDAO, Integer> {

}
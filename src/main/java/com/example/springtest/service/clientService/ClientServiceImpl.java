package com.example.springtest.service.clientService;

import com.example.springtest.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientsRepo clientsRepo;

}

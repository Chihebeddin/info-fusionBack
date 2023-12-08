package com.example.infofusionback.repository;

import com.example.infofusionback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.infofusionback.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    Client findByEmail (String email);

}

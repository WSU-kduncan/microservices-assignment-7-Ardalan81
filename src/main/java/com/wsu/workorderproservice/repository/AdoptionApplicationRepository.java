package com.wsu.workorderproservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsu.workorderproservice.model.AdoptionApplication;


@Repository
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
    
}


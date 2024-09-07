package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer>{

}

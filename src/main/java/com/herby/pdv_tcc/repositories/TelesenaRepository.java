package com.herby.pdv_tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herby.pdv_tcc.domain.Telesena;

@Repository
public interface TelesenaRepository extends JpaRepository<Telesena, Integer>{

}

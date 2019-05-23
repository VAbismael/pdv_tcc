package com.herby.pdv_tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herby.pdv_tcc.domain.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer>{

}

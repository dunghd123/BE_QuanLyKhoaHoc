package com.example.jpa_final.repo;

import com.example.jpa_final.model.ChuDe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChudeRep extends JpaRepository<ChuDe,Integer> {
}

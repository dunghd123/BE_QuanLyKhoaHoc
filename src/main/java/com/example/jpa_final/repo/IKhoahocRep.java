package com.example.jpa_final.repo;

import com.example.jpa_final.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKhoahocRep extends JpaRepository<KhoaHoc,Integer> {
}

package com.example.jpa_final.repo;

import com.example.jpa_final.model.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHocvienRep extends JpaRepository<HocVien,Integer> {
}

package com.example.jpa_final.repo;

import com.example.jpa_final.model.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDangkyRep extends JpaRepository<DangKyHoc,Integer> {

}

package com.example.jpa_final.repo;

import com.example.jpa_final.model.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuyenhanRep extends JpaRepository<QuyenHan,Integer> {
}

package com.example.jpa_final.repo;

import com.example.jpa_final.model.LoaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaibaivietRep extends JpaRepository<LoaiBaiViet,Integer> {
}

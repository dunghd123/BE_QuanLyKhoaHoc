package com.example.jpa_final.repo;

import com.example.jpa_final.model.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaivietRep extends JpaRepository<BaiViet,Integer> {
}

package com.example.jpa_final.repo;

import com.example.jpa_final.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaikhoanRep extends JpaRepository<TaiKhoan,Integer> {
}

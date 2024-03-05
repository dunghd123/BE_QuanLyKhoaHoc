package com.example.jpa_final.repo;

import com.example.jpa_final.model.TinhTrangHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITinhtranghocRep extends JpaRepository<TinhTrangHoc,Integer> {
}

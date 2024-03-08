package com.example.jpa_final.controller;

import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.services.KhoahocServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/khoahoc")
public class KhoahocController {
    @Autowired
    KhoahocServices khServices;

    @PostMapping(value = "themkhoahoc")
    public ResponseEntity<?> themKH(@RequestBody KhoaHoc khoaHoc){
        if(khServices.themKhoahoc(khoaHoc)){
            return ResponseEntity.ok("them thanh cong!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Them that bai!!!");
    }
    @PutMapping(value = "suakhoahoc")
    public ResponseEntity<?> suaKH(@RequestBody KhoaHoc khoaHoc){
        if(khServices.suaKhoaHoc(khoaHoc)){
            return ResponseEntity.ok("sua thanh cong khoa hoc co id: "+khoaHoc.getKhoahocID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua that bai khoa hoc co id: "+khoaHoc.getKhoahocID());
    }
}

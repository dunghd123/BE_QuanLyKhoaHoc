package com.example.jpa_final.controller;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.services.BaivietServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/baiviet")
public class BaivietController {
    @Autowired
    BaivietServices bvServices;
    @PostMapping(value = "thembaiviet")
    public void themBV(@RequestBody BaiViet baiViet){
        bvServices.themBaiViet(baiViet);
    }
    @PutMapping(value = "suabaiviet")
    public void suaBV(@RequestBody BaiViet baiViet){
        bvServices.suaBaiViet(baiViet);
    }
    @DeleteMapping (value = "xoabaiviet")
    public ResponseEntity<?> xoaBV(@RequestParam int baivietid){
        if(bvServices.xoaBaiViet(baivietid)){
            return ResponseEntity.ok("xoa thanh cong bai viet co ma: "+baivietid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai bai viet co ma: "+baivietid);
    }
    @GetMapping(value = "hienthidanhsachbaiviet")
    public List<BaiViet> hienThiDsBv(@RequestParam int pagenum){
        return bvServices.danhSachBaiviet(pagenum);
    }
    @GetMapping(value = "timkiemtheotenbaiviet")
    public List<BaiViet> findByTenbv(@RequestParam String tenbv, @RequestParam int pagenum){
        return bvServices.timKiemTheoTenBaiViet(tenbv, pagenum);
    }
}

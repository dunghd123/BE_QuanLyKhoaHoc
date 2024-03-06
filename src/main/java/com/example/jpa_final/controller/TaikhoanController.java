package com.example.jpa_final.controller;

import com.example.jpa_final.model.TaiKhoan;
import com.example.jpa_final.services.TaikhoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/taikhoan")
public class TaikhoanController {
    @Autowired
    TaikhoanServices tkServices;

    @PostMapping(value = "themtaikhoan")
    public void themTK(@RequestBody TaiKhoan taiKhoan){
        tkServices.themTaiKhoan(taiKhoan);
    }
    @PutMapping(value = "suataikhoan")
    public ResponseEntity<?> suaTK(@RequestBody TaiKhoan taiKhoan){
        if(tkServices.suaTaiKhoan(taiKhoan)){
            return ResponseEntity.ok("Sua thanh cong tai khoan co ma: "+taiKhoan.getTaikhoanid());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("sua khong thanh con tai khoan co ma: "+taiKhoan.getTaikhoanid());
    }
    @DeleteMapping(value = "xoataikhoan")
    public ResponseEntity<?> xoaTK(@RequestParam int taikhoanid){
        if(tkServices.xoaTaiKhoan(taikhoanid)){
            return ResponseEntity.ok("xoa thanh cong tai khoan co ma: "+taikhoanid);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" khong ton tai tai khoan co ma: "+taikhoanid);
    }

    @GetMapping(value = "hienthidanhsachtk")
    public List<TaiKhoan> hienthiDSTK(@RequestParam int pagenum){
        return tkServices.hienDsTaikhoan(pagenum);
    }
    @GetMapping(value = "timkiemtheotendn")
    public List<TaiKhoan> timkiemTheoTenDN(@RequestParam String tendn, @RequestParam Integer pagenum){
        return tkServices.timkiemtheoTenDangnhap(tendn,pagenum);
    }
}

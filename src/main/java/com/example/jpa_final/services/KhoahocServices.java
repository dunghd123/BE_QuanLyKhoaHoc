package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.model.LoaiKhoaHoc;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.IKhoahocRep;
import com.example.jpa_final.repo.ILoaikhoahocRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KhoahocServices {
    @Autowired
    IKhoahocRep khoahocRep;
    @Autowired
    IDangkyRep dangkyRep;
    @Autowired
    ILoaikhoahocRep loaikhoahocRep;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();

    public boolean themKhoahoc(KhoaHoc khoaHoc){
        boolean check=true;
        Set<ConstraintViolation<KhoaHoc>> violationSet= val.validate(khoaHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            int lkhid= khoaHoc.getLoaiKhoaHoc().getLoaikhoahocID();
            Optional<LoaiKhoaHoc> op= Optional.empty();
            if(loaikhoahocRep.findById(lkhid)==op){
                check=false;
            }else {
                khoahocRep.save(khoaHoc);
            }
        }
        return check;
    }

    public boolean suaKhoaHoc(KhoaHoc khoaHoc){
        boolean check=true;
        KhoaHoc khCurrent= khoahocRep.findById(khoaHoc.getKhoahocID()).get();
        khCurrent.setTenkhoahoc(khoaHoc.getTenkhoahoc());
        khCurrent.setThoigianhoc(khoaHoc.getThoigianhoc());
        khCurrent.setGioithieu(khoaHoc.getGioithieu());
        khCurrent.setNoidung(khoaHoc.getNoidung());
        khCurrent.setHocphi(khoaHoc.getHocphi());
        khCurrent.setSohocvien(khoaHoc.getSohocvien());
        khCurrent.setSoluongmon(khoaHoc.getSoluongmon());
        khCurrent.setLoaiKhoaHoc(khoaHoc.getLoaiKhoaHoc());
        Set<ConstraintViolation<KhoaHoc>> violationSet= val.validate(khoaHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            int lkhid= khCurrent.getLoaiKhoaHoc().getLoaikhoahocID();
            Optional<LoaiKhoaHoc> op= Optional.empty();
            if(loaikhoahocRep.findById(lkhid)==op){
                check=false;
            }else {
                khoahocRep.save(khCurrent);
            }
        }
        return check;
    }
}

package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.DangKyHoc;
import com.example.jpa_final.model.TinhTrangHoc;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.ITinhtranghocRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TinhtranghocServices {
    @Autowired
    ITinhtranghocRep tinhtranghocRep;
    @Autowired
    IDangkyRep dangkyRep;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();
    public boolean themTinhTrangHoc(TinhTrangHoc tinhTrangHoc){
        boolean check= false;
        Set<ConstraintViolation<TinhTrangHoc>> violationSet= val.validate(tinhTrangHoc);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            check=true;
            tinhtranghocRep.save(tinhTrangHoc);
        }
        return check;
    }
    public boolean suaTinhTrangHoc(TinhTrangHoc tinhTrangHoc){
        boolean check= false;
        TinhTrangHoc tthCurrent= tinhtranghocRep.findById(tinhTrangHoc.getTinhtranghocID()).get();
        tthCurrent.setTentinhtrang(tinhTrangHoc.getTentinhtrang());
        Set<ConstraintViolation<TinhTrangHoc>> violationSet= val.validate(tthCurrent);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            check=true;
            tinhtranghocRep.save(tthCurrent);
        }
        return check;
    }
    public boolean xoaTinhTrangHoc(int tthid){
        boolean check= true;
        Optional<TinhTrangHoc> op= Optional.empty();
        if(tinhtranghocRep.findById(tthid)==op){
            check=false;
        }else {
            for(DangKyHoc dk: dangkyRep.findAll()){
                if(dk.getTinhTrangHoc().getTinhtranghocID()==tthid){
                    dangkyRep.delete(dk);
                }
            }
            tinhtranghocRep.deleteById(tthid);
        }
        return check;
    }
    public List<TinhTrangHoc> hienThiDS(int pagenum){
        Pageable pageable= PageRequest.of(pagenum,20);
        return tinhtranghocRep.findAllBy(pageable);
    }
}

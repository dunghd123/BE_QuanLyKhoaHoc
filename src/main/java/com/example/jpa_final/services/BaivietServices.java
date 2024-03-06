package com.example.jpa_final.services;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.model.ChuDe;
import com.example.jpa_final.repo.IBaivietRep;
import com.example.jpa_final.repo.IChudeRep;
import com.example.jpa_final.repo.ITaikhoanRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BaivietServices {
    @Autowired
    IBaivietRep baivietRep;
    @Autowired
    IChudeRep chudeRep;
    @Autowired
    ITaikhoanRep taikhoanRep;

    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();

    public void themBaiViet(BaiViet baiViet){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        baiViet.setThoigiantao(date);
        Set<ConstraintViolation<BaiViet>> violationSet= val.validate(baiViet);
        violationSet.forEach(x->{
            System.out.println(x.getMessage());
        });
        if(violationSet.isEmpty()){
            baivietRep.save(baiViet);
        }
    }
    public void suaBaiViet(BaiViet baiViet){

    }
}

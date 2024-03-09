package com.example.jpa_final.services;

import com.example.jpa_final.model.DangKyHoc;
import com.example.jpa_final.model.KhoaHoc;
import com.example.jpa_final.model.TinhTrangHoc;
import com.example.jpa_final.repo.IDangkyRep;
import com.example.jpa_final.repo.IKhoahocRep;
import com.example.jpa_final.repo.ITinhtranghocRep;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DangkyServices {
    @Autowired
    IDangkyRep dangkyRep;
    @Autowired
    IKhoahocRep khoahocRep;
    @Autowired
    ITinhtranghocRep tinhtranghocRep;
    @Autowired
    KhoahocServices khoahocServices;
    ValidatorFactory valFac= Validation.buildDefaultValidatorFactory();
    Validator val= valFac.getValidator();
    public static Date tinhNgayKetThuc(Date ngaybd,int thoigianhoc){
        LocalDate localDateBatDau = ngaybd.toLocalDate();
        LocalDate localDateKetThuc = localDateBatDau.plusDays(thoigianhoc);
        return Date.valueOf(localDateKetThuc);
    }

    public boolean themDangKyHoc(DangKyHoc dangKyHoc){
        boolean check=true;
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        //lay ra id "dang hoc chinh"
        int getIdDangHocChinh = 0;
        for(TinhTrangHoc tt: tinhtranghocRep.findAll()){
            if(tt.getTentinhtrang().equals("Đang học chính")){
                getIdDangHocChinh=tt.getTinhtranghocID();
            }
        }
        if(dangkyRep.findAll().isEmpty()){
            dangKyHoc.setNgaydangky(date);
            if(dangKyHoc.getTinhTrangHoc().getTinhtranghocID()==getIdDangHocChinh){
                dangKyHoc.setNgaybatdau(date);
            }else {
                dangKyHoc.setNgaybatdau(null);
            }
            KhoaHoc khCurrent= khoahocRep.findById(dangKyHoc.getKhoaHoc().getKhoahocID()).get();
            if(dangKyHoc.getNgaybatdau()!=null){
                dangKyHoc.setNgayketthuc(tinhNgayKetThuc(dangKyHoc.getNgaybatdau(), khCurrent.getThoigianhoc()));
            }else {
                dangKyHoc.setNgayketthuc(null);
            }
            Set<ConstraintViolation<DangKyHoc>> violationSet= val.validate(dangKyHoc);
            if(violationSet.isEmpty()){
                dangkyRep.save(dangKyHoc);
                khoahocServices.suaKhoaHoc(khCurrent);
            }
        }else {
            //kiem tra xem tai khoan - hoc vien da ton tai hay chua?
            boolean checkTKHV=true;
            for(DangKyHoc dk: dangkyRep.findAll()){
                if(dangKyHoc.getHocVien().getHocvienID()==dk.getHocVien().getHocvienID()
                && dangKyHoc.getTaiKhoan().getTaikhoanid() == dk.getTaiKhoan().getTaikhoanid()){
                    checkTKHV=false;
                    check=false;
                    break;
                }
            }
            if(checkTKHV && check){
                dangKyHoc.setNgaydangky(date);
                if(dangKyHoc.getTinhTrangHoc().getTinhtranghocID()==getIdDangHocChinh){
                    dangKyHoc.setNgaybatdau(date);
                }else {
                    dangKyHoc.setNgaybatdau(null);
                }
                KhoaHoc khCurrent= khoahocRep.findById(dangKyHoc.getKhoaHoc().getKhoahocID()).get();
                if(dangKyHoc.getNgaybatdau()!=null){
                    dangKyHoc.setNgayketthuc(tinhNgayKetThuc(dangKyHoc.getNgaybatdau(), khCurrent.getThoigianhoc()));
                }else {
                    dangKyHoc.setNgayketthuc(null);
                }
                Set<ConstraintViolation<DangKyHoc>> violationSet= val.validate(dangKyHoc);
                if(violationSet.isEmpty()){
                    dangkyRep.save(dangKyHoc);
                    khoahocServices.suaKhoaHoc(khCurrent);
                }
            }
        }
        return check;
    }
    public void suaDangKy(DangKyHoc dangKyHoc){

    }
    public boolean xoaDangKyHoc(int id){
        boolean check= true;
        int khid;
        DangKyHoc dk= dangkyRep.findById(id).get();
        khid=dk.getKhoaHoc().getKhoahocID();
        KhoaHoc kh= khoahocRep.findById(khid).get();
        Optional<DangKyHoc> op= Optional.empty();
        if(dangkyRep.findById(id)==op){
            check=false;
        }else {
            dangkyRep.deleteById(id);
            khoahocServices.suaKhoaHoc(kh);
        }
        return check;
    }
    public List<DangKyHoc> hienThiDKy(int pagenum){
        Pageable pageable= PageRequest.of(pagenum,20);
        return dangkyRep.findAllBy(pageable);
    }
}

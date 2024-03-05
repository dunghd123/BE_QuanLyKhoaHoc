package com.example.jpa_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "khoahocs")
@Getter
@Setter
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int KhoahocID;
    @Column(name = "tenkhoahoc")
    @Size(max = 50,message = "ten loai khong qua 50 ky tu")
    private String Tenkhoahoc;
    @Column(name = "thoigianhoc")
    private int Thoigianhoc;
    @Column(name = "gioithieu")
    private String Gioithieu;
    @Column(name = "noidung")
    private String Noidung;
    @Column(name = "hocphi")
    private float Hocphi;
    @Column(name = "sohocvien")
    private int Sohocvien;
    @Column(name = "soluongmon")
    private int Soluongmon;
    @Column(name = "hinhanh")
    private String Hinhanh;

    //khoa ngoai den bang loaikhoahoc
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaikhoahocid",foreignKey = @ForeignKey(name = "FK_KhoaHoc_LoaiKhoaHoc"))
    @JsonIgnoreProperties(value = "khoaHocs")
    LoaiKhoaHoc loaiKhoaHoc;

    //lien ket den bang dangkyhoc
    @OneToMany(mappedBy = "khoaHoc")
    @JsonIgnoreProperties(value = "khoaHoc")
    Set<DangKyHoc> dangKyHocs;
}

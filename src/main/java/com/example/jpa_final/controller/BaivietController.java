package com.example.jpa_final.controller;

import com.example.jpa_final.model.BaiViet;
import com.example.jpa_final.services.BaivietServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/baiviet")
public class BaivietController {
    @Autowired
    BaivietServices bvServices;
    @PostMapping(value = "thembaiviet")
    public void themBV(@RequestBody BaiViet baiViet){
        bvServices.themBaiViet(baiViet);
    }
}

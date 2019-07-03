package com.cat.code.controller;

import com.cat.code.po.TestPo;
import com.cat.code.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/add/image")
    public String addImage(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws Exception{
        if(!file.isEmpty()){
            BASE64Encoder encoder = new BASE64Encoder();
            String image = encoder.encode(file.getBytes());
            TestPo testPo = new TestPo();
            testPo.setId(id);
            testPo.setImage(image);
            testService.addImage(testPo);
        }
        return "ok";
    }

    @GetMapping("/get/image")
    public void getImage(@RequestParam("id") Integer id, HttpServletResponse response) throws Exception{
        testService.getImage(id,response);
    }
}

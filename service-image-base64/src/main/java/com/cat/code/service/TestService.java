package com.cat.code.service;

import com.cat.code.mapper.TestMapper;
import com.cat.code.po.TestPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Service
public class TestService {

    @Autowired
    public TestMapper testMapper;

    public String addImage(TestPo testPo){
        testMapper.insertImage(testPo);
        return "ok";
    }

    public String getImage(Integer id, HttpServletResponse response){
        try {
            TestPo testPo = testMapper.selectImageById(id);
            byte[] image = (byte[])testPo.getImage();
            String value = new String(image,"UTF-8");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(value);
            for(int i=0;i<bytes.length;i++){
                if(bytes[i]<0){
                    bytes[i]+=256;
                }
            }
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "ok";
    }
}

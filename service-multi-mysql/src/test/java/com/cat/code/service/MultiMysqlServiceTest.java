package com.cat.code.service;

import com.cat.code.mappers.primary.PrimaryMapper;
import com.cat.code.mappers.secondary.SecondaryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: lvgang
 * @Time: 2019/8/26 15:32
 * @Description: service调用类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MultiMysqlServiceTest {

    @Autowired
    private MultiMysqlService multiMysqlService;

    @Test
    public void primaryServiceTest(){
        Integer num =multiMysqlService.primaryService();
        System.out.println(num);
    }

    @Test
    public void secondaryServiceTest(){
        Integer num = multiMysqlService.secondaryService();
        System.out.println(num);
    }
}

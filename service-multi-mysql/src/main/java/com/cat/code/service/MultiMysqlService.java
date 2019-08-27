package com.cat.code.service;

import com.cat.code.mappers.primary.PrimaryMapper;
import com.cat.code.mappers.secondary.SecondaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lvgang
 * @Time: 2019/8/26 15:32
 * @Description: service调用类
 */
@Service
public class MultiMysqlService {

    private static Logger logger = LoggerFactory.getLogger(MultiMysqlService.class);

    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private SecondaryMapper secondaryMapper;

    public Integer primaryService(){
        Integer num = primaryMapper.countInfoTest1();
        logger.debug("primary test1 count:{}",num);
        return num;
    }


    public Integer secondaryService(){
        Integer num = secondaryMapper.countInfoTest2();
        logger.debug("secondary test2 count:{}",num);
        return num;
    }
}

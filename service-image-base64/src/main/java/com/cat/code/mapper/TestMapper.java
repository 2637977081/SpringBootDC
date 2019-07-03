package com.cat.code.mapper;

import com.cat.code.po.TestPo;

public interface TestMapper {

    void insertImage(TestPo testPo);

    TestPo selectImageById(Integer id);
}

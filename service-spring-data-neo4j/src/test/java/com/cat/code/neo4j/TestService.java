package com.cat.code.neo4j;

import com.cat.code.neo4j.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lvgang
 * @Time: 2019/11/11 14:47
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceSpringDataNeo4jApplication.class)
public class TestService {

    @Autowired
    private PersonService personService;

    @Test
    public void testSave() throws Exception{
        personService.savePerson();
    }

    @Test
    public void testSaveUpdate() throws Exception{
        personService.saveAndUpdatePerson();
    }

    @Test
    public void testFind() throws Exception{
        personService.findByName();
    }

    @Test
    public void testDelete() throws Exception{
        personService.deleteById();
    }


}

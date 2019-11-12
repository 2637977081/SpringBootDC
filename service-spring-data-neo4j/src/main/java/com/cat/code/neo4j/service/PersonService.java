package com.cat.code.neo4j.service;

import com.cat.code.neo4j.domain.PersonEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cat.code.neo4j.repository.PersonRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: lvgang
 * @Time: 2019/11/11 14:29
 * @Email: lvgang@golaxy.cn
 * @Description: 服务类
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    /**
     * 添加事物处理
     */
    @Transactional
    public void savePerson() throws Exception{
        PersonEntity person1 = new PersonEntity("云码","女");
        PersonEntity person2 = new PersonEntity("猫仔","男");
        PersonEntity person3 = new PersonEntity("木库","男");
        PersonEntity person4 = new PersonEntity("莫莫","男");

        Set<PersonEntity> set1 = new HashSet<>();
        set1.add(person2);
        set1.add(person3);
        person1.setFriends(set1);

        Set<PersonEntity> set2 = new HashSet<>();
        set2.add(person1);
        set2.add(person3);
        set2.add(person4);
        person2.setFriends(set2);

        Set<PersonEntity> set3 = new HashSet<>();
        set3.add(person1);
        set3.add(person2);
        set3.add(person4);
        person3.setFriends(set3);

        Set<PersonEntity> set4 = new HashSet<>();
        set4.add(person2);
        set4.add(person3);
        person4.setFriends(set4);
        ObjectMapper objectMapper = new ObjectMapper();
        PersonEntity personEntity = personRepository.save(person1);
        System.out.println("新增结果："+personEntity.getId());
    }

    public void saveAndUpdatePerson() throws Exception{
        //查询节点
        List<PersonEntity> personEntityList = personRepository.findByName("木库");
        for (PersonEntity temp:personEntityList){
            //删除节点
            personRepository.deleteById(temp.getId());
        }

        PersonEntity person1 = new PersonEntity("云码","女");
        PersonEntity person2 = new PersonEntity("猫仔","男");
        PersonEntity person3 = new PersonEntity("木库","男");
        Set<PersonEntity> set3 = new HashSet<>();
        set3.add(person1);
        set3.add(person2);
        person3.setFriends(set3);

        ObjectMapper objectMapper = new ObjectMapper();
        PersonEntity personEntity = personRepository.save(person3,34);
        System.out.println("新增或修改结果"+objectMapper.writeValueAsString(personEntity));
    }

    public void findByName() throws Exception{
        String name = "云码";

        ObjectMapper objectMapper = new ObjectMapper();
        List<PersonEntity> personEntityList = personRepository.findByName(name);
        System.out.println("查询结果："+objectMapper.writeValueAsString(personEntityList));
    }

    public void deleteById(){
        for (Long id=0L;id<70;id++){
            personRepository.deleteById(id);
        }
    }
}

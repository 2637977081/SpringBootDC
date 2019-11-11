package com.cat.code.neo4j.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lvgang
 * @Time: 2019/11/11 14:11
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@NodeEntity(label = "Person")
public class PersonEntity {
    //图库id
    private Long id;
    //人名
    private String name;
    //性别
    private String sex;

    /**
     * type 关系名称
     * direction 关系方向
     *  INCOMING 指向自己
     *  OUTGOING 指向对方
     *  UNDIRECTED 无向
     */
    @Relationship(type = "FRIEND", direction = "OUTGOING")
    private Set<PersonEntity> friends;

    public PersonEntity(){}

    public PersonEntity(String name,String sex){
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PersonEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<PersonEntity> friends) {
        this.friends = friends;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

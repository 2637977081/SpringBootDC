package com.cat.code.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lvgang
 * @Time: 2019/10/29 16:43
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer age;

    public User(){}

    public User(Long id,String name,Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

}

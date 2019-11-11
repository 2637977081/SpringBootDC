package com.cat.code.neo4j.repository;

import com.cat.code.neo4j.domain.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lvgang
 * @Time: 2019/11/11 14:27
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity,Long> {
    List<PersonEntity> findByName(String name);
    List<PersonEntity> findByNameLike(String name);
}

package com.yao.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jack Yao on 2021/6/11 10:27 下午
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}

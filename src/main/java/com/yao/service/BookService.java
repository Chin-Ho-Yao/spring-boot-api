package com.yao.service;

import com.yao.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack Yao on 2021/6/11 10:27 下午
 */

public interface BookService {
    List<Book> findAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Long id);
    void deleteAllBooks();
}

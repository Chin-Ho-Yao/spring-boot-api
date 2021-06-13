package com.yao.service;

import com.yao.domain.Book;
import com.yao.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack Yao on 2021/6/13 3:05 下午
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        /*包含id才進行更新*/
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}

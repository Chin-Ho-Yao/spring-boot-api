package com.yao.api;

import com.yao.domain.Book;
import com.yao.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jack Yao on 2021/6/13 3:49 下午
 */
@RestController
@RequestMapping("/api/v1")
public class BookApi {
    @Autowired
    private BookService bookService;

    /*獲取書單列表*/
    @GetMapping("/books")
    public ResponseEntity<?> listAllBooks(){
        List<Book> books = bookService.findAllBooks();
        /*封裝到RE裡面，請求成功返回200的狀態*/
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    /*獲取一條書單信息*/
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        /*封裝到RE裡面，請求成功返回200的狀態*/
        return new ResponseEntity<Object>(book, HttpStatus.OK);
    }

    /*新增書單，201(Created)*/
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(Book book){
        Book book1 = bookService.saveBook(book);
        return new ResponseEntity<Object>(book1, HttpStatus.CREATED);
    }

    /*更新書單*/
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, Book book){
        Book currentBook = bookService.getBookById(id);
        /*複製屬性，右邊複製給左邊*/
        BeanUtils.copyProperties(book, currentBook);
        Book book1 = bookService.updateBook(currentBook);
        return new ResponseEntity<Object>(book1, HttpStatus.OK);

    }

    /*刪除一個書單*/
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /*刪除所有書單*/
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBook(){
        bookService.deleteAllBooks();
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


}

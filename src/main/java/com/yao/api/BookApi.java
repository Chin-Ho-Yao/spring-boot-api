package com.yao.api;

import com.yao.domain.Book;
import com.yao.dto.BookDTO;
import com.yao.exception.InvalidRequestException;
import com.yao.exception.NotFoundException;
import com.yao.service.BookService;
import com.yao.util.CustomBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
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
        if (books.isEmpty()){
            throw new NotFoundException("書單列表不存在");
        }
        /*封裝到RE裡面，請求成功返回200的狀態*/
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    /*獲取一條書單信息*/
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if (book == null){
            throw new NotFoundException(String.format("book by id %s not found",id));
        }
        /*封裝到RE裡面，請求成功返回200的狀態*/
        return new ResponseEntity<Object>(book, HttpStatus.OK);
    }

    /*新增書單，201(Created)*/
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult){
        /*先驗證有無錯誤*/

        if (bindingResult.hasErrors()){
            /*bindingResult將驗證失敗傳遞過去*/
            throw new InvalidRequestException("Invalid parameter",bindingResult);
        }/*驗證失敗就不會再往下走了*/

        Book book1 = bookService.saveBook(bookDTO.convertToBook());
        return new ResponseEntity<Object>(book1, HttpStatus.CREATED);
    }

    /*更新書單*/
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@Valid @PathVariable Long id,@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult){
        /*先驗證有無錯誤*/
        if (bindingResult.hasErrors()){
            /*bindingResult將驗證失敗傳遞過去*/
            throw new InvalidRequestException("Invalid parameter",bindingResult);
        }
        Book currentBook = bookService.getBookById(id);
        bookDTO.convertToBook(currentBook);
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

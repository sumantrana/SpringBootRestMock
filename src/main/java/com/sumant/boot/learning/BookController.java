package com.sumant.boot.learning;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService ){
        this.bookService = bookService;
    }

    @GetMapping
    public Book getBook(){
        return bookService.getBook();
    }

    @PostMapping
    public ResponseEntity<String> createBook(Book book){
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
}


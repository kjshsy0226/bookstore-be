package com.fullstackdev.controller;

import com.fullstackdev.model.Book;
import com.fullstackdev.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/transaction")
    public ResponseEntity<Void> recordTransaction(@RequestParam Long bookId, @RequestParam int quantity, @RequestParam boolean isSale) {
        bookService.recordTransaction(bookId, quantity, isSale);
        return ResponseEntity.ok().build();
    }
}

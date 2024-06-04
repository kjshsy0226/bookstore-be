package com.fullstackdev.service;

import com.fullstackdev.model.Book;
import com.fullstackdev.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void recordTransaction(Long bookId, int quantity, boolean isSale) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        BigDecimal transactionAmount = book.getSalePrice().multiply(new BigDecimal(quantity));

        if (isSale) {
            book.setStockQuantity(book.getStockQuantity() - quantity);
            book.setSoldQuantity(book.getSoldQuantity() + quantity);
            book.setTotalSales(book.getTotalSales().add(transactionAmount));
        } else {
            book.setStockQuantity(book.getStockQuantity() + quantity);
        }

        bookRepository.save(book);
    }
}

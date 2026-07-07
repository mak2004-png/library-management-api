package library_management.controller;

import library_management.model.Book;
import library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")

public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getGetAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails)
    {
        Optional<Book> book = bookService.updateBook(id, bookDetails);
        if (book.isPresent()){
            return ResponseEntity.ok(book.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}

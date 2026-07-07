package library_management.service;

import library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import library_management.model.Book;

@Service

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public Optional<Book> updateBook(Long id, Book bookDetails){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
        Book existingBook = book.get();

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setAvailable(bookDetails.isAvailable());
        existingBook.setIsbn(bookDetails.getIsbn());

        return Optional.of(bookRepository.save(existingBook));
        }

        else {
            return Optional.empty();
        }
    }

}



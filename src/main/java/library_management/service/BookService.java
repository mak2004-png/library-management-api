package library_management.service;

import library_management.exception.ResourceNotFoundException;
import library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import library_management.model.Book;

@Service

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){

         return bookRepository.findById(id).
                 orElseThrow(() -> {
                     return new ResourceNotFoundException("Book id " + id + " not found");
                 });

    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        boolean exists = bookRepository.existsById(id);
        if (!exists){

            throw  new ResourceNotFoundException("Book id " + id + " not found");
        }

            bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book bookDetails){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Book id " + id + " not found");
                });

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setAvailable(bookDetails.isAvailable());
        book.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(book);
        }
}



package library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import library_management.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findByIsbn(String isbn);
}

package library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import library_management.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

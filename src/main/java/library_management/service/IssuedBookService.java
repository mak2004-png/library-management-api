package library_management.service;

import library_management.exception.BookAlreadyReturnedException;
import library_management.exception.BookUnavailableException;
import library_management.exception.ResourceNotFoundException;
import library_management.model.Book;
import library_management.model.IssuedBook;
import library_management.model.Member;
import library_management.repository.BookRepository;
import library_management.repository.IssuedBookRepository;
import library_management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public List<IssuedBook> getAllIssuedBooks(){
        return issuedBookRepository.findAll();
    }

    public IssuedBook issueBook(Long bookId, Long memberId){
        Book book = bookRepository.findById(bookId).
                orElseThrow(()->{
                   return new ResourceNotFoundException("Book id " + bookId + " not found");
                });
        Member member = memberRepository.findById(memberId).
                orElseThrow(()->{
                    return new ResourceNotFoundException("Member id " + memberId + " not found");
                });
        if (!book.isAvailable()){
            throw new BookUnavailableException("Book id " + bookId + " is currently unavailable");
        }
        IssuedBook issuedBook = new IssuedBook();

        issuedBook.setBook(book);
        issuedBook.setMember(member);
        issuedBook.setIssuedDate(LocalDate.now());
        book.setAvailable(false);

        bookRepository.save((book));
        return issuedBookRepository.save(issuedBook);
    }

    public IssuedBook returnBook(Long issuedBookId)
    {
        IssuedBook issuedBook = issuedBookRepository.findById(issuedBookId).
                orElseThrow(()->{
                    return new ResourceNotFoundException
                            ("There is no record of this  "+ issuedBookId + " id");
                });


        if (issuedBook.getReturnDate() != null)
        {
            throw new BookAlreadyReturnedException("Issued Book id "+ issuedBookId + " has already been returned");
        }
        issuedBook.setReturnDate(LocalDate.now());
        Book book = issuedBook.getBook();
        book.setAvailable(true);

        bookRepository.save(book);
        return issuedBookRepository.save(issuedBook);
    }
}

package library_management.controller;

import library_management.model.IssuedBook;
import library_management.service.IssuedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/issued-books")

public class IssuedBookController {
    @Autowired
    private IssuedBookService issuedBookService;

    @GetMapping
    public List<IssuedBook> getAllIssuedBooks(){
        return issuedBookService.getAllIssuedBooks();
    }

    @PostMapping
    public IssuedBook issueBook(@RequestParam Long bookId, @RequestParam Long memberId){
        return issuedBookService.issueBook(bookId,memberId);
    }

    @PutMapping("/{id}/return")
    public IssuedBook returnBook(@PathVariable Long id){
        return issuedBookService.returnBook(id);
    }
}

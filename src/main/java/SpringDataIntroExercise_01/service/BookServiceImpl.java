package SpringDataIntroExercise_01.service;

import SpringDataIntroExercise_01.entities.Book;
import SpringDataIntroExercise_01.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void registerBook(Book book) {
        this.bookRepository.save(book);
    }
}

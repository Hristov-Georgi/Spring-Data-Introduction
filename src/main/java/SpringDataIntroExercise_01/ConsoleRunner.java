package SpringDataIntroExercise_01;

import SpringDataIntroExercise_01.entities.Author;
import SpringDataIntroExercise_01.entities.Book;
import SpringDataIntroExercise_01.repositories.AuthorRepository;
import SpringDataIntroExercise_01.repositories.BookRepository;
import SpringDataIntroExercise_01.service.SeedDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedDatabase seedDatabase;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner (SeedDatabase seedDatabase, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedDatabase = seedDatabase;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //seedDatabase.seedAll();
        //getAllBooksAfterYear2000();
        //getAllAuthorsWithBookBefore1990();
        //this.getAllAuthorsAndBooksCount();
        findAuthorByName();

    }

    private void getAllBooksAfterYear2000() {
        LocalDate dateAfter = LocalDate.of(2000, 12, 31);

        List<Book> booksList = bookRepository.findByReleaseDateAfter(dateAfter);

        booksList.forEach(b -> System.out.println(b.getTitle()));
    }

    private void getAllAuthorsWithBookBefore1990() {
        LocalDate date = LocalDate.of(1990, 1,1);

        List<Author> authorsList = this.authorRepository.findDistinctByBooksReleaseDateBefore(date);

        authorsList.forEach(a -> System.out.printf("%s %s\n", a.getFirstName(), a.getLastName()));
    }

    private void getAllAuthorsAndBooksCount() {

        List<Author> authorList = authorRepository.findAll();

        authorList.stream()
                .sorted((a1, a2) -> a2.getBooks().size() - a1.getBooks().size())
                .forEach(a -> System.out.printf("%s %s %d\n",
                        a.getFirstName(), a.getLastName(), a.getBooks().size()));
    }

    private void findAuthorByName() {
        String firstName = "George";
        String lastName = "Powell";

        Author author = authorRepository
            .findByFirstNameAndLastName(firstName, lastName);

        author.getBooks().stream()
                .sorted((d1, d2) -> d2.getReleaseDate().compareTo(d1.getReleaseDate()))
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(b -> System.out.printf("%s - %s - %d\n",
                        b.getTitle(),
                        b.getReleaseDate(),
                        b.getCopies()));

    }
}

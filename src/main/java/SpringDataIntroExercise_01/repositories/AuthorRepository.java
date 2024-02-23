package SpringDataIntroExercise_01.repositories;

import SpringDataIntroExercise_01.entities.Author;
import SpringDataIntroExercise_01.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate date);

    Author findByFirstNameAndLastName(String firstName, String lastName);


}

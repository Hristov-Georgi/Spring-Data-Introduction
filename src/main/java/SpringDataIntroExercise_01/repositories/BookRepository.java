package SpringDataIntroExercise_01.repositories;

import SpringDataIntroExercise_01.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

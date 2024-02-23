package SpringDataIntroExercise_01.service;

import SpringDataIntroExercise_01.entities.Author;

public interface AuthorService {
    Author getRandomAuthor();

    void registerAuthor(Author author);
}

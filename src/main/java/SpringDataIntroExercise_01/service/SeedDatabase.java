package SpringDataIntroExercise_01.service;


import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedDatabase {

    void seedCategories() throws IOException;

    void seedAuthors() throws FileNotFoundException;

    void seedBooks() throws FileNotFoundException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();

    }
}

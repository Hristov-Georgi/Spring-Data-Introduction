package SpringDataIntroExercise_01.service;

import SpringDataIntroExercise_01.entities.Author;
import SpringDataIntroExercise_01.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {

        long authorsNumber = authorRepository.count();

        Random rnd = new Random();
        long authorId = rnd.nextInt((int) authorsNumber) + 1;

        if(authorRepository.findById(authorId).isEmpty()) {
            getRandomAuthor();
        }
        return authorRepository.findById(authorId).get();
    }

    @Override
    public void registerAuthor(Author author) {
        this.authorRepository.save(author);
    }
}

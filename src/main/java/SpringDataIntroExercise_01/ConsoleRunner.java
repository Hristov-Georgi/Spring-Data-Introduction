package SpringDataIntroExercise_01;

import SpringDataIntroExercise_01.service.SeedDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedDatabase seedDatabase;

    @Autowired
    public ConsoleRunner (SeedDatabase seedDatabase) {
        this.seedDatabase = seedDatabase;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase.seedAuthors();
        seedDatabase.seedCategories();
        seedDatabase.seedBooks();
    }
}

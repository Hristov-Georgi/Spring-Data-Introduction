package SpringDataIntroExercise_01.service;

import SpringDataIntroExercise_01.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class SeedDatabaseImpl implements SeedDatabase {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static final String CATEGORIES_PATH = RESOURCES_PATH + "exerciseResources\\categories.txt";
    private static final String AUTHORS_PATH = RESOURCES_PATH + "exerciseResources\\authors.txt";
    private static final String BOOKS_PATH = RESOURCES_PATH + "exerciseResources\\books.txt";

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public SeedDatabaseImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void seedCategories() throws IOException {
        File categoriesFile = new File(CATEGORIES_PATH);
        Scanner scanner = new Scanner(categoriesFile);

        while (scanner.hasNextLine()) {
            String text = scanner.next();

            if (text != null) {
                categoryService.registerCategory(new Category(text));
            }
        }
    }

    @Override
    public void seedAuthors() throws FileNotFoundException {
        File authorsFile = new File(AUTHORS_PATH);
        Scanner scanner = new Scanner(authorsFile);

        while (scanner.hasNextLine()) {

            String[] authorNames = scanner.nextLine().split("\\s+");

            if (authorNames.length == 2) {
                authorService.registerAuthor(new Author(authorNames[0], authorNames[1]));
            } else if (!authorNames[0].isBlank()) {
                authorService.registerAuthor(new Author(authorNames[0]));
            }
        }
    }

    @Override
    public void seedBooks() throws FileNotFoundException {

        File booksFile = new File(BOOKS_PATH);

        Scanner scanner = new Scanner(booksFile);

        while (scanner.hasNextLine()) {
            String readLine = scanner.nextLine();

            if(readLine.isBlank()) {
                continue;
            }

            bookService.registerBook(getSingleBook(readLine));

        }


    }

    private Book getSingleBook(String line) throws FileNotFoundException {

        String[] bookInfo = line.split("\\s+");

        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[0])];

        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookInfo[2]);

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(bookInfo[3]));

        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[4])];

        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction);
        book.setAuthor(authorService.getRandomAuthor());
        book.setCategories(categoryService.getRandomCategories());

        return book;

    }


}

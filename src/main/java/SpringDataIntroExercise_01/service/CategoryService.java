package SpringDataIntroExercise_01.service;

import SpringDataIntroExercise_01.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();

    void registerCategory(Category category);
}

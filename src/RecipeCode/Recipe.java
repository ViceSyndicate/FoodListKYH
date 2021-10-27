package RecipeCode;

import FoodCode.Food;

import java.util.UUID;

public class Recipe extends Food {

    private String []ingredients;
    private String description;

    public Recipe (String name, String []ingredients, String description, boolean ketoFriendly){
        // Calls the Superclass constructor which assigns those variables.
        super(name, ketoFriendly);
        this.ingredients = ingredients;
        this.description = description;
    }
    public String[] getIngredients() { return ingredients; }
    public String getDescription() { return description; }
}
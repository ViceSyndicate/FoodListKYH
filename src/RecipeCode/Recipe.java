package RecipeCode;

import FoodCode.Food;

public class Recipe extends Food {

    final String []ingredients;
    final String description;

    public Recipe (String name, String []ingredients, String description, boolean ketoFriendly){
        // super Calls the Superclass constructor which assigns those variables.
        super(name, ketoFriendly);
        this.ingredients = ingredients;
        this.description = description;
    }
    public String[] getIngredients() { return ingredients; }
    public String getDescription() { return description; }
}
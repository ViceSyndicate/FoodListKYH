package RecipeCode;

import FoodCode.Food;

import java.util.UUID;

public class Recipe extends Food {

    private String []ingredients;
    private String description;
    private UUID id;

    public Recipe (String name, String []ingredients, String description, boolean ketoFriendly){
        super(name, ketoFriendly);
        this.ingredients = ingredients;
        this.description = description;
        this.id = UUID.randomUUID();
    }
    public String[] getIngredients() { return ingredients; }
    public String getDescription() { return description; }
}
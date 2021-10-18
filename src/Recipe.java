import java.security.PublicKey;
import java.util.ArrayList;

public class Recipe {

    private String name;
    private String []ingredients;
    private String description;
    private String id;
    private boolean isKetoFriendly;

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public static Recipe getIsKetoFriendly() {
        return getIsKetoFriendly();
    }

    public String getDescription() {
        return description;
    }

    public Recipe (String name, String []ingredients, String description, boolean isKetoFriendly){
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.isKetoFriendly = isKetoFriendly;
    }
}
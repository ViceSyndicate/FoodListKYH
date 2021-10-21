import java.io.Serializable;
import java.util.UUID;

public class Recipe implements Serializable {

    private String name;
    private String []ingredients;
    private String description;
    private boolean isKetoFriendly;
    private UUID id;

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
        this.id = UUID.randomUUID();
    }
}
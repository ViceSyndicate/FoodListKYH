import java.io.Serializable;
import java.util.UUID;

public class Food implements Serializable {

    private String id;
    private String name;
    private boolean isKetoFriendly;

    // Takes a string FoodName and Boolean if it is keto friendly or not.
    public Food(String name, boolean isKetoFriendly) {
        this.name = name;
        this.isKetoFriendly = isKetoFriendly;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public boolean getIsKetoFriendly() { return isKetoFriendly; }
}

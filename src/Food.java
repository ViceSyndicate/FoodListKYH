import java.io.Serializable;
import java.util.UUID;

public class Food implements Serializable {

    private String id;
    private String name;
    private boolean ketoFriendly;

    public Food(String name, boolean ketoFriendly) {
        this.name = name;
        this.ketoFriendly = ketoFriendly;
        this.id = UUID.randomUUID().toString();
    }
    public String getName() { return name; }
    public boolean getIsKetoFriendly() { return ketoFriendly; }
}

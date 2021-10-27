package FoodCode;

import java.io.Serializable;

public class Food implements Serializable {

    final String name;
    final boolean ketoFriendly;

    public Food(String name, boolean ketoFriendly) {
        this.name = name;
        this.ketoFriendly = ketoFriendly;
    }






    // putting this here to avoid duplicate code in FoodDataAndLogic & RecipeDataAndLogic.
    // It's used to validate the name of both Foods and Recipes.
    public boolean containsMoreThanThreeNumbersInARow(String string){
        // we need to check if index 0 < string.length()-2 in case you enter a string shorter than 3 letters.
        for (int i = 0; i < string.length() - 2; i++){
            if (Character.isDigit(string.charAt(i)) &&
                    Character.isDigit(string.charAt(i+1)) &&
                    Character.isDigit(string.charAt(i+2))){
                return true;
            }
        }
        return false;
    }

    public String getName() { return name; }
    public boolean getIsKetoFriendly() { return ketoFriendly; }
}

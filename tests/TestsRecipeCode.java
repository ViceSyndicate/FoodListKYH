import FoodCode.Food;
import FoodCode.FoodDataAndLogic;
import RecipeCode.Recipe;
import RecipeCode.RecipeDataAndLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestsRecipeCode {

    //setup
    RecipeDataAndLogic recipeDataAndLogic = new RecipeDataAndLogic();
    ArrayList<Recipe> liveRecipeList = recipeDataAndLogic.getRecipeList();

    static ArrayList<Recipe> mockupValidRecipeList = new ArrayList<Recipe>();
    static ArrayList<Recipe> mockupInvalidRecipeList = new ArrayList<Recipe>();

    // Create mockup Recipes and add them to the mockup ArrayLists for testing.
    @BeforeAll private static void createMockupRecipeLists(){
        String[] pizzaIngredients = {"Cheese", "Dough", "Tomato Sauce", "Ham", "Oregano"};
        Recipe vezuvio = new Recipe("Pizza", pizzaIngredients, "Description Is Not Empty", false );

        String[] ketoPizzaIngredients = {"Cheese", "Keto Dough", "Tomato Sauce", "Ham", "Oregano"};
        Recipe ketoPizza = new Recipe("Keto Pizza", ketoPizzaIngredients, "Description Is Not Empty", true );

        mockupValidRecipeList.add(vezuvio);
        mockupValidRecipeList.add(ketoPizza);

        String[] emptyIngredientsList = {};
        Recipe emptyIgredientsRecipe = new Recipe("Empty Ingredients", emptyIngredientsList, "Description Empty Ingredients", true);

        String[] notEmptyIngredientsList = {"Not Empty"};
        Recipe emptyNameRecipe = new Recipe("", notEmptyIngredientsList, "Description Empty Ingredients", false);
        Recipe numbersInRecipe = new Recipe("2354jti9oe", notEmptyIngredientsList, "24535", true);

        mockupInvalidRecipeList.add(emptyNameRecipe);
        mockupInvalidRecipeList.add(emptyIgredientsRecipe);
        mockupInvalidRecipeList.add(numbersInRecipe);
    }

    // Checks if any strings are empty & if it has more than 0 ingredients &
    // if name has more than 2 consecutive numbers.
    @Test public void testValidRecipesReturnsTrue(){
        for ( Recipe recipe : mockupValidRecipeList) {
            Assertions.assertTrue(recipeDataAndLogic.validRecipe(recipe));
        }
    }

    @Test public void testInvalidRecipesReturnsFalse(){
        for (Recipe recipe : mockupInvalidRecipeList) {
            Assertions.assertFalse(recipeDataAndLogic.validRecipe(recipe));
        }
    }

    @Test public void testNoNullRecipesFromRecipeFile() {
        for ( Recipe recipe : liveRecipeList) {
            Assertions.assertNotNull(recipe);
        }
    }

    @Test public void testRecipesContainsMoreThanThreeNumbersInARow(){
        // Creating temporary recipe here to access the function to test.
        // This function is inherited from Food because i want to check
        // The name of both Recipe.Name and Food.Name.

        Recipe recipe = mockupValidRecipeList.get(1);

        Assertions.assertFalse(recipe.containsMoreThanThreeNumbersInARow("12"));
        Assertions.assertFalse(recipe.containsMoreThanThreeNumbersInARow("1"));
        Assertions.assertFalse(recipe.containsMoreThanThreeNumbersInARow("12qw12"));
        Assertions.assertFalse(recipe.containsMoreThanThreeNumbersInARow("qweewr"));

        Assertions.assertTrue(recipe.containsMoreThanThreeNumbersInARow("qwe3245"));
        Assertions.assertTrue(recipe.containsMoreThanThreeNumbersInARow("32543qwesdf"));
        Assertions.assertTrue(recipe.containsMoreThanThreeNumbersInARow("12345"));
        Assertions.assertTrue(recipe.containsMoreThanThreeNumbersInARow("1234"));
        Assertions.assertTrue(recipe.containsMoreThanThreeNumbersInARow("123"));
    }

    @Test public void testGetKetoListOnlyReturnsKetoRecipes(){
        ArrayList<Recipe> ketoList = recipeDataAndLogic.getKetoList(mockupValidRecipeList);
        for (Recipe recipe : ketoList) {
            Assertions.assertTrue(recipe.getIsKetoFriendly());
        }
    }
}

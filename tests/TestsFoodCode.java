import FoodCode.Food;
import FoodCode.FoodDataAndLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestsFoodCode {


    FoodDataAndLogic foodDataAndLogic = new FoodDataAndLogic();
    ArrayList<Food> liveFoodList = foodDataAndLogic.getFoodList();

    static ArrayList<Food> mockupValidFoodList = new ArrayList<Food>();

    @BeforeAll private static void fillMockupFoodLists(){
        // Creating Foods for the mockupValidFoodList<Food>
        Food potato = new Food("Potato", false);
        Food rice = new Food("Rice", false);
        Food cauliflower = new Food("Cauliflower", true);
        Food beef = new Food("Wagyu Steak", true);

        mockupValidFoodList.add(potato);
        mockupValidFoodList.add(rice);
        mockupValidFoodList.add(cauliflower);
        mockupValidFoodList.add(beef);
    }

    // Checks for no empty Food names.
    @Test public void testFoodsAreValidFromFoodsFile() {
        for ( Food food : liveFoodList) {
            Assertions.assertTrue(foodDataAndLogic.validFood(food));
        }
    }

    @Test public void testNoNullFoodsFromFoodsFile() {
        for ( Food food : liveFoodList) {
            Assertions.assertNotNull(food);
        }
    }

    @Test
    public void testGetKetoListOnlyReturnsKetoFoods(){
        ArrayList<Food> ketoList = foodDataAndLogic.getKetoList(mockupValidFoodList);
        for (Food food : ketoList) {
            Assertions.assertTrue(food.getIsKetoFriendly());
        }
    }

    @Test public void testRecipesContainsMoreThanThreeNumbersInARow(){
        // Creating temporary food here to access the function to test.
        // This function is inherited from Food because i want to check
        // The name of both Recipe.Name and Food.Name.

        Food food = mockupValidFoodList.get(1);

        Assertions.assertFalse(food.containsMoreThanThreeNumbersInARow("12"));
        Assertions.assertFalse(food.containsMoreThanThreeNumbersInARow("1"));
        Assertions.assertFalse(food.containsMoreThanThreeNumbersInARow("12qw12"));
        Assertions.assertFalse(food.containsMoreThanThreeNumbersInARow("qweewr"));

        Assertions.assertTrue(food.containsMoreThanThreeNumbersInARow("qwe3245"));
        Assertions.assertTrue(food.containsMoreThanThreeNumbersInARow("32543qwesdf"));
        Assertions.assertTrue(food.containsMoreThanThreeNumbersInARow("12345"));
        Assertions.assertTrue(food.containsMoreThanThreeNumbersInARow("1234"));
        Assertions.assertTrue(food.containsMoreThanThreeNumbersInARow("123"));
    }
}

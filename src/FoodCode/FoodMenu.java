package FoodCode;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodMenu {

    public FoodMenu() {
        int userChoice = Menu();
        while (userChoice != 10)
            userChoice = Menu();
    }

    private int Menu() {

        FoodDataAndLogic foodDataAndLogic = new FoodDataAndLogic();
        foodDataAndLogic.storageFileExists();
        ArrayList<Food> foodList;

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List All Foods");
        System.out.println("2 - Add Food");
        System.out.println("3 - Delete Item?");
        System.out.println("0 - Back To Main Menu.");

        Scanner inputScanner = new Scanner(System.in);
        // If user doesn't enter valid input. Display menu again.
        if(!inputScanner.hasNextInt()) return 0;

        int selection = inputScanner.nextInt();

        switch (selection) {
            case 0:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Back To Main Menu.");
                return 10;
            case 1:
                System.out.println("Selected 1. List Foods");
                foodList = foodDataAndLogic.getFoodList();
                System.out.println("Keto only? y/n: ");

                Scanner scannerInput = new Scanner(System.in);

                // Clears the list of all non-keto foods
                if (scannerInput.hasNext("y") || scannerInput.hasNext("yes")) {
                    foodList = foodDataAndLogic.getKetoList(foodList);
                }
                System.out.println(("-----------------------------------------------------------------------------"));
                System.out.printf("%33s", "NAME");
                System.out.printf("%12s", "KETO");
                System.out.println();
                System.out.println(("-----------------------------------------------------------------------------"));
                for (Food food : foodList) {
                    System.out.println(String.format("%33s %11s",
                            food.getName(), food.getIsKetoFriendly()));
                }
                return 0;
            case 2:
                System.out.println("Selected 2. Create a Food");
                Food newFood = foodDataAndLogic.createFood();
                if (newFood == null) {
                    System.out.println("The Food information you entered is invalid.");
                    System.out.println("A food can't have an empty name and more than 2 numbers in it.");
                    return 0;
                }
                foodList = foodDataAndLogic.getFoodList();
                foodList.add(newFood);
                foodDataAndLogic.storeFoodArrayList(foodList);
                return 0;
            case 3:
                System.out.println("Selected 3. Delete a Food");
                System.out.print("Enter the name of the food you want to delete: ");
                // If failed to delete Food.
                if (!foodDataAndLogic.deleteByName()){
                    System.out.println("Couldn't removed that Recipe. Did you enter the right Name?");
                }
                return 0;
            default:
                return 0;
        }
    }
}

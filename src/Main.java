import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // I feel like we should have a non static Menu function that can return an error.
        // If it does, we should handle that error in main() and discard the return value.
        // And call the Menu function again.

        // I think this needs to be re-written.
        // While userchoice != 10
        // Go to menu


        int userChoice = Menu();
        while (userChoice != 10)
            userChoice = Menu();
    }

    public static int Menu() {

        FoodDataStorage foodDataStorage = new FoodDataStorage();
        foodDataStorage.storageFileExists();
        int selection;
        List<Food> foodList;
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List All Foods");
        System.out.println("2 - Add Food");
        System.out.println("3 - Delete Item?");
        System.out.println("0 - Quit\n");

        if(!inputScanner.hasNextInt()) return 1;


        selection = inputScanner.nextInt();
        switch (selection) {
            case 0: // Exits Program.
                System.exit(0);
            case 1:
                foodList = foodDataStorage.getFoodList();
                System.out.println("Keto only? y/n: ");

                Scanner scannerInput = new Scanner(System.in);

                // Clears the list of all non-keto foods
                if (scannerInput.hasNext("y") || scannerInput.hasNext("yes"))
                    foodList = foodDataStorage.getKetoList(foodList);
                else

                System.out.println(("-----------------------------------------------------------------------------"));
                System.out.printf("%33s", "NAME");
                System.out.printf("%12s", "KETO");
                System.out.println();
                System.out.println(("-----------------------------------------------------------------------------"));
                for (int i = 0; i < foodList.size(); i++) {
                    System.out.println(String.format("%33s %11s",
                            foodList.get(i).getName(), foodList.get(i).isKetoFriendly()));
                }
                //System.out.println("These are all foods.");
                return 1;
            case 2:
                // System.out.println("Add Food Function");
                Food newFood = foodDataStorage.createFood();
                if (newFood != null) {
                    foodList = foodDataStorage.getFoodList();
                    foodList.add(newFood);
                    foodDataStorage.storeFoodArrayList(foodList);
                }
                return 1;
            case 3:
                System.out.println("Delete Food Function");
                System.out.print("Enter the name of the food you want to delete: ");

                inputScanner = new Scanner(System.in);

                foodDataStorage.deleteFoodByName(inputScanner.nextLine());
                return 1;
            default:
                return 1;
        }
    }
}

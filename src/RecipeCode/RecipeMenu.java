package RecipeCode;

import java.util.List;
import java.util.Scanner;

public class RecipeMenu {
    public RecipeMenu(){
        int userChoice = Menu();
        while(userChoice != 10)
            userChoice = Menu();
    }

    private int Menu() {

        RecipeDataAndLogic recipeDataAndLogic = new RecipeDataAndLogic();
        recipeDataAndLogic.storageFileExists();
        List<Recipe> recipeList;

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List Recipies");
        System.out.println("2 - Add Recipie");
        System.out.println("3 - Delete Recipie");
        System.out.println("0 - Back To Main Menu.");

        Scanner inputScanner = new Scanner(System.in);
        // If user doesn't enter valid input. Display menu again.
        if (!inputScanner.hasNextInt()) return 0;

        int selection = inputScanner.nextInt();

        switch (selection){
            case 0:
                System.out.println("----------------------------------------------------------------------------");
                System.out.println("Back To Main Menu.");
                return 10;
            case 1:
                System.out.println("Selected 1. Ask for Keto or Not. Then List Recipies.");
                recipeList = recipeDataAndLogic.getRecipeList();
                System.out.println("Keto only? y/n: ");

                Scanner scannerInput = new Scanner(System.in);

                // Clears the list of all non-keto foods
                if (scannerInput.hasNext("y") || scannerInput.hasNext("yes")){
                    recipeList = recipeDataAndLogic.getKetoList(recipeList);
                }

                // Comments for understanding formatting
                /*
                 %-s          left align type String
                 %-3s         left align type String with 3 padding
                 %-3.10s      left align type String with 3 padding with 10 char max
                 %[flags][width][.precision]conversion-character
                 */
                System.out.println(("------------------------------------------------------------"));
                String fmt = "%-3s %-48.50s %-12s";
                System.out.printf(fmt, "ID", "| NAME", "| KETO");
                System.out.println();
                System.out.printf("----" + "+------------------------------------------------+" + "------");
                System.out.println();
                for (int i = 0; i < recipeList.size(); i++) {
                    System.out.println(String.format(fmt,
                            i,"| " + recipeList.get(i).getName(), "| " + recipeList.get(i).getIsKetoFriendly()));
                }
                System.out.println("^");
                System.out.println("Enter the ID of the recipe you'd like more information on");
                System.out.println("Or enter anything else than a number to return to the Menu.");

                scannerInput = new Scanner(System.in);
                if (scannerInput.hasNextInt()){
                    Recipe chosenRecipe = recipeList.get(scannerInput.nextInt());
                    System.out.println("Name: "+chosenRecipe.getName());
                    System.out.println("Ingredients:");
                    for (int i = 0; i < chosenRecipe.getIngredients().length; i++) {
                        System.out.println(" - " + chosenRecipe.getIngredients()[i]);
                    }
                    System.out.println("Description: " + chosenRecipe.getDescription());
                }
                return 0;
            case 2:
                System.out.println("Selected 2. Create a RecipeCode.Recipe.");
                Recipe newRecipe = recipeDataAndLogic.createRecipe();
                if (newRecipe != null) { // May be redundant...
                    recipeList = recipeDataAndLogic.getRecipeList();
                    recipeList.add(newRecipe);
                    recipeDataAndLogic.storeRecipeArrayList(recipeList);
                }
                return 0;
            case 3:
                System.out.println("Delete FoodCode.Food Function");
                System.out.print("Enter the name of the food you want to delete: ");

                inputScanner = new Scanner(System.in);

                recipeDataAndLogic.deleteByName(inputScanner.nextLine());
                return 0;
            default:
                return 0;
        }
    }
}

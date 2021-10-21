import java.util.List;
import java.util.Scanner;

public class RecipeMenu {
    RecipeMenu(){
        int userChoice = Menu();
        while(userChoice != 10)
            userChoice = Menu();
    }

    public int Menu() {

        RecipeDataAndLogic recipeDataAndLogic = new RecipeDataAndLogic();
        recipeDataAndLogic.storageFileExists();
        List<Recipe> recipeList;

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List Recipies");
        System.out.println("2 - Add Recipie");
        System.out.println("3 - Delete Recipie");
        System.out.println("0 - Back To Main Menu.\n");

        Scanner inputScanner = new Scanner(System.in);
        // If user doesn't enter valid input. Display menu again.
        if (!inputScanner.hasNextInt()) return 0;

        int selection = inputScanner.nextInt();

        switch (selection){
            case 0:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Back To Main Menu.");
                return 10;
            case 1:
                System.out.println("Selected 1. Ask for Keto or Not. Then List Recipies. WIP");
                recipeList = recipeDataAndLogic.getRecipeList();
                System.out.println("Keto only? y/n: ");

                Scanner scannerInput = new Scanner(System.in);

                // Clears the list of all non-keto foods
                if (scannerInput.hasNext("y") || scannerInput.hasNext("yes")){
                    recipeList = recipeDataAndLogic.getKetoList(recipeList);
                }
                System.out.println(("-----------------------------------------------------------------------------"));
                System.out.printf("%33s", "NAME");
                System.out.printf("%12s", "KETO");
                System.out.println();
                System.out.println(("-----------------------------------------------------------------------------"));
                for (int i = 0; i < recipeList.size(); i++) {
                    System.out.println(String.format("%33s %11s",
                            recipeList.get(i).getName(), recipeList.get(i).getIsKetoFriendly()));
                }
                return 0;
            case 2:
                System.out.println("Selected 2. Create a Recipe.");
                Recipe newRecipe = recipeDataAndLogic.createRecipe();
                if (newRecipe != null) { // May be redundant...
                    recipeList = recipeDataAndLogic.getRecipeList();
                    recipeList.add(newRecipe);
                    recipeDataAndLogic.storeRecipeArrayList(recipeList);
                }
                return 0;
            case 3:
                System.out.println("Delete Food Function");
                System.out.print("Enter the name of the food you want to delete: ");

                inputScanner = new Scanner(System.in);

                recipeDataAndLogic.deleteRecipeByName(inputScanner.nextLine());
                return 0;
            default:
                return 0;
        }
    }
}

import java.util.Scanner;

public class RecipeMenu {

    RecipeMenu(){
        int userChoice = Menu();
        while(userChoice != 10)
            userChoice = Menu();
    }

    public int Menu() {
        Scanner inputScanner = new Scanner(System.in);
        RecipeDataStorage recipeDataStorage = new RecipeDataStorage();
        recipeDataStorage.storageFileExists();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List Recipies");
        System.out.println("2 - Add Recipie");
        System.out.println("3 - Delete Recipie");
        System.out.println("0 - Back To Main Menu.\n");

        if (!inputScanner.hasNextInt())  {
            return 0;
        }

        int selection = inputScanner.nextInt();

        switch (selection){
            case 0:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Back To Main Menu.");
                return 10;
            case 1:
                System.out.println("Selected 1. Ask for Keto or Not. Then List Recipies. WIP");
            case 2:
                System.out.println("Selected 2. Create a Recipe. WIP");
                Recipe recipe = recipeDataStorage.createRecipe();
            case 3:
                System.out.println("Selected 3. Delete a Recipe. WIP");
            default:
                return 0;
        }
    }
}

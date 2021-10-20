import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class RecipeDataStorage {
    String fileName = "RecipeStorage.txt";

    public boolean storageFileExists(){
        File storageFile = new File(fileName);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            }
            catch (Exception e){
                System.out.println(e);
            }
            return true;
        }
        return false;
    }

    public Recipe createRecipe() {
        String name;
        String []ingredients;
        String description;
        boolean isKetoFriendly = false;

        System.out.print("Enter the Recipe name: ");

        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();


        System.out.println("Enter the ingredients separated with , . or a space: ");
        ingredients = scanner.toString().split("[\\,\\.\\ ]+");

        System.out.println("Please Enter a description on how to cook the recipe: ");
        description = scanner.nextLine();

        System.out.println("\nIs it Keto friendly? Yes/No: ");
        if (scanner.hasNext("y") || scanner.hasNext("yes")) {
            isKetoFriendly = true;
        }

        Recipe recipe = new Recipe(name, ingredients, description, isKetoFriendly);
        return recipe;
    }
}

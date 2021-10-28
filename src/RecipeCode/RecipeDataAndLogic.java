package RecipeCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class RecipeDataAndLogic {
    String currentDir = System.getProperty("user.dir") + "\\src\\Data\\";
    String fileName = currentDir + "RecipeStorage.bin";

    public RecipeDataAndLogic(){
        storageFileExists();
    }

    public void storageFileExists() {
        File storageFile = new File(fileName);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void storeRecipeArrayList(List<Recipe> recipeList) {
        try {
            FileOutputStream fileOutPutStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutPutStream);

            for (Recipe recipe : recipeList) {
                objectOutputStream.writeObject(recipe);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Recipe createRecipe() {
        String name;
        String[] ingredients;
        String description;
        boolean isKetoFriendly = false;

        System.out.print("Enter the Recipe name: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();

        System.out.println("Enter the ingredients separated with , or . : ");
        ingredients = scanner.nextLine().split("[\\,\\.]+");

        System.out.print("Please Enter a description on how to cook the recipe: ");
        description = scanner.nextLine();

        System.out.println("\nIs it Keto friendly? Yes/No: ");
        if (scanner.hasNext("y") || scanner.hasNext("yes")) {
            isKetoFriendly = true;
        }
        Recipe recipe = new Recipe(name, ingredients, description, isKetoFriendly);
        // If the recipe is Invalid: Return null.
        if (!validRecipe(recipe)){ return null; }
        return recipe;
    }

    public boolean validRecipe(Recipe recipe){
        if (recipe.getName().isEmpty()) {
            return false;
        }
        if (recipe.getIngredients().length < 1){
            return false;
        }
        // Check every Ingredient in the recipe and return false if any of them are empty strings.
        for (int i = 0; i < recipe.getIngredients().length; i ++){
            if (recipe.getIngredients()[i].isEmpty()) { return false; }
        }
        // I know I can simplify this but this way of writing it is easier for me to understand.
        if (recipe.containsMoreThanThreeNumbersInARow(recipe.getName())) { return false; }
        return true;
    }

    public ArrayList<Recipe> getRecipeList() {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        try {
            FileInputStream inputFile = new FileInputStream(fileName);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);
            while (true) {
                // May need to cast directly to "ArrayList<RecipeCode.Recipe> recipeList = (ArrayList<RecipeCode.Recipe>)"
                Recipe recipe = (Recipe) inputObject.readObject();
                recipeList.add(recipe);
            }
        } catch (EOFException eof) {} // Reached end of file.
        // Everything was most likely read properly.
        // I get a EOF Exception if i don't have this, even though it does nothing.
        catch (IOException | ClassNotFoundException ex) {
            // Most likely triggered if data is corrupt or changed.
            ex.printStackTrace();
        }
        return recipeList;
    }

    public ArrayList<Recipe> getKetoList(List<Recipe> recipeList) {

        ArrayList<Recipe> ketoFoods = new ArrayList<Recipe>();

        for (Recipe recipe : recipeList) {
            if (recipe.getIsKetoFriendly())
                ketoFoods.add(recipe);
        }
        return ketoFoods;
    }

    public boolean deleteByName() {
        boolean deleted = false;
        ArrayList<Recipe> recipeList = getRecipeList();
        Scanner scanner = new Scanner(System.in);
        String recipeName = scanner.nextLine();

        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeName.equalsIgnoreCase(recipeList.get(i).getName())) {
                System.out.println("Removing: " + recipeList.get(i).getName());
                recipeList.remove(i);
                deleted = true;
            }
        }
        // Store new RecipeList after having removed the Recipe.
        storeRecipeArrayList(recipeList);
        return deleted;
    }
}
package RecipeCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class RecipeDataAndLogic implements Interface.IData {
    String fileName = "RecipeStorage.txt";

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

        System.out.print("Enter the RecipeCode.Recipe name: ");
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
        return recipe;
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
        } catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return recipeList;
    }

    public ArrayList<Recipe> getKetoList(List<Recipe> recipeList) {

        ArrayList<Recipe> ketoFoods = new ArrayList<Recipe>();

        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getIsKetoFriendly() == true)
                ketoFoods.add(recipeList.get(i));
        }
        return ketoFoods;
    }

    public void deleteByName(String recipeName) {
        ArrayList<Recipe> recipeList = getRecipeList();

        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeName.equalsIgnoreCase(recipeList.get(i).getName())) {
                System.out.println("Removing: " + recipeList.get(i).getName());
                recipeList.remove(i);
            }
        }
        storeRecipeArrayList(recipeList);
    }
}
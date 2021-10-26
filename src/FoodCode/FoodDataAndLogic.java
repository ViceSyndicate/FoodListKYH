package FoodCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FoodDataAndLogic {
    String currentDir = System.getProperty("user.dir") + "\\src\\Data\\";
    String fileName = currentDir + "FoodStorage.txt";

    public FoodDataAndLogic(){
        storageFileExists();
    }

     public boolean storageFileExists(){
        File storageFile = new File(fileName);
        if (!storageFile.exists()) {
            try {
                return storageFile.createNewFile();
            } catch (IOException e){
                System.out.println(e);
            }
        }
        return false;
    }

    public void storeFoodArrayList(List<Food> foodList) {
        try {
            FileOutputStream fileOutPutStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutPutStream);
            for (Food food : foodList) {
                objectOutputStream.writeObject(food);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Food createFood() {
        String foodName;
        boolean isKetoFriendly = false;

        System.out.print("Enter the name of the food: ");
        Scanner scanner = new Scanner(System.in);
        foodName = scanner.nextLine();

        System.out.println("\nIs it Keto friendly? Yes/No: ");
        if (scanner.hasNext("y") || scanner.hasNext("yes")) {
            isKetoFriendly = true;
        }
        Food food = new Food(foodName, isKetoFriendly);
        // If food is NOT valid
        if (!validFood(food)){ return null; }
        return food;
    }

    public boolean validFood(Food food){
        // if food.name is empty, Return false.
        if (food.getName().isEmpty()) return false;
        // if food.name has 3 consecutive numbers in it, return false.
        if (food.containsMoreThanThreeNumbersInARow(food.getName())) return false;
        return true;
    }

    public ArrayList<Food> getFoodList() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        try {
            FileInputStream inputFile = new FileInputStream(fileName);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);

            while (true) {
                // May need to cast directly to "ArrayList<FoodCode.Food> foodList = (ArrayList<FoodCode.Food>)"
                Food food = (Food) inputObject.readObject();
                foodList.add(food);
            }
        }
        catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return foodList;
    }

    public ArrayList<Food> getKetoList(ArrayList<Food> foodList) {

        ArrayList<Food> ketoFoods = new ArrayList<>();

        for (int i = 0; i < foodList.size(); i++)
        {
            if (foodList.get(i).getIsKetoFriendly() == true)
                ketoFoods.add(foodList.get(i));
        }
        return ketoFoods;
    }

    public boolean deleteByName() {
         boolean deleted = false;
         ArrayList<Food> foodList = getFoodList();
         Scanner scanner = new Scanner(System.in);
         String foodName = scanner.nextLine();
         for(int i = 0; i < foodList.size(); i++){
             if (foodName.equalsIgnoreCase(foodList.get(i).getName())) {
                 System.out.println("Removing: " + foodList.get(i).getName());
                 foodList.remove(i);
                 deleted = true;
             }
         }
         // Store new FoodList after having removed the Food.
         storeFoodArrayList(foodList);
         return deleted;
    }
}

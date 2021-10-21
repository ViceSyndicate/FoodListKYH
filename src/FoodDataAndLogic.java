import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class FoodDataAndLogic {

    String fileName = "FoodStorage.txt";

     public void storageFileExists(){
        File storageFile = new File(fileName);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
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

    public List<Food> getKetoList(List<Food> foodList) {

        ArrayList<Food> ketoFoods = new ArrayList<>();

        for (int i = 0; i < foodList.size(); i++)
        {
            if (foodList.get(i).getIsKetoFriendly() == true)
                ketoFoods.add(foodList.get(i));
        }
        return ketoFoods;
    }

    public List<Food> getFoodList() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        try {
            FileInputStream inputFile = new FileInputStream(fileName);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);

            while (true) {
                // May need to cast directly to "ArrayList<Food> foodList = (ArrayList<Food>)"
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

        return new Food(foodName, isKetoFriendly);
    }

    public void deleteFoodByName(String foodName) {
        List<Food> foodList = getFoodList();

        for(int i = 0; i < foodList.size(); i++){
            if (foodName.equalsIgnoreCase(foodList.get(i).getName().toLowerCase())) {
                System.out.println("Removing: " + foodList.get(i).getName());
                foodList.remove(i);
            }
        }
        storeFoodArrayList(foodList);
    }
}

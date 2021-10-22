import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // While userchoice != 10
        // Go to menu

            int userChoice = Menu();
            while(userChoice != 10)
                userChoice = Menu();
    }

    public static int Menu() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - Go To Food Menu");
        System.out.println("2 - Go To Recipies Menu");
        System.out.println("0 - Quit\n");

        if (!inputScanner.hasNextInt())  {
            return 0;
        }

        int selection = inputScanner.nextInt();

        switch (selection){
            case 0:
                System.out.println("Selected 0. Exit Program");
                return 10;
            case 1:
                FoodMenu foodMenu = new FoodMenu();
                return 0;
            case 2:
                RecipeMenu recipeMenu = new RecipeMenu();
                return 0;
            default:
                return 0;
        }
    }

}

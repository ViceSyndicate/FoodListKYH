import java.util.Scanner;

public class RecipeMenu {

    RecipeMenu(){
        int userChoice = Menu();
        while(userChoice != 10)
            userChoice = Menu();
    }

    public int Menu() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("1 - List Recipies");
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
                System.out.println("Selected 1. Ask for Keto or Not. Then List Recipies.");
            default:
                return 0;
        }
    }
}

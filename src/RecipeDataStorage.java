import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class RecipeDataStorage {
    String fileName = "RecipeStorage.txt";

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
}

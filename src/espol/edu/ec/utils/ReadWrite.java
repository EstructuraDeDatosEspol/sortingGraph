package espol.edu.ec.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author SSAM
 */
public class ReadWrite {
    
    public static ArrayList<Integer> readFile(String file) throws IOException {
        ArrayList<Integer> array = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(file))){
            String line;
            while((line=bf.readLine()) != null){
                array.add(Integer.parseInt(line));
            }
        }catch(NumberFormatException ex) {
                return array;
        }
        return array;
    }
}
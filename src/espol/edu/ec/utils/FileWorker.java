package espol.edu.ec.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Miguel PS
 */
public class FileWorker {


    /**
     * Genera una lista con números aleatorios sin repetir
     * @param cant: cantidad de numeros a generar
     * @return  lista de numeros generados
     */
    public static List<Integer> loadRandomNumbers(Integer cant){
        List<Integer> numbers = new ArrayList<>();
        while(numbers.size()<cant){
            Integer n = (int)(Math.random()*cant*2);
            if(!numbers.contains(n))
                numbers.add(n);
        }

        return numbers;
    }


    /**
     * Lee numeros desde un archivo de texto. Si no es un número,
     * no lo agrega.
     * @param path  Ruta del archivo.
     * @return lista con nuúmeros.
     */
    public static List<Integer> loadNumbersFromFile(String path){
        List<Integer> numbers = new ArrayList<>();

        Scanner s;

        try {
            String line;
            s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                line = s.nextLine();
                System.out.println(line);
                if(checkValidNumber(line))
                    numbers.add(Integer.parseInt(line));
            }
            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al leer el archivo. " + e.getMessage());
        }

        return numbers;
    }


    /**
     *
     * @param path ruta del archivo.
     * @param start numero de la linea por la cual empezar a guardar.
     * @param end numero de la linea por la cual terminar de guardar.
     * @return lista con números leidos.
     */
    public static List<Integer> loadNumbersFromFile(String path, int start, int end ){
        List<Integer> numbers = new ArrayList<>();

        Scanner s;

        try {
            String line;
            s = new Scanner(new File(path));
            int actual = 0;
            while (actual++ <= start){
                s.nextLine();
            }


            while ((actual++ < end) && s.hasNextLine()) {
                line = s.nextLine();
                actual++;
                if(checkValidNumber(line))
                    numbers.add(Integer.parseInt(line));
            }
            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error al leer el archivo. " + e.getMessage());
        }
        return numbers;
    }

    /**
     * Verifica si un string representa un entero.
     * @param number String que representa el número.
     * @return true, si el string en un número entero.
     */
    public static boolean checkValidNumber(String number){
        Scanner sc = new Scanner(number.trim());
        return sc.hasNextInt();
    }
}
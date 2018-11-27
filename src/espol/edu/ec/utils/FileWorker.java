package espol.edu.ec.utils;

import espol.edu.ec.tda.Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    public static boolean generateFile(List<List<Entry>> lista, int tamanio){
        
        
        String[] nombres = {"insertionSort (ms)","quickSort (ms)","mergeSort (ms)","stoogeSort (ms)"};
        String tab = "\t";
        String salto = "\n";
        
        
        ArrayList<Integer> indices = new ArrayList<>();
        
        try{
            //agrega los headers
            PrintWriter pw = new PrintWriter(new File("resultados.txt"));
            pw.append("Numero de Elementos: ");
            pw.append(String.valueOf(tamanio));
            pw.append(salto);
            pw.append("n");
            
            for (int i = 0 ; i< lista.size(); i++){
                if(!lista.get(i).isEmpty()){
                    indices.add(i);
                    pw.append(tab);
                    pw.append(nombres[i]);
                }
            }
            pw.append(salto);
            
            //agrega los tiempos
            
            for(int i =0; i< lista.get(indices.get(0)).size(); i++){
                pw.append(String.valueOf(lista.get(indices.get(0)).get(i).getN()));
                pw.append(tab);
                pw.append(tab);
                pw.append(tab);
                for(Integer indice: indices){
                    pw.append(String.valueOf(lista.get(indice).get(i).getTime()));
                    pw.append(tab);
                    pw.append(tab);
                    pw.append(tab);
                }
                pw.append(salto);
            }
            pw.close();
            
        }catch(FileNotFoundException e){
            
        }
        
        return true;
    }
}
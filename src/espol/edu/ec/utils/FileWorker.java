package espol.edu.ec.utils;

import espol.edu.ec.tda.Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        
        if(start > end){
            int contenedor = end;
            end = start;
            start = contenedor;
        }
        Scanner s;

        try {
            String line;
            s = new Scanner(new File(path));
            int actual = 0;
            while (++actual < start)
                s.nextLine();

            System.out.println("desde "+start+" "+end);

            while ((actual++ <= end) && s.hasNextLine()) {
                line = s.nextLine();
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
        if(!sc.hasNextInt() )
            return false;
        if(sc.nextInt() < 1)
            return false;
        return true;
    }
    
    public static boolean generateFile(List<List<Entry>> lista, int tamanio){
        String tab = "\t";
        String direccion = new File("resultados.txt").getAbsolutePath();
        String algoritmos[] = {"insert(ms)","quick(ms)","merge(ms)","stooge(ms)"};
        List<Integer> cantidades = new ArrayList<>();
        int iteraciones = 0;
        
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion))){
            StringBuilder bld = new StringBuilder();
            bld.append("Número de Datos: ");
            bld.append(tamanio);
            bw.write(bld.toString());
            bw.newLine();
            
            //Primera Linea
            bld.delete(0, bld.length());
            bld.append("n");
            bld.append(tab);
            bld.append(tab);
            int pos = 0, posAlgoritmo = 0;
            
            for(List<Entry> l: lista){
                if(!l.isEmpty()){
                    bld.append(algoritmos[posAlgoritmo]);
                    bld.append(tab);
                    bld.append(tab);
                    if((pos++) == 0){
                        for(Entry e: l)
                            cantidades.add(e.getN());
                        iteraciones = l.size();
                    }
                }
                posAlgoritmo++;
            }
            bw.write(bld.toString());
            bw.newLine();
            bld.delete(0, bld.length());
            
            for(int i = 1; i<iteraciones; i++){
                bld.append(String.valueOf(cantidades.get(i)));
                bld.append(tab);
                bld.append(tab);    
                if(!lista.get(0).isEmpty()){
                    bld.append(lista.get(0).get(i).getTime());
                    bld.append(tab);
                    bld.append(tab);
                    bld.append(tab);
                }
                if(!lista.get(1).isEmpty()){
                    bld.append(lista.get(1).get(i).getTime());
                    bld.append(tab);
                    bld.append(tab);
                    bld.append(tab);
                }
                if(!lista.get(2).isEmpty()){
                    bld.append(lista.get(2).get(i).getTime());
                    bld.append(tab);
                    bld.append(tab);
                    bld.append(tab);
                }
                if(!lista.get(3).isEmpty())
                    bld.append(lista.get(3).get(i).getTime());
                bw.write(bld.toString());
                bw.newLine();
                bld.delete(0, bld.length());
            }
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error al escribir el archivo. " + ex.getMessage());
        }
        return true;
    }
}
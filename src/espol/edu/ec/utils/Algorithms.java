/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.utils;

import espol.edu.ec.tda.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author SSAM
 */
class Algorithms<E> {
    private Comparator<E> f;
    private int steps = 5;
    private int fraction = 150;
    private int max = 1000;

    protected Algorithms(Comparator<E> f){
        this.f = f;
    }


    /**
     * Hace llamado al metodo insertionSort
     * @param elements lista completa de elementos.
     * @return  Lista con entries de clave/valor; clave: numero de elementos "n", valor: tiempo de ejecucion respectivo
     */
    List<Entry> getInsertionSortTimes(List<E> elements){
        
        if(elements.size() > max)
             steps = elements.size()/ fraction;
        List<Entry> results = new ArrayList<>();
        List<E> tempList;
        double start;
        double end;
        for (int n = 0; n <= elements.size(); n += steps){
            tempList = copyElements(elements.subList(0, n));
            start = System.currentTimeMillis();
            insertionSort(tempList);
            end = System.currentTimeMillis();

            results.add(new Entry(n, end - start));
        }

        return results;
    }


    /**
     * Hace llamado al metodo quickSort
     * @param elements lista completa de elementos.
     * @return  Lista con entries de clave/valor; clave: numero de elementos "n", valor: tiempo de ejecucion respectivo
     */
    List<Entry> getQuickSortTimes(List<E> elements){
        if(elements.size() > max)
             steps= elements.size()/ fraction;
        List<Entry> results = new ArrayList<>();
        List<E> tempList;
        double start;
        double end;
        for (int n = 0; n <= elements.size(); n += steps){
            tempList = copyElements(elements.subList(0, n));
            start = System.currentTimeMillis();
            quickSort(tempList);
            end = System.currentTimeMillis();
            results.add(new Entry(n, end - start));
        }
        return results;
    }


    /**
     * Hace llamado al metodo mergeSort
     * @param elements lista completa de elementos.
     * @return  Lista con entries de clave/valor; clave: numero de elementos "n", valor: tiempo de ejecucion respectivo
     */
    List<Entry> getMergeSortTimes(List<E> elements){
        if(elements.size() > max)
             steps= elements.size()/ fraction;
        List<Entry> results = new ArrayList<>();
        List<E> tempList;
        double start;
        double end;
        for (int n = 0; n <= elements.size(); n += steps){
            tempList = copyElements(elements.subList(0, n));
            start = System.currentTimeMillis();
            mergeSort(tempList);
            end = System.currentTimeMillis();
            results.add(new Entry(n, end -  start));
        }
        System.out.println(results.size());

        return results;
    }


    /**
     * Hace llamado al metodo stoogeSort
     * @param elements lista completa de elementos.
     * @return  Lista con entries de clave/valor; clave: numero de elementos "n", valor: tiempo de ejecucion respectivo
     */
    List<Entry> getStoogeSortTimes(List<E> elements){
        if(elements.size() > max)
             steps= elements.size()/ fraction;
        List<Entry> results = new ArrayList<>();
        List<E> tempList;
        double start;
        double end;
        for (int n = 0; n <= elements.size(); n += steps){
            tempList = copyElements(elements.subList(0, n));
            start = System.currentTimeMillis();
            stoogeSort(tempList);
            end = System.currentTimeMillis();
            results.add(new Entry(n, end - start));
        }

        return results;
    }


    /**
     * metodo insertionSort
     * @param arr Lista de elementos
     *
     */
    private void insertionSort(List<E> arr){
        int n = arr.size();

        for (int i=1; i<n; ++i){
            E key = arr.get(i);
            int j = i-1;

            /*
	            Comparamos si el valor de la parte ya ordenada (i-1) es mayor que
	            la nueva posicion (i) y asi hasta que quede ordenado todo y como el
	            de (i) ya esta guardado al final se pone en la posicion
	            correspondiente
            */
            while (j>=0 && f.compare(arr.get(j), key) > 0){
                arr.set(j+1, arr.get(j));
                j = j-1;
            }
            arr.set(j+1, key);
        }
    }


    /**
     * da inicio a metodo quickSort
     * @param elements lista de elementos
     *
     */
    private void quickSort(List<E> elements){
        quickSort(elements, 0 , elements.size()-1);
    }

    /**
     *
     * @param arr  lista de elementos
     * @param low  posicion mas baja
     * @param high  posicion mas alta
     *
     */
    private void quickSort(List<E> arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    /**
     *
     * @param arr  lista de elementos
     * @param low  posicion mas baja
     * @param high  posicion mas alta
     *
     */
    private int partition(List<E> arr, int low, int high){
        E pivot = arr.get(high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++){
            if (f.compare(arr.get(j), pivot) < 0){
                i++;
                // cambio
                E temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        E temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        return i+1;
    }


    /**
     * da inicio a metodo mergeSort
     * @param elements lista de elementos
     *
     */
    private void mergeSort(List<E> elements){

        mergeSort(elements, 0, elements.size()-1);
    }

    /**
     *
     * @param arr  lista de elementos
     * @param lf  posicion izquierda
     * @param rg  posicion derecha
     *
     */
    private void mergeSort(List<E> arr, int lf, int rg){
        if (lf < rg){
            //Encontramos el punto medio
            int m = (lf+rg)/2;

            // Ordenamos primera y segunda mitad
            mergeSort(arr, lf, m);
            mergeSort(arr , m+1, rg);

            //unimos las mitades
            merge(arr, lf, m, rg);
        }
    }

    /**
     *
     * @param arr  lista de elementos
     * @param l  posicion izquierda
     * @param r  posicion derecha
     * @param m posicion media (piso)
     */
    private void merge(List<E> arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        //Creamos arrays temporales
        E L[] = (E[])new Object[n1];
        E R[] = (E[])new Object[n2];

        //Copiamos los datos a los arrays temporales
        for (int i=0; i<n1; ++i)
            L[i] = arr.get(l + i);
        for (int j=0; j<n2; ++j)
            R[j] = arr.get(m + 1+ j);

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (f.compare(L[i], R[j]) < 0){
                arr.set(k, L[i]);
                i++;
            }else{
                arr.set(k, R[j]);
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1){
            arr.set(k, L[i]);
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2){
            arr.set(k, R[j]);
            j++;
            k++;
        }
    }


    /**
     * da inicio a metodo stoogeSort
     * @param elements lista de elementos
     *
     */
    private void stoogeSort(List<E> elements){
        stoogesort(elements, 0, elements.size()-1);
    }

    /**
     *
     * @param arr  lista de elementos
     * @param l  posicion baja osea inicio
     * @param h  posicion alta osea techo de (2/3)*n
     */
    private void stoogesort(List<E> arr, int l, int h){
        if (l >= h)
            return;

        if (f.compare(arr.get(l), arr.get(h)) >0 ){
            E temp = arr.get(l);
            arr.set(l, arr.get(h));
            arr.set(h, temp);
        }

        if (h - l + 1 > 2){
            int t = (h - l + 1) / 3;
            //los primeros 2/3 de elementos
            stoogesort(arr, l, h - t);

            //los ultimpos 2/3 elementos
            stoogesort(arr, l + t, h);

            //se confirma ordenar de nuevo los primeros 2/3
            stoogesort(arr, l, h - t);
        }
    }


    private List<E> copyElements(List<E> elements){
        List<E> copy = new ArrayList<>();
        for (int i = 0; i<elements.size(); i++)
            copy.add(elements.get(i));

        return copy;
    }
}

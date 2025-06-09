package ejercicio6;

import AVL.AVL;
import comparator.DefaultComparator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVL<Integer> tree = new AVL<>(new DefaultComparator<Integer>());
        Scanner teclado = new Scanner(System.in);
        ingresoNumeros(tree);
        System.out.println("Impresion por niveles: ");
        tree.impresionNiveles();
        System.out.println("Impresion en orden: ");
        tree.inOrderTraversal();

    }
    private static void ingresoNumeros(AVL<Integer> tree){
        Scanner teclado = new Scanner(System.in);
        System.out.println("El ingreso finalizara cuando se ingrese un .");
        System.out.println("Ingrese un numero");
        String ingreso = teclado.nextLine();
        while (esDigito(ingreso)){
            tree.insert(Integer.parseInt(ingreso));
            System.out.println("Ingrese un numero");
            ingreso = teclado.nextLine();
        }
    }
    private static boolean esDigito(String ingreso){
        boolean resultado = true;
        try{
            Integer.parseInt(ingreso);
        }
        catch (NumberFormatException e) {
            resultado = false;
        }
        finally {
            return resultado;
        }
    }
}

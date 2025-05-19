package aBB;

import comparator.DefaultComparator;

public class Main {
    public static void main (String [] args){
        ABB<Integer> arbol = new ABB<>(new DefaultComparator<Integer>());
        arbol.insert(2);
        arbol.insert(3);
        arbol.insert(6);
        arbol.insert(5);
        arbol.insert(4);
        arbol.insert(1);
        System.out.println(arbol.pertenece(2));
        System.out.println(arbol.pertenece(3));
        System.out.println(arbol.delete(3));
        System.out.println(arbol.delete(2));
        System.out.println(arbol.delete(5));
        System.out.println(arbol.delete(1));
        System.out.println(arbol.pertenece(1));

    }
}

package aBB;

import comparator.DefaultComparator;

import java.util.List;

public class Main {
    public static void main (String [] args){
        ABB<Integer> arbol = new ABB<>(new DefaultComparator<Integer>());

        arbol.insert(3);
        arbol.insert(6);
        arbol.insert(5);
        arbol.insert(7);
        arbol.insert(2);
        arbol.insert(5);
        arbol.insert(4);
        arbol.insert(1);
        recorridos<Integer> recorridos = new recorridos<>();
        List<Integer> arrayPre = recorridos.preOrder(arbol);
        System.out.println(arrayPre);
        List<Integer> arrayIn = recorridos.inOrder(arbol);
        System.out.println(arrayIn);
        List<Integer> arrayPost = recorridos.postOrder(arbol);
        System.out.println(arrayPost);
        arbol.impresionNiveles();
        System.out.println(arbol.alturaArbol());
    }
}

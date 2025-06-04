package aBB;

import java.util.ArrayList;
import java.util.List;


public class Recorridos <T extends Comparable<T>>{
    public List<T> preOrder(ABB<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodeABB<T> raiz = arbol.getRaiz();
        elementosPreRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }

    // Método auxiliar recursivo para recorrido pre-order
    private void elementosPreRecursivo(NodeABB<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }

        // PRE-ORDER: Raíz -> Izquierda -> Derecha

        // 1. Procesar la raíz (nodo actual)
        elementos.add(nodo.getData());

        // 2. Recorrer subárbol izquierdo
        elementosPreRecursivo(nodo.getLeftNode(), elementos);

        // 3. Recorrer subárbol derecho
        elementosPreRecursivo(nodo.getRightNode(), elementos);
    }
    public List<T> inOrder(ABB<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodeABB<T> raiz = arbol.getRaiz();
        elementosInRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }
    private void elementosInRecursivo(NodeABB<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }
        // IN-ORDER: Izquierda -> Raiz -> Derecha
        elementosInRecursivo(nodo.getLeftNode(), elementos);
        elementos.add(nodo.getData());
        elementosInRecursivo(nodo.getRightNode(), elementos);
    }
    public List<T> postOrder(ABB<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodeABB<T> raiz = arbol.getRaiz();
        elementosPostRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }
    private void elementosPostRecursivo(NodeABB<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }
        // Post-ORDER: Izquierda -> Derecha -> Raiz
        elementosPostRecursivo(nodo.getLeftNode(), elementos);
        elementosPostRecursivo(nodo.getRightNode(), elementos);
        elementos.add(nodo.getData());
    }
}

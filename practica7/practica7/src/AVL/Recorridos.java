package AVL;

import java.util.ArrayList;
import java.util.List;


public class Recorridos <T extends Comparable<T>>{
    public List<T> preOrder(AVL<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodoAVL<T> raiz = arbol.getRaiz();
        elementosPreRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }

    // Método auxiliar recursivo para recorrido pre-order
    private void elementosPreRecursivo(NodoAVL<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }

        // PRE-ORDER: Raíz -> Izquierda -> Derecha

        // 1. Procesar la raíz (nodo actual)
        elementos.add(nodo.getData());

        // 2. Recorrer subárbol izquierdo
        elementosPreRecursivo(nodo.getLeft(), elementos);

        // 3. Recorrer subárbol derecho
        elementosPreRecursivo(nodo.getRight(), elementos);
    }
    public List<T> inOrder(AVL<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodoAVL<T> raiz = arbol.getRaiz();
        elementosInRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }
    private void elementosInRecursivo(NodoAVL<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }
        // IN-ORDER: Izquierda -> Raiz -> Derecha
        elementosInRecursivo(nodo.getLeft(), elementos);
        elementos.add(nodo.getData());
        elementosInRecursivo(nodo.getRight(), elementos);
    }
    public List<T> postOrder(AVL<T> arbol) {
        ArrayList<T> elementos = new ArrayList<>();
        NodoAVL<T> raiz = arbol.getRaiz();
        elementosPostRecursivo(raiz, elementos);

        // Convertir ArrayList a arreglo
        return elementos;
    }
    private void elementosPostRecursivo(NodoAVL<T> nodo, ArrayList<T> elementos) {
        if (nodo == null) {
            return;
        }
        // Post-ORDER: Izquierda -> Derecha -> Raiz
        elementosPostRecursivo(nodo.getLeft(), elementos);
        elementosPostRecursivo(nodo.getRight(), elementos);
        elementos.add(nodo.getData());
    }
}

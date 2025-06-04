package Ejercicio7;
import aBB.ABB;
import aBB.Recorridos;
import java.util.*;


public class aBBDictionary<K extends Comparable<K>, V extends Comparable <V>>  {
    protected Node<Entrada<K, ABB<V>>> head;
    protected Node<Entrada<K, ABB<V>>> tail;
    protected int size;
    private Recorridos recorridos;
    public aBBDictionary() {
        head = null;
        tail = null;
        size = 0;
        recorridos = new Recorridos();
    }


    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public Iterable<V> get(K k){
        if (size == 0) {
            return null;
        }
        Node<Entrada<K, ABB<V>>> actual = head;
        while (actual != null && !actual.getElement().getKey().equals(k)) {
            actual = actual.getNext();
        }
        if (actual != null) {
            List<V> listaValores = recorridos.inOrder(actual.getElement().getValue());
            return listaValores;
        }
        return null;
    }
    public void put(K k, V v){
        if (size == 0) {
            ABB<V> arbol = new ABB<>();
            arbol.insert(v);
            Entrada<K, ABB<V>> entrada = new Entrada<>(k, arbol);
            Node<Entrada<K, ABB<V>>> nuevoNodo = new Node<>(entrada, null, null);
            tail = nuevoNodo;
            head = tail;
            size++;
            return;
        }
        Node<Entrada<K, ABB<V>>> actual = head;
        while (actual != null && !actual.getElement().getKey().equals(k)) {
            actual = actual.getNext();
        }
        if(actual != null){
            actual.getElement().getValue().insert(v);
        }
        else {
            ABB<V> arbol = new ABB<>();
            arbol.insert(v);
            Entrada<K, ABB<V>> entrada = new Entrada<>(k, arbol);
            Node<Entrada<K, ABB<V>>> nuevoNodo = new Node<>(entrada, null, tail);
            tail.setNext(nuevoNodo);
            tail = nuevoNodo;
            size++;
        }
    }
    public Iterable<V> remove(K k){
        if(this.isEmpty())
            return null;
        else {
            Node<Entrada<K, ABB<V>>> aux = head;
            while(aux != null && aux.getElement().getKey().compareTo(k)!= 0) {
                aux = aux.getNext();
            }
            if(aux == null)
                return null;
            List <V> valores = recorridos.preOrder(aux.getElement().getValue());
            if (aux == head && aux == tail) {
                head = null;
                tail = null;
            } else if (aux == head) {
                head = head.getNext();
                if (head != null) {
                    head.setPrev(null);
                }
            } else if (aux == tail) {
                tail = tail.getPrev();
                if (tail != null) {
                    tail.setNext(null);
                }
            } else {
                aux.getPrev().setNext(aux.getNext());
                aux.getNext().setPrev(aux.getPrev());
            }
            size--;
            return valores;
        }
    }
    public V remove(K k, V v){
        if(this.isEmpty())
            return null;
        else {
            Node<Entrada<K, ABB<V>>> aux = head;
            while(aux != null && aux.getElement().getKey().compareTo(k)!= 0 ) {
                aux = aux.getNext();
            }
            if(aux == null)
                return null;
            return aux.getElement().getValue().delete(v);
        }
    }
    public Iterable<K> keys(){
            List<K> claves = new LinkedList<>();
            Node<Entrada<K, ABB<V>>> actual = head;
            while (actual != null) {
                claves.addLast(actual.getElement().getKey());
                actual = actual.getNext();
            }
            return claves;
    }
    public Iterable<Entrada<K, Iterable<V>>> entries(){
        LinkedList<Entrada<K, Iterable<V>>> entradas = new LinkedList<>();
        Node<Entrada<K, ABB<V>>> actual = head;
        while (actual != null) {
            List<V> copiaValores = recorridos.inOrder(actual.getElement().getValue());
            entradas.addLast(new Entrada<>(actual.getElement().getKey(), copiaValores));
            actual = actual.getNext();
        }
        return entradas;
    }
}

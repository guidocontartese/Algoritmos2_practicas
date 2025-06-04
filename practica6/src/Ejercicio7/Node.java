package Ejercicio7;

public class Node<T> {

    protected T element;
    protected Node<T> next;
    protected Node<T> prev;

    public Node(T element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public Node(T element, Node<T> next, Node<T> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public Node<T> getNext() {
        return this.next;
    }
    public Node<T> getPrev() {
        return this.prev;
    }

    public T getElement() {
        return this.element;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

}

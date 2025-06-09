package AVL;

public class NodoAVL<E> {
    E data;
    private NodoAVL<E> left, right;
    int height;

    public NodoAVL(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodoAVL<E> getLeft() {
        return left;
    }

    public void setLeft(NodoAVL<E> left) {
        this.left = left;
    }

    public NodoAVL<E> getRight() {
        return right;
    }

    public void setRight(NodoAVL<E> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

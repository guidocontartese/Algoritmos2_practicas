package aBB;

public interface ABBTDA <T extends Comparable<T>>{
    public boolean pertenece(T element);
    public void insert(T element);
    public T delete(T element);
    //public String toString();
}

package aBB;

public interface ABBTDA <T extends Comparable<T>>{
    public boolean belong(T element);
    public void insert(T element);
    public T delete(T element);
    //public String toString();
}

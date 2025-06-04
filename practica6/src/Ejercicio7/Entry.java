package Ejercicio7;

public interface Entry<K extends Comparable<K>,V>
{
    public K getKey();
    public V getValue();
}


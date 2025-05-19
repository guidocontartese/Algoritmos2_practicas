package aBB;

import comparator.DefaultComparator;

public class ABB <T extends Comparable<T>> implements ABBTDA<T>{
    protected NodeABB<T> raiz;
    DefaultComparator<T> comparator;
    int size;
    public ABB(){
        raiz = new NodeABB<>(null, null);
        comparator = null;
        size = 0;
    }
    public ABB(NodeABB<T> raiz, DefaultComparator<T> comparator){
        this.raiz = raiz;
        this.comparator = comparator;
        size = 0;
    }


    public boolean belong(T element){
        return buscarNode(element, this.raiz) != null;
    }
    private NodeABB<T> buscarNode(T element, NodeABB<T> raiz){
        if(raiz.getData() == null)
            return null;
        else{
            int comparacion = comparator.compare(raiz.getData(), element);
            if(comparacion == 0)
                return raiz;
            else if(comparacion < 0)
                return buscarNode(element, raiz.getRightNode());
            else
                return buscarNode(element, raiz.getLeftNode());
        }
    }
    public void insert(T element){
        NodeABB<T> nodoAInsertar = buscarNode(element, raiz);
        if(nodoAInsertar != null)
            throw new RuntimeException("Ya se encuentra el nodo");
        else{
            nodoAInsertar.setData(element);
            nodoAInsertar.setLeftNode(new NodeABB<>(null, nodoAInsertar));
            nodoAInsertar.setRightNode(new NodeABB<>(null, nodoAInsertar));
            size++;
        }
    }
    public T delete(T element){
        NodeABB<T> nodoAEliminar = buscarNode(element, raiz);
        if(nodoAEliminar == null)
            throw new RuntimeException("Ya se encuentra el nodo");
        else{
            T retorno = nodoAEliminar.getData();
            NodeABB<T> prev = nodoAEliminar.getFatherNode();
            if(nodoAEliminar.getLeftNode() != null){
                prev.setLeftNode(nodoAEliminar.getLeftNode());
                nodoAEliminar.getLeftNode().setFatherNode(prev);
            }
            if(nodoAEliminar.getRightNode() != null){
                prev.setRightNode(nodoAEliminar.getRightNode());
                nodoAEliminar.getRightNode().setFatherNode(prev);
            }
            nodoAEliminar = null;
            return retorno;
        }
    }
    //public String toString();
}

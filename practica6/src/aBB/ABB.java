package aBB;

import comparator.DefaultComparator;

public class ABB <T extends Comparable<T>> implements ABBTDA<T>{
    protected NodeABB<T> raiz;
    DefaultComparator<T> comparator;
    int size;
    public ABB(){
        raiz = null;
        comparator = null;
        size = 0;
    }
    public ABB(DefaultComparator<T> comparator){
        raiz = null;
        this.comparator = comparator;
        size = 0;
    }
    public ABB(NodeABB<T> raiz, DefaultComparator<T> comparator){
        this.raiz = raiz;
        this.comparator = comparator;
        size = 0;
    }


    public boolean pertenece(T element){
        return buscarNode(element, this.raiz, false) != null;
    }
    private NodeABB<T> buscarNode(T element, NodeABB<T> raiz, boolean busquedaInsercion){
        /*Le agrego un tercer parametro boolean, ya que, si estoy buscando un nodo para
        insertar data y yo devuelvo el ultimo nodo es null, por ende, si necesito insertar mi nuevo nodo en un nodo hoja.
        devuelvo el nodo padre y con ese lo inserto donde corresponda*/
        if(raiz == null)
            return raiz;
        int comparacion = comparator.compare(raiz.getData(), element);
        if(comparacion == 0)
            return raiz;
        else if(comparacion < 0)
        {
            if(busquedaInsercion && raiz.getRightNode() == null)
                return raiz;
            return buscarNode(element, raiz.getRightNode(), busquedaInsercion);
        }
        else{
            if(busquedaInsercion && raiz.getLeftNode() == null)
                return raiz;
            return buscarNode(element, raiz.getLeftNode(), busquedaInsercion);
        }

    }
    public void insert(T element){
        if(size==0){
            raiz = new NodeABB<>(element, null);
            size++;
            return;
        }
        NodeABB<T> nodoAInsertar = buscarNode(element, raiz, true);
        if(nodoAInsertar.getData().compareTo(element) == 0)
            return;//throw new RuntimeException("Ya se encuentra el nodo");
        else if(nodoAInsertar.getData().compareTo(element)> 0){
            nodoAInsertar.setLeftNode(new NodeABB<>(element, nodoAInsertar));
        }
        else{
            nodoAInsertar.setRightNode(new NodeABB<>(element, nodoAInsertar));
        }
        size++;
    }
    public T delete(T element){
        /*Agregar prevencion de eliminar con size 0*/
        NodeABB<T> nodoAEliminar = buscarNode(element, raiz, false);
        if(nodoAEliminar == null)
            throw new RuntimeException("No se encontro el nodo");
        else {
            T retorno = nodoAEliminar.getData();
            if (size == 1) { // si es mi unico nodo...
                raiz = null;
                size--;
            } else {
                if (nodoAEliminar.getLeftNode() != null && nodoAEliminar.getRightNode() != null) {
                    eliminarNodo2Hijos(nodoAEliminar);
                } else if (nodoAEliminar.getRightNode() != null || nodoAEliminar.getLeftNode() != null) {
                    eliminarNodo1Hijo(nodoAEliminar);
                } else
                    eliminarNodoSinHijos(nodoAEliminar);
            }

            size--;
            return retorno;
        }
    }
    private void eliminarNodo2Hijos(NodeABB<T> nodoAEliminar){
        /*al tener 2 hijos debo encontrar el menor nodo del lado mayor derecho y suplantar
        mi nodo eliminado por el*/
        NodeABB<T> nuevaRaiz = encontrarMinimo(nodoAEliminar.getRightNode());
        nodoAEliminar.setData(nuevaRaiz.getData());
        if(nuevaRaiz.getRightNode()!= null){
            eliminarNodo1Hijo(nuevaRaiz);
        }
        else
            eliminarNodoSinHijos(nuevaRaiz);
    }
    private void eliminarNodo1Hijo(NodeABB<T> nodoAEliminar){
        NodeABB<T> hijo = nodoAEliminar.getLeftNode()!=null ? nodoAEliminar.getLeftNode() : nodoAEliminar.getRightNode();
        if(nodoAEliminar == this.raiz){
            this.raiz = hijo;
            hijo.setFatherNode(null);
        }
        else{
            NodeABB<T> father = nodoAEliminar.getFatherNode();
            hijo.setFatherNode(father);
            if(father.getRightNode() == nodoAEliminar){
                father.setRightNode(hijo);
            }
            else{
                father.setLeftNode(hijo);
            }
        }
    }
    private void eliminarNodoSinHijos(NodeABB<T> nodoAEliminar){
        if(nodoAEliminar == raiz){
            raiz = null;
        }
        else{
            NodeABB<T> father = nodoAEliminar.getFatherNode();
            if(father.getRightNode() == nodoAEliminar){
                father.setRightNode(null);
            }
            else{
                father.setLeftNode(null);
            }
        }
    }
    private NodeABB<T> encontrarMinimo(NodeABB<T> nodo){
        /*Funcion auxiliar para encontrar el nodo mas chico de esa raiz*/
        while(nodo.getLeftNode() != null){
            nodo = nodo.getLeftNode();
        }
        return nodo;
    }
    //public String toString();
}

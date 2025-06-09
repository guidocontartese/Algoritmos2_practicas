package AVL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class AVL<E extends Comparable<E>> implements AVLTDA<E> {
    private final Comparator<E> comp;
    private NodoAVL<E> root;

    public AVL(Comparator<E> comp) {
        this.comp = comp;
        this.root = null;
    }

    private int getHeight(NodoAVL<E> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    // CORREGIDO: Balance = altura_izquierda - altura_derecha
    private int getBalance(NodoAVL<E> node) {
        return (node == null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private NodoAVL<E> rightRotate(NodoAVL<E> y) {
        NodoAVL<E> x = y.getLeft();
        NodoAVL<E> t2 = x.getRight();

        // Realizar rotación
        x.setRight(y);
        y.setLeft(t2);

        // Actualizar alturas
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));
        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));

        return x;
    }

    private NodoAVL<E> leftRotate(NodoAVL<E> x) {
        NodoAVL<E> y = x.getRight();
        NodoAVL<E> t2 = y.getLeft();

        // Realizar rotación
        y.setLeft(x);
        x.setRight(t2);

        // Actualizar alturas
        x.setHeight(1 + Math.max(getHeight(x.getLeft()), getHeight(x.getRight())));
        y.setHeight(1 + Math.max(getHeight(y.getLeft()), getHeight(y.getRight())));

        return y;
    }

    private NodoAVL<E> insert(NodoAVL<E> node, E data) {
        // Validación de entrada
        if (data == null) {
            throw new IllegalArgumentException("No se pueden insertar valores null");
        }

        // 1. Inserción normal de BST
        if (node == null) {
            return new NodoAVL<>(data);
        }

        int cmp = comp.compare(data, node.data);
        if (cmp < 0) {
            node.setLeft(insert(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(insert(node.getRight(), data));
        } else {
            // Elemento duplicado, no insertar
            return node;
        }

        // 2. Actualizar altura del nodo actual
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        // 3. Rebalancear el árbol
        return rebalanceAfterInsertion(node, data);
    }

    private NodoAVL<E> delete(NodoAVL<E> node, E data) {
        // Validación de entrada
        if (data == null) {
            return node;
        }

        // 1. Eliminación normal de BST
        if (node == null) {
            return null;
        }

        int cmp = comp.compare(data, node.data);
        if (cmp < 0) {
            node.setLeft(delete(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(delete(node.getRight(), data));
        } else {
            // Nodo a eliminar encontrado
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            // Nodo con dos hijos: obtener el sucesor inorder
            NodoAVL<E> min = minValueNode(node.getRight());
            node.setData(min.getData());
            node.setRight(delete(node.getRight(), min.getData()));
        }

        // 2. Actualizar altura
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));

        // 3. Rebalancear
        return rebalanceAfterDeletion(node);
    }

    // CORREGIDO: Rebalanceo después de inserción
    private NodoAVL<E> rebalanceAfterInsertion(NodoAVL<E> node, E data) {
        int balance = getBalance(node);

        // Caso Left-Left
        if (balance > 1 && comp.compare(data, node.getLeft().data) < 0) {
            return rightRotate(node);
        }

        // Caso Right-Right
        if (balance < -1 && comp.compare(data, node.getRight().data) > 0) {
            return leftRotate(node);
        }

        // Caso Left-Right
        if (balance > 1 && comp.compare(data, node.getLeft().data) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Caso Right-Left
        if (balance < -1 && comp.compare(data, node.getRight().data) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    // CORREGIDO: Rebalanceo después de eliminación
    private NodoAVL<E> rebalanceAfterDeletion(NodoAVL<E> node) {
        int balance = getBalance(node);

        // Caso Left-Left
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rightRotate(node);
        }

        // Caso Left-Right
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Caso Right-Right
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return leftRotate(node);
        }

        // Caso Right-Left
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public void insert(E data) {
        root = insert(root, data);
    }

    public void delete(E data) {
        root = delete(root, data);
    }

    public boolean exists(E value) {
        return exists(root, value);
    }

    private boolean exists(NodoAVL<E> node, E value) {
        if (node == null || value == null) return false;

        int cmp = comp.compare(value, node.data);
        if (cmp == 0) return true;
        return (cmp < 0) ? exists(node.getLeft(), value) : exists(node.getRight(), value);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(NodoAVL<E> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.data + " ");
            inOrderTraversal(node.getRight());
        }
    }

    public NodoAVL<E> getRaiz() {
        return root;
    }

    private NodoAVL<E> minValueNode(NodoAVL<E> node) {
        NodoAVL<E> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    // CORREGIDO: toString mejorado
    public String toString() {
        if (root == null) {
            return "()";
        }
        String salida = inOrderTraversalString(root);
        return salida.trim();
    }

    private String inOrderTraversalString(NodoAVL<E> node) {
        if (node == null) {
            return "";
        }

        String left = inOrderTraversalString(node.getLeft());
        String right = inOrderTraversalString(node.getRight());

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (!left.isEmpty()) {
            sb.append(left).append(" ");
        }
        sb.append(node.data);
        if (!right.isEmpty()) {
            sb.append(" ").append(right);
        }
        sb.append(")");

        return sb.toString();
    }

    public void impresionNiveles() {
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Queue<NodoAVL<E>> cola = new LinkedList<>();
        cola.add(root);
        impresionYAgregado(cola);
    }

    private void impresionYAgregado(Queue<NodoAVL<E>> cola) {
        int nivel = 0;
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            // Procesar todos los nodos del nivel actual
            for (int i = 0; i < nodosEnNivel; i++) {
                NodoAVL<E> nodo = cola.poll();
                System.out.print(nodo.getData());

                if (i < nodosEnNivel - 1) {
                    System.out.print(", ");
                }
                // Agregar hijos a la cola para el siguiente nivel
                if (nodo.getLeft() != null) {
                    cola.add(nodo.getLeft());
                }
                if (nodo.getRight() != null) {
                    cola.add(nodo.getRight());
                }
            }
            System.out.println(); // Nueva línea después de cada nivel
            nivel++;
        }
    }

    // NUEVO: Método para verificar si el árbol está balanceado
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(NodoAVL<E> node) {
        if (node == null) return true;

        int balance = getBalance(node);
        return Math.abs(balance) <= 1 &&
                isBalanced(node.getLeft()) &&
                isBalanced(node.getRight());
    }

    // NUEVO: Método para obtener la altura del árbol
    public int getTreeHeight() {
        return getHeight(root);
    }
}
package grafoD;

public class ejercicio3 {
    public static void main(String[] args){
                GrafoDin<String> grafo = new GrafoDin<>();

                // Agregamos vértices
                grafo.agregarVertice("A");
                grafo.agregarVertice("B");
                grafo.agregarVertice("C");
                grafo.agregarVertice("D"); // Nodo aislado
                grafo.agregarVertice("E"); // Nodo aislado

                // Agregamos aristas
                grafo.agregarArista("A", "B", 1);
                grafo.agregarArista("B", "C", 2);
                grafo.agregarArista("C", "A", 3);

                // Probamos nodosAislados
                System.out.println("Nodos aislados: " + grafo.nodosAislados());
                // Debería imprimir: [D, E] (no tienen aristas salientes ni entrantes)

                // Agregamos una arista a D
                grafo.agregarArista("A", "D", 4);
                System.out.println("Nodos aislados después de conectar D: " + grafo.nodosAislados());
                // Ahora debería imprimir solo: [E]

                // Conectamos E consigo mismo
                grafo.agregarArista("E", "E", 5);
                System.out.println("Nodos aislados después de auto-conectar E: " + grafo.nodosAislados());
                // Ahora debería estar vacío (ningún nodo aislado)
            }
    }


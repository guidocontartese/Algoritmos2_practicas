package grafoS;

import java.util.List;

public class ejercicio2 {
    public static void main(String[] args) {
        GrafoEst<String> grafo = new GrafoEst<>();
        grafo.inicializarGrafo();

        // Agregamos vértices
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        // Agregamos aristas con distintos pesos desde A
        grafo.agregarArista("A", "B", 5);
        grafo.agregarArista("C", "B", 2);
        grafo.agregarArista("A", "C", 10);
        grafo.agregarArista("A", "D", 12);

        // También agregamos algunas aristas desde otros vértices (opcional)
        grafo.agregarArista("B", "C", 7);
        grafo.agregarArista("C", "D", 2);

        // Llamamos a la función mayorCostoArista para A
        int mayorCosto = grafo.mayorCostoArista("A");
        System.out.println("Mayor costo desde el vértice A: " + mayorCosto);

        // Llamamos para otro vértice
        int mayorCostoB = grafo.mayorCostoArista("B");
        System.out.println("Mayor costo desde el vértice B: " + mayorCostoB);
        List<String> predecesoresB = grafo.predecesoresVertices("B");
        System.out.print("Predecesores del vértice B: ");
        for (String pred : predecesoresB) {
            System.out.print(pred + " ");
        }
        System.out.println();
    }
}

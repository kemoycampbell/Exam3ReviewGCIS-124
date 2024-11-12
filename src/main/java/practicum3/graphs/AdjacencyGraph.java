package practicum3.graphs;

import java.util.HashMap;
import java.util.Map;

public class AdjacencyGraph<E> implements Graph<E> 
{
    private Map<E, Vertex<E>> vertices;

    public AdjacencyGraph()
    {
        this.vertices = new HashMap<E, Vertex<E>>();
    }

    @Override
    public void add(E value) 
    {
        Vertex<E> vertex = new Vertex<E>(value);
        this.vertices.put(value, vertex);
    }

    @Override
    public boolean contains(E value) {
        return this.vertices.containsKey(value);
    }

    @Override
    public int size() {
        return this.vertices.size();
    }

    @Override
    public void connectDirected(E a, E b) {
        Vertex<E> vertexA = this.vertices.get(a);
        Vertex<E> vertexB = this.vertices.get(b);

        vertexA.connect(vertexB);
    }

    @Override
    public void connectUndirected(E a, E b) {
        Vertex<E> vertexA = this.vertices.get(a);
        Vertex<E> vertexB = this.vertices.get(b);

        vertexA.connect(vertexB);
        vertexB.connect(vertexA);
    }

    @Override
    public boolean connected(E a, E b) {
        Vertex<E> vertex = this.vertices.get(a);
        Vertex<E> vertexB = this.vertices.get(b);
        return vertex.connected(vertexB);
    }

    @Override
    public String toString()
    {
        String graph = "";
        for (Map.Entry<E, Vertex<E>> entry : vertices.entrySet())
        {
            Vertex<E> vertex = entry.getValue();
            graph+=vertex.toString() + "\n";
        }

        return graph;
    }
    
}

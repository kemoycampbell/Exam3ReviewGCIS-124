package practicum3.graphs;

public class GraphStuff 
{
    public static void main(String args[])
    {
        Graph<String> graph = new AdjacencyGraph<String>();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");
        graph.add("F");

        //neighbors
        //direct
        graph.connectDirected("A", "D");
        graph.connectDirected("C", "A");
        graph.connectDirected("E", "C");

        //undirect
        graph.connectUndirected("B", "A");
        graph.connectUndirected("B", "E");
        graph.connectUndirected("E", "F");
        graph.connectUndirected("F", "C");
        graph.connectUndirected("F", "D");

        System.out.println(graph);

    }
    
}

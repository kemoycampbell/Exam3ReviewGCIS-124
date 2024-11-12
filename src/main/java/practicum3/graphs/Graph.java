package practicum3.graphs;

public interface Graph<E>
{
    public void add(E value);
    public boolean contains(E value);
    public int size();
    public void connectDirected(E a, E b);
    public void connectUndirected(E a, E b);
    public boolean connected(E a, E b);
}

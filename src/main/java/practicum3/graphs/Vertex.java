package practicum3.graphs;

import java.util.HashSet;
import java.util.Set;

public class Vertex<E> {
   private E value;
   private Set<Vertex<E>> neighbors;

   public Vertex(E value)
   {
        this.value = value;
        this.neighbors = new HashSet<>();
   }

   public E getValue()
   {
        return value;
   }

   public void connect(Vertex<E> neighbor)
   {
        this.neighbors.add(neighbor);
   }

   public boolean connected(Vertex<E> neighbor)
   {
        return this.neighbors.contains(neighbor);
   }

   public Set<Vertex<E>> getNeighbors()
   {
        return this.neighbors;
   }

   @Override
   public String toString()
   {
        String vertex = this.value.toString();
        if(this.neighbors.size() > 0) {
            vertex+=" [";
            //add append all neighbors
            for(Vertex<E> neighbor: neighbors){
                vertex+=neighbor.value.toString()+", ";
            }
            vertex = vertex.substring(0, vertex.length()-2);
            vertex+="]";
        }

        return vertex;
   }
}

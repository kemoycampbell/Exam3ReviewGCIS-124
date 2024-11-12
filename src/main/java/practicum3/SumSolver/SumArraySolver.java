package practicum3.SumSolver;

import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import practicum3.backtracker.Backtracker;
import practicum3.backtracker.Configuration;

public class SumArraySolver implements Configuration
{
    private int k;
    private int[] elements;
    private int n;



    public SumArraySolver(int k, int n)
    {
        this(k, n,new int[0]);
    }

    private SumArraySolver(int k, int n,int[] elements)
    {
        this.k = k;
        this.n = n;
        this.elements = elements;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        Collection<Configuration> successors = new LinkedList<Configuration>();

        if(this.elements.length == this.n)
            return successors;
        
        for(int i = 1; i <=k/2; i++){
            int nextElementGuessPosition = this.elements.length;
            int[] newElement = Arrays.copyOf(elements, this.elements.length+1);
            newElement[nextElementGuessPosition] = i;
            successors.add(new SumArraySolver(k, n, newElement));
        }

        return successors;
    }

    @Override
    public boolean isValid() {
        Map<Integer, Integer> visited = new HashMap<>();
        for(int i = 0; i < this.elements.length; i++){
            int element = this.elements[i];
            if(visited.containsKey(element))
                return false;
            visited.put(element, element); 
        }

        return true;
    }


    @Override
    public boolean isGoal() {
        int sum = 0;
        for(int i = 0; i < this.elements.length; i++){
            sum+=this.elements[i];
        }

        return this.elements.length == n && sum == k;
    }

    @Override
    public String toString()
    {
        if(this.elements.length == 0)
            return "<empty>";
        
        String elementString = "[";
        for(int i = 0; i < this.elements.length; i++){
            elementString+=this.elements[i] +",";
        }

        elementString = elementString.substring(0, elementString.length()-1);
        elementString = elementString + "]";

        return String.format("n=%d,k=%d:%s",n,k,elementString);

    }

    //test our algorithm
    public static void main(String args[])
    {
        Backtracker solver = new Backtracker(false);
        Configuration solution = solver.solve(new SumArraySolver(48, 3));
        System.out.println("Solution:" + solution);
    }
    
}

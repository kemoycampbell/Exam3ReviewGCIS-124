package practicum3.Greedy;

import java.util.Arrays;
public class Greedy 
{

    public int maximizeArraySum(int[] array, int k)
    {
        Arrays.sort(array);//the in place sort is o(nlogn)
        int sum = 0;

        for(int i =0; i < k; i++) {
            array[i]*=-1;
            sum+=array[i];
        }

        return sum;

    }
    
}

import java.util.*;

public class Order {

    static HashMap<Integer, Integer> order = new HashMap<Integer, Integer>();
    static Integer finalLength = null;

    public Order (int[] reqLengths, int[] reqQuantities){

        for( int i = 0; i < reqLengths.length; i++){
            if (i == reqLengths.length-1){
                finalLength = reqLengths[i];
            }
            order.put(reqLengths[i],reqQuantities[i]);
        }
    }

    public static Integer getFinalLength(){
        return finalLength;
    }

    public static HashMap<Integer,Integer> getOrder(){
        return order;
    }

    
    
}

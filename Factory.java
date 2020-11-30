import java.util.*;

public class Factory {

    static HashMap<Integer, Integer> order = new HashMap<Integer, Integer>();
    static HashMap<Integer, Double> logistics = new HashMap<Integer,Double>();


    public Factory(HashMap<Integer, Double> logistics ) {

        Factory.logistics = logistics;
    }


    public static void createOrder(int[] reqLengths, int[] reqQuantities){

        for( int i = 0; i < reqLengths.length; i++){
            order.put(reqLengths[i],reqQuantities[i]);
        }
    }


    public static Double Fulfill() {

        Double finalCost = 0.0;

        for (Integer currentRequiredLength : order.keySet()){
            List<Integer> cuttableStockLengths = new ArrayList<Integer>();
            for(Integer currentStockLength : logistics.keySet()){
                if (currentRequiredLength <= currentStockLength){
                    cuttableStockLengths.add(currentStockLength);
                }
            }

            Collections.shuffle(cuttableStockLengths);   
            Integer chosenStockLength = cuttableStockLengths.get(0);
            double cost = logistics.get(chosenStockLength);
            
            int remainingCuts = order.get(currentRequiredLength);
            

            while( remainingCuts != 0){
                
                if(chosenStockLength - currentRequiredLength > 0){
                    chosenStockLength -= currentRequiredLength;
                    remainingCuts -= 1;
                }
                else if (chosenStockLength - currentRequiredLength == 0){
                    
                    remainingCuts -= 1;
                    finalCost += cost;
                    chosenStockLength -= currentRequiredLength;
                }
                else {
                    finalCost += cost;
                    Collections.shuffle(cuttableStockLengths);
                    chosenStockLength = cuttableStockLengths.get(0);
                    cost = logistics.get(chosenStockLength);
                }
            }
        }
        return finalCost;
    }
}

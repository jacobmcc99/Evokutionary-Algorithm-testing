import java.util.*;

public class Factory {

    //declare hashmaps to hold logistics information
    static HashMap<Integer, Double> logistics = new HashMap<Integer,Double>();

    //constructor method taking a parameter of the stock lengths and costs
    public Factory(HashMap<Integer, Double> logistics ) {
        Factory.logistics = logistics;
    }

    public static Double Fulfill() {
        HashMap<Integer,Integer> order = Order.getOrder();
        //initialise final cost
        Double finalCost = 0.0;
        boolean materialRemaining = false;
        int remainingLength = 0;
        double costOfPrevious = 0;

        /*  for every required length, create an array holding the stock lengths larger than it
        */
        for (Integer currentRequiredLength : order.keySet()){
            List<Integer> cuttableStockLengths = new ArrayList<Integer>();
            for(Integer currentStockLength : logistics.keySet()){
                if (currentRequiredLength <= currentStockLength){
                    cuttableStockLengths.add(currentStockLength);
                }
            }

            //randomise order of the cuttable lengths
            Collections.shuffle(cuttableStockLengths);
            //select the first length from the random list
            Integer chosenStockLength = cuttableStockLengths.get(0);
            //set cost to the cost of this length
            double cost = logistics.get(chosenStockLength);
            //set remaining cuts to the amount specified for this length in the order
            int remainingCuts = order.get(currentRequiredLength);

            /*if there was a partial stock cut leftover from the last length that was cut, 
            use this as the current length */
            if (materialRemaining) {
                chosenStockLength = remainingLength;
                cost = costOfPrevious;
            }
            

            while( remainingCuts != 0){

                /*if cutting from the stock length is possible without using all of it,
                remove the required length from the stock. If all cuts have been completed, set the remainder
                of the stock length to be used cut to make the next order. 
                */
                if(chosenStockLength - currentRequiredLength > 0){
                    chosenStockLength -= currentRequiredLength;
                    remainingCuts -= 1;
                    if(remainingCuts == 0){
                        /* if the current length is the last on the order and there are no remaining cuts,
                        the cost of the stock will be added to the total as it cannot be used for future cuts.*/
                        if (currentRequiredLength == Order.getFinalLength()){
                            finalCost += cost;
                        }
                        materialRemaining = true;
                        remainingLength = chosenStockLength;
                        costOfPrevious = cost;
                    }
                }
                /* if the remainder of the stock is the required length, add its cost onto the total
                */
                else if (chosenStockLength - currentRequiredLength == 0){
                    
                    remainingCuts -= 1;
                    finalCost += cost;
                    chosenStockLength -= currentRequiredLength;
                    if(remainingCuts == 0){
                        materialRemaining = false;
                    }
                }
                /*
                If the remaining stock cut is to small to make a cut from,
                add the cost of the stock to the total and select a new random stock length.
                */
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
